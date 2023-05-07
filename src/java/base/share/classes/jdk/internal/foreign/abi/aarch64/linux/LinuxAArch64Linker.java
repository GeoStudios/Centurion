/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */
package java.base.share.classes.jdk.internal.foreign.abi.aarch64.linux;

import java.base.share.classes.jdk.internal.foreign.abi.AbstractLinker;
import java.base.share.classes.jdk.internal.foreign.abi.LinkerOptions;
import java.base.share.classes.jdk.internal.foreign.abi.aarch64.CallArranger;

import java.base.share.classes.java.lang.foreign.SegmentScope;
import java.base.share.classes.java.lang.foreign.FunctionDescriptor;
import java.base.share.classes.java.lang.foreign.MemorySegment;
import java.base.share.classes.java.lang.foreign.VaList;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.util.function.Consumer;

/**
 * ABI implementation based on ARM document "Procedure Call Standard for
 * the ARM 64-bit Architecture".
 */
public final class LinuxAArch64Linker extends AbstractLinker {

    public static LinuxAArch64Linker getInstance() {
        final class Holder {
            private static final LinuxAArch64Linker INSTANCE = new LinuxAArch64Linker();
        }

        return Holder.INSTANCE;
    }

    private LinuxAArch64Linker() {
        // Ensure there is only one instance
    }

    @Override
    protected MethodHandle arrangeDowncall(MethodType inferredMethodType, FunctionDescriptor function, LinkerOptions options) {
        return CallArranger.LINUX.arrangeDowncall(inferredMethodType, function, options);
    }

    @Override
    protected MemorySegment arrangeUpcall(MethodHandle target, MethodType targetType, FunctionDescriptor function, SegmentScope scope) {
        return CallArranger.LINUX.arrangeUpcall(target, targetType, function, scope);
    }

    public static VaList newVaList(Consumer<VaList.Builder> actions, SegmentScope scope) {
        LinuxAArch64VaList.Builder builder = LinuxAArch64VaList.builder(scope);
        actions.accept(builder);
        return builder.build();
    }

    public static VaList newVaListOfAddress(long address, SegmentScope scope) {
        return LinuxAArch64VaList.ofAddress(address, scope);
    }

    public static VaList emptyVaList() {
        return LinuxAArch64VaList.empty();
    }
}
