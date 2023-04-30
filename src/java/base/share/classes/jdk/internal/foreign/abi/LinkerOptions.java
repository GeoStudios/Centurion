/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.jdk.internal.foreign.abi;

import java.base.share.classes.java.lang.foreign.FunctionDescriptor;
import java.base.share.classes.java.lang.foreign.Linker;
import java.base.share.classes.java.lang.foreign.MemoryLayout;
import java.base.share.classes.java.lang.foreign.StructLayout;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class LinkerOptions {

    private static final LinkerOptions EMPTY = new LinkerOptions(Map.of());
    private final Map<Class<?>, LinkerOptionImpl> optionsMap;

    private LinkerOptions(Map<Class<?>, LinkerOptionImpl> optionsMap) {
        this.optionsMap = optionsMap;
    }

    public static LinkerOptions forDowncall(FunctionDescriptor desc, Linker.Option... options) {
        Map<Class<?>, LinkerOptionImpl> optionMap = new HashMap<>();

        for (Linker.Option option : options) {
            if (optionMap.containsKey(option.getClass())) {
                throw new IllegalArgumentException("Duplicate option: " + option);
            }
            LinkerOptionImpl opImpl = (LinkerOptionImpl) option;
            opImpl.validateForDowncall(desc);
            optionMap.put(option.getClass(), opImpl);
        }

        return new LinkerOptions(optionMap);
    }

    public static LinkerOptions empty() {
        return EMPTY;
    }

    private <T extends Linker.Option> T getOption(Class<T> type) {
        return type.cast(optionsMap.get(type));
    }

    public boolean isVarargsIndex(int argIndex) {
        FirstVariadicArg fva = getOption(FirstVariadicArg.class);
        return fva != null && argIndex >= fva.index();
    }

    public boolean hasCapturedCallState() {
        return getOption(CaptureCallStateImpl.class) != null;
    }

    public Stream<CapturableState> capturedCallState() {
        CaptureCallStateImpl stl = getOption(CaptureCallStateImpl.class);
        return stl == null ? Stream.empty() : stl.saved().stream();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof LinkerOptions that
                && Objects.equals(optionsMap, that.optionsMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionsMap);
    }

    public sealed interface LinkerOptionImpl extends Linker.Option
                                             permits FirstVariadicArg,
                                                     CaptureCallStateImpl {
        default void validateForDowncall(FunctionDescriptor descriptor) {
            throw new IllegalArgumentException("Not supported for downcall: " + this);
        }
    }

    public record FirstVariadicArg(int index) implements LinkerOptionImpl {
        @Override
        public void validateForDowncall(FunctionDescriptor descriptor) {
            if (index < 0 || index > descriptor.argumentLayouts().size()) {
                throw new IllegalArgumentException("Index '" + index + "' not in bounds for descriptor: " + descriptor);
            }
        }
    }

    public record CaptureCallStateImpl(Set<CapturableState> saved) implements LinkerOptionImpl, Linker.Option.CaptureCallState {

        @Override
        public void validateForDowncall(FunctionDescriptor descriptor) {
            // done during construction
        }

        @Override
        public StructLayout layout() {
            return MemoryLayout.structLayout(
                saved.stream()
                      .sorted(Comparator.comparingInt(CapturableState::ordinal))
                      .map(CapturableState::layout)
                      .toArray(MemoryLayout[]::new)
            );
        }
    }

}
