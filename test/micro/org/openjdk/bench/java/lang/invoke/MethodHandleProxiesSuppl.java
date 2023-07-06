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

package org.openjdk.bench.java.lang.invoke;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandleProxies;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.concurrent.TimeUnit;














/**
 * Benchmark evaluates the performance of MethodHandleProxies.* supplementary methods.
 *
 * @author Aleksey Shipilev (aleksey.shipilev@oracle.com)
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class MethodHandleProxiesSuppl {

    /*
     * Implementation notes:
     *   - This is a very naive test, evaluates the performance of service methods.
     */

    private Doable instance;

    @Setup
    public void setup() throws Throwable {
        MethodHandle target = MethodHandles.lookup().findStatic(MethodHandleProxiesSuppl.class, "doWork", MethodType.methodType(int.class, int.class));
        instance = MethodHandleProxies.asInterfaceInstance(Doable.class, target);
    }

    @Benchmark
    public Object baselineReturn() {
        return instance;
    }

    @Benchmark
    public boolean testIsWrapperInstance() {
        return MethodHandleProxies.isWrapperInstance(instance);
    }

    @Benchmark
    public Class<?> testInstanceType() {
        return MethodHandleProxies.wrapperInstanceType(instance);
    }

    @Benchmark
    public MethodHandle testInstanceTarget() {
        return MethodHandleProxies.wrapperInstanceTarget(instance);
    }

    public static int doWork(int i) {
        throw new IllegalStateException("Can't touch this");
    }

    public interface Doable {
        int doWork(int i);
    }

}