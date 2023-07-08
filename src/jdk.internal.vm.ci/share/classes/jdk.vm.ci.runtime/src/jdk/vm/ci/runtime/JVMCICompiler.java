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

package jdk.internal.vm.ci.share.classes.jdk.vm.ci.runtime.src.jdk.vm.ci.runtime;


import jdk.internal.vm.ci.share.classes.jdk.vm.ci.runtime.src.jdk.vm.ci.code.CompilationRequest;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.runtime.src.jdk.vm.ci.code.CompilationRequestResult;















public interface JVMCICompiler {
    int INVOCATION_ENTRY_BCI = -1;

    /**
     * Services a compilation request. This object should compile the method to machine code and
     * install it in the code cache if the compilation is successful.
     */
    CompilationRequestResult compileMethod(CompilationRequest request);

    /**
     * Determines if this compiler supports the {@code gcIdentifier} garbage collector. The default
     * implementation of this method returns true as that is the effective answer given by a
     * {@link JVMCICompiler} before this method was added.
     *
     * @param gcIdentifier a VM dependent GC identifier
     */
    default boolean isGCSupported(int gcIdentifier) {
        return true;
    }
}
