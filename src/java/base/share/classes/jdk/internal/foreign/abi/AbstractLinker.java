/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.jdk.internal.foreign.abi;

import java.base.share.classes.jdk.internal.foreign.SystemLookup;
import java.base.share.classes.jdk.internal.foreign.abi.aarch64.linux.LinuxAArch64Linker;
import java.base.share.classes.jdk.internal.foreign.abi.aarch64.macos.MacOsAArch64Linker;
import java.base.share.classes.jdk.internal.foreign.abi.riscv64.linux.LinuxRISCV64Linker;
import java.base.share.classes.jdk.internal.foreign.abi.x64.sysv.SysVx64Linker;
import java.base.share.classes.jdk.internal.foreign.abi.x64.windows.Windowsx64Linker;
import java.base.share.classes.jdk.internal.foreign.layout.AbstractLayout;

import java.base.share.classes.java.lang.foreign.GroupLayout;
import java.base.share.classes.java.lang.foreign.MemoryLayout;
import java.base.share.classes.java.lang.foreign.SegmentScope;
import java.base.share.classes.java.lang.foreign.FunctionDescriptor;
import java.base.share.classes.java.lang.foreign.Linker;
import java.base.share.classes.java.lang.foreign.MemorySegment;
import java.base.share.classes.java.lang.foreign.SequenceLayout;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.util.Objects;

public abstract sealed class AbstractLinker implements Linker permits LinuxAArch64Linker, MacOsAArch64Linker,
                                                                      SysVx64Linker, Windowsx64Linker, LinuxRISCV64Linker {

    private record LinkRequest(FunctionDescriptor descriptor, LinkerOptions options) {}
    private final SoftReferenceCache<LinkRequest, MethodHandle> DOWNCALL_CACHE = new SoftReferenceCache<>();

    @Override
    public MethodHandle downcallHandle(FunctionDescriptor function, Option... options) {
        Objects.requireNonNull(function);
        Objects.requireNonNull(options);
        checkHasNaturalAlignment(function);
        LinkerOptions optionSet = LinkerOptions.forDowncall(function, options);

        return DOWNCALL_CACHE.get(new LinkRequest(function, optionSet), linkRequest ->  {
            FunctionDescriptor fd = linkRequest.descriptor();
            MethodType type = fd.toMethodType();
            MethodHandle handle = arrangeDowncall(type, fd, linkRequest.options());
            handle = SharedUtils.maybeInsertAllocator(fd, handle);
            return handle;
        });
    }
    protected abstract MethodHandle arrangeDowncall(MethodType inferredMethodType, FunctionDescriptor function, LinkerOptions options);

    @Override
    public MemorySegment upcallStub(MethodHandle target, FunctionDescriptor function, SegmentScope scope) {
        Objects.requireNonNull(scope);
        Objects.requireNonNull(target);
        Objects.requireNonNull(function);
        checkHasNaturalAlignment(function);
        SharedUtils.checkExceptions(target);

        MethodType type = function.toMethodType();
        if (!type.equals(target.type())) {
            throw new IllegalArgumentException("Wrong method handle type: " + target.type());
        }
        return arrangeUpcall(target, target.type(), function, scope);
    }

    protected abstract MemorySegment arrangeUpcall(MethodHandle target, MethodType targetType,
                                                   FunctionDescriptor function, SegmentScope scope);

    @Override
    public SystemLookup defaultLookup() {
        return SystemLookup.getInstance();
    }

    // Current limitation of the implementation:
    // We don't support packed structs on some platforms,
    // so reject them here explicitly
    private static void checkHasNaturalAlignment(FunctionDescriptor descriptor) {
        descriptor.returnLayout().ifPresent(AbstractLinker::checkHasNaturalAlignmentRecursive);
        descriptor.argumentLayouts().forEach(AbstractLinker::checkHasNaturalAlignmentRecursive);
    }

    private static void checkHasNaturalAlignmentRecursive(MemoryLayout layout) {
        checkHasNaturalAlignment(layout);
        if (layout instanceof GroupLayout gl) {
            for (MemoryLayout member : gl.memberLayouts()) {
                checkHasNaturalAlignmentRecursive(member);
            }
        } else if (layout instanceof SequenceLayout sl) {
            checkHasNaturalAlignmentRecursive(sl.elementLayout());
        }
    }

    private static void checkHasNaturalAlignment(MemoryLayout layout) {
        if (!((AbstractLayout<?>) layout).hasNaturalAlignment()) {
            throw new IllegalArgumentException("Layout bit alignment must be natural alignment: " + layout);
        }
    }
}
