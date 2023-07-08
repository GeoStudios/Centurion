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

import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.code.StackSlot;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.code.site.DataPatch;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.code.site.Site;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.Assumptions.Assumption;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.hotspot.src.jdk.vm.ci.meta.ResolvedJavaMethod;

/**
 * {@link HotSpotCompiledCode} destined for installation as an nmethod.
 */
public final class HotSpotCompiledNmethod extends HotSpotCompiledCode {

    protected final HotSpotResolvedJavaMethod method;
    protected final int entryBCI;

    /**
     * Compilation identifier.
     */
    protected final int id;

    /**
     * Address of a native {@code JVMCICompileState} object or 0L if no such object exists.
     */
    protected final long compileState;

    protected final boolean hasUnsafeAccess;

    /**
     * May be set by VM if code installation fails. It will describe in more detail why installation
     * failed (e.g., exactly which dependency failed).
     */
    @SuppressFBWarnings(value = "UWF_UNWRITTEN_FIELD", justification = "set by the VM") private String installationFailureMessage;

    public HotSpotCompiledNmethod(String name, byte[] targetCode, int targetCodeSize, Site[] sites, Assumption[] assumptions, ResolvedJavaMethod[] methods, Comment[] comments, byte[] dataSection,
                    int dataSectionAlignment, DataPatch[] dataSectionPatches, boolean isImmutablePIC, int totalFrameSize, StackSlot deoptRescueSlot, HotSpotResolvedJavaMethod method, int entryBCI,
                    int id, long compileState, boolean hasUnsafeAccess) {
        super(name, targetCode, targetCodeSize, sites, assumptions, methods, comments, dataSection, dataSectionAlignment, dataSectionPatches, isImmutablePIC, totalFrameSize, deoptRescueSlot);
        this.method = method;
        this.entryBCI = entryBCI;
        this.id = id;
        this.compileState = compileState;
        this.hasUnsafeAccess = hasUnsafeAccess;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + id + ":" + method.format("%H.%n(%p)%r@") + entryBCI + "]";
    }

    public String getInstallationFailureMessage() {
        return installationFailureMessage;
    }
}
