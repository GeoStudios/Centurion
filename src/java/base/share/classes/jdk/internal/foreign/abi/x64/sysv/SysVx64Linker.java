/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package jdk.internal.foreign.abi.x64.sysv;


import jdk.internal.foreign.abi.AbstractLinker;
import jdk.internal.foreign.abi.LinkerOptions;

import java.lang.foreign.SegmentScope;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.VaList;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.util.function.Consumer;

/**
 * ABI implementation based on System V ABI AMD64 supplement v.0.99.6
 */
public final class SysVx64Linker extends AbstractLinker {

    public static SysVx64Linker getInstance() {
        final class Holder {
            private static final SysVx64Linker INSTANCE = new SysVx64Linker();
        }

        return Holder.INSTANCE;
    }

    private SysVx64Linker() {
        // Ensure there is only one instance
    }
    @Override
    protected MethodHandle arrangeDowncall(MethodType inferredMethodType, FunctionDescriptor function, LinkerOptions options) {
        return CallArranger.arrangeDowncall(inferredMethodType, function, options);
    }

    @Override
    protected MemorySegment arrangeUpcall(MethodHandle target, MethodType targetType, FunctionDescriptor function, SegmentScope scope) {
        return CallArranger.arrangeUpcall(target, targetType, function, scope);
    }

    public static VaList newVaList(Consumer<VaList.Builder> actions, SegmentScope scope) {
        SysVVaList.Builder builder = SysVVaList.builder(scope);
        actions.accept(builder);
        return builder.build();
    }

    public static VaList newVaListOfAddress(long address, SegmentScope scope) {
        return SysVVaList.ofAddress(address, scope);
    }

    public static VaList emptyVaList() {
        return SysVVaList.empty();
    }
}
