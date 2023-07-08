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

package compiler.types.correctness.execution;


import compiler.types.correctness.hierarchies.TypeHierarchy;
import compiler.types.correctness.scenarios.Scenario;














/**
 * Execution scenario represents test methods execution type.
 * @param <T> parameter type
 * @param <R> result Type
 */
public interface Execution<T extends TypeHierarchy.I, R> {
    /**
     * Executes the test code of the given scenario
     * See {@link Scenario#run(T)}
     *
     * @param scenario test scenario
     */
    void execute(Scenario<T, R> scenario);

    default String getName() {
        return this.getClass().getName();
    }
}
