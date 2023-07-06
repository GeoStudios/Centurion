/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.hotspot;


import java.base.share.classes.java.util.Arrays;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.code.stack.InspectedFrame;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.ResolvedJavaMethod;















public class HotSpotStackFrameReference implements InspectedFrame {

    private CompilerToVM compilerToVM;
    // set in the VM when materializeVirtualObjects is called
    @SuppressWarnings("unused") private boolean objectsMaterialized;

    // information used to find the stack frame
    private long stackPointer;
    private int frameNumber;

    // information about the stack frame's contents
    private int bci;
    private HotSpotResolvedJavaMethod method;
    private Object[] locals;
    private boolean[] localIsVirtual;

    public long getStackPointer() {
        return stackPointer;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    @Override
    public Object getLocal(int index) {
        return locals[index];
    }

    @Override
    public boolean isVirtual(int index) {
        return localIsVirtual != null && localIsVirtual[index];
    }

    @Override
    public void materializeVirtualObjects(boolean invalidateCode) {
        compilerToVM.materializeVirtualObjects(this, invalidateCode);
    }

    @Override
    public int getBytecodeIndex() {
        return bci;
    }

    @Override
    public ResolvedJavaMethod getMethod() {
        return method;
    }

    @Override
    public boolean isMethod(ResolvedJavaMethod otherMethod) {
        return method.equals(otherMethod);
    }

    @Override
    public boolean hasVirtualObjects() {
        return localIsVirtual != null;
    }

    @Override
    public String toString() {
        return "HotSpotStackFrameReference [stackPointer=" + stackPointer + ", frameNumber=" + frameNumber + ", bci=" + bci + ", method=" + getMethod() + ", locals=" + Arrays.toString(locals) +
                        ", localIsVirtual=" + Arrays.toString(localIsVirtual) + "]";
    }
}
