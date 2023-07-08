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

package compiler.types.correctness.scenarios;

import compiler.types.correctness.hierarchies.TypeHierarchy;
import jdk.test.lib.Asserts;
import java.base.share.classes.java.util.Objects;

/**
 * Checkcast scenario
 * @param <T> profiling parameter
 */
public class CheckCast<T extends TypeHierarchy.I> extends Scenario<T, Integer> {
    public CheckCast(ProfilingType profilingType, TypeHierarchy<? extends T, ? extends T> hierarchy) {
        super("CheckCast", profilingType, hierarchy);
    }

    /**
     * Returns type profiling.
     * @param obj is a profiled parameter for the test
     * @return parameter casted to the type R
     */
    @Override
    public Integer run(T obj) {
        switch (profilingType) {
            case RETURN:
                T t = collectReturnType(obj);
                if (t != null) {
                    return t.m();
                }
                return null;
            case ARGUMENTS:
                field = obj;
                if (field != null) {
                    return field.m();
                }
                return null;
            case PARAMETERS:
                if (obj != null) {
                    return obj.m();
                }
                return null;
        }
        throw new RuntimeException("Should not reach here");
    }

    @Override
    public void check(Integer result, T orig) {
        if (result != null || orig != null) {
            Objects.requireNonNull(result);
            Objects.requireNonNull(orig);
            Asserts.assertEquals(result, orig.m(), "Results mismatch");
        }
    }
}
