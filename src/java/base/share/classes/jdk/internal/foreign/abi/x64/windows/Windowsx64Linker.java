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
package java.base.share.classes.jdk.internal.foreign.abi.x64.windows;

import java.base.share.classes.jdk.internal.foreign.abi.AbstractLinker;
import java.base.share.classes.jdk.internal.foreign.abi.LinkerOptions;

import java.base.share.classes.java.lang.foreign.FunctionDescriptor;
import java.base.share.classes.java.lang.foreign.MemorySegment;
import java.base.share.classes.java.lang.foreign.SegmentScope;
import java.base.share.classes.java.lang.foreign.VaList;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.util.function.Consumer;

/**
 * ABI implementation based on Windows ABI AMD64 supplement v.0.99.6
 */
public final class Windowsx64Linker extends AbstractLinker {

    public static Windowsx64Linker getInstance() {
        final class Holder {
            private static final Windowsx64Linker INSTANCE = new Windowsx64Linker();
        }

        return Holder.INSTANCE;
    }

    private Windowsx64Linker() {
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
        WinVaList.Builder builder = WinVaList.builder(scope);
        actions.accept(builder);
        return builder.build();
    }

    public static VaList newVaListOfAddress(long address, SegmentScope scope) {
        return WinVaList.ofAddress(address, scope);
    }

    public static VaList emptyVaList() {
        return WinVaList.empty();
    }
}

