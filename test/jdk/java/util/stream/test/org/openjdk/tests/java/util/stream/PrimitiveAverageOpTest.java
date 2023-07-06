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

package org.openjdk.tests.java.util.stream;


import java.util.stream.*;
import org.testng.annotations.Test;














public class PrimitiveAverageOpTest extends OpTestCase {

    @Test(dataProvider = "IntStreamTestData", dataProviderClass = IntStreamTestDataProvider.class)
    public void testOps(String name, TestData.OfInt data) {
        exerciseTerminalOps(data, s -> s.average());
    }

    @Test(dataProvider = "LongStreamTestData", dataProviderClass = LongStreamTestDataProvider.class)
    public void testOps(String name, TestData.OfLong data) {
        exerciseTerminalOps(data, s -> s.average());
    }

    // @@@ For Double depending on the input data the average algorithm may produce slightly
    //     different results for the sequential and parallel evaluation.results are within
    //     While the following works at the moment, it could change when double data, not cast from long
    //     values is introduced, or if the average/sum algorithm is modified.
    @Test(dataProvider = "DoubleStreamTestData", dataProviderClass = DoubleStreamTestDataProvider.class)
    public void testOps(String name, TestData.OfDouble data) {
        exerciseTerminalOps(data, s -> s.average());
    }

}