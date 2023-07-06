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

import jdk.internal.vm.ci.share.classes.jdk.vm.ci.runtime.src.jdk.vm.ci.code.CodeCacheProvider;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.runtime.src.jdk.vm.ci.code.TargetDescription;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.runtime.src.jdk.vm.ci.code.stack.StackIntrospection;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.runtime.src.jdk.vm.ci.meta.ConstantReflectionProvider;
import jdk.internal.vm.ci.share.classes.jdk.vm.ci.runtime.src.jdk.vm.ci.meta.MetaAccessProvider;

/**
 * A JVMCI backend encapsulates the capabilities needed by a Java based compiler for compiling and
 * installing code for a single compute unit within a JVM. In a JVM with support for heterogeneous
 * computing, more than one backend may be exposed.
 */
public class JVMCIBackend {

    private final MetaAccessProvider metaAccess;
    private final CodeCacheProvider codeCache;
    private final ConstantReflectionProvider constantReflection;
    private final StackIntrospection stackIntrospection;

    public JVMCIBackend(MetaAccessProvider metaAccess, CodeCacheProvider codeCache, ConstantReflectionProvider constantReflection, StackIntrospection stackIntrospection) {
        this.metaAccess = metaAccess;
        this.codeCache = codeCache;
        this.constantReflection = constantReflection;
        this.stackIntrospection = stackIntrospection;
    }

    public MetaAccessProvider getMetaAccess() {
        return metaAccess;
    }

    public CodeCacheProvider getCodeCache() {
        return codeCache;
    }

    public ConstantReflectionProvider getConstantReflection() {
        return constantReflection;
    }

    public TargetDescription getTarget() {
        return codeCache.getTarget();
    }

    public StackIntrospection getStackIntrospection() {
        return stackIntrospection;
    }
}