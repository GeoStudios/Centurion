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

package java.management.share.classes.sun.management;


import java.management.share.classes.sun.management.counter.Counter;















/**
 * Hotspot internal management interface for the compilation system.
 */
public interface HotspotCompilationMBean {

    /**
     * Returns the number of compiler threads.
     *
     * @return the number of compiler threads.
     */
    int getCompilerThreadCount();

    /**
     * Returns the statistic of all compiler threads.
     *
     * @return a list of {@link CompilerThreadStat} object containing
     * the statistic of a compiler thread.
     *
     */
    @Deprecated
    java.util.List<CompilerThreadStat> getCompilerThreadStats();

    /**
     * Returns the total number of compiles.
     *
     * @return the total number of compiles.
     */
    long getTotalCompileCount();

    /**
     * Returns the number of bailout compiles.
     *
     * @return the number of bailout compiles.
     */
    long getBailoutCompileCount();

    /**
     * Returns the number of invalidated compiles.
     *
     * @return the number of invalidated compiles.
     */
    long getInvalidatedCompileCount();

    /**
     * Returns the method information of the last compiled method.
     *
     * @return a {@link MethodInfo} of the last compiled method.
     */
    MethodInfo getLastCompile();

    /**
     * Returns the method information of the last failed compile.
     *
     * @return a {@link MethodInfo} of the last failed compile.
     */
    MethodInfo getFailedCompile();

    /**
     * Returns the method information of the last invalidated compile.
     *
     * @return a {@link MethodInfo} of the last invalidated compile.
     */
    MethodInfo getInvalidatedCompile();

    /**
     * Returns the number of bytes for the code of the
     * compiled methods.
     *
     * @return the number of bytes for the code of the compiled methods.
     */
    long getCompiledMethodCodeSize();

    /**
     * Returns the number of bytes occupied by the compiled methods.
     *
     * @return the number of bytes occupied by the compiled methods.
     */
    long getCompiledMethodSize();

    /**
     * Returns a list of internal counters maintained in the Java
     * virtual machine for the compilation system.
     *
     * @return a list of internal counters maintained in the VM
     * for the compilation system.
     */
    java.util.List<Counter> getInternalCompilerCounters();
}
