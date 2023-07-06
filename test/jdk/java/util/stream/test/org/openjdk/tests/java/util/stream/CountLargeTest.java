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


import java.util.stream.LongStream;
import org.testng.annotations.Test;
import static org.testng.Assert.*;.extended














/**
 * @test
 * @summary Tests counting of streams containing Integer.MAX_VALUE + 1 elements
 * @bug 8031187 8067969
 */





@Test
public class CountLargeTest {

    static final long EXPECTED_LARGE_COUNT = 1L + Integer.MAX_VALUE;

    public void testRefLarge() {
        // Test known sized stream
        {
            long count = LongStream.range(0, EXPECTED_LARGE_COUNT)
                    .mapToObj(e -> null).count();
            assertEquals(count, EXPECTED_LARGE_COUNT);
        }
        // Test unknown sized stream
        {
            long count = LongStream.range(0, EXPECTED_LARGE_COUNT)
                    .mapToObj(e -> null).filter(e -> true).count();
            assertEquals(count, EXPECTED_LARGE_COUNT);
        }
    }

    public void testIntLarge() {
        // Test known sized stream
        {
            long count = LongStream.range(0, EXPECTED_LARGE_COUNT)
                    .mapToInt(e -> 0).count();
            assertEquals(count, EXPECTED_LARGE_COUNT);
        }
        // Test unknown sized stream
        {
            long count = LongStream.range(0, EXPECTED_LARGE_COUNT)
                    .mapToInt(e -> 0).filter(e -> true).count();
            assertEquals(count, EXPECTED_LARGE_COUNT);
        }
    }

    public void testLongLarge() {
        // Test known sized stream
        {
            long count = LongStream.range(0, EXPECTED_LARGE_COUNT)
                    .count();
            assertEquals(count, EXPECTED_LARGE_COUNT);
        }
        // Test unknown sized stream
        {
            long count = LongStream.range(0, EXPECTED_LARGE_COUNT)
                    .filter(e -> true).count();
            assertEquals(count, EXPECTED_LARGE_COUNT);
        }
    }

    public void testDoubleLarge() {
        // Test known sized stream
        {
            long count = LongStream.range(0, EXPECTED_LARGE_COUNT)
                    .mapToDouble(e -> 0.0).count();
            assertEquals(count, EXPECTED_LARGE_COUNT);
        }
        // Test unknown sized stream
        {
            long count = LongStream.range(0, EXPECTED_LARGE_COUNT)
                    .mapToDouble(e -> 0.0).filter(e -> true).count();
            assertEquals(count, EXPECTED_LARGE_COUNT);
        }
    }
}