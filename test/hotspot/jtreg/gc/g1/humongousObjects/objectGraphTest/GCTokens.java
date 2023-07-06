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

package gc.g1.humongousObjects.objectGraphTest;

/**
 * Contains tokens that could appear in gc log
 */
public final class GCTokens {
    // Private c-tor to prevent instantiating
    private GCTokens() {
    }

    public static final String WB_INITIATED_YOUNG_GC = "Young (Normal) (WhiteBox Initiated Young GC)";
    public static final String WB_INITIATED_MIXED_GC = "Young (Mixed) (WhiteBox Initiated Young GC)";
    public static final String WB_INITIATED_CMC = "WhiteBox Initiated Concurrent Mark";
    public static final String FULL_GC = "Full (System.gc())";
    public static final String FULL_GC_MEMORY_PRESSURE = "WhiteBox Initiated Full GC";
    public static final String CMC = "Concurrent Mark)";
    public static final String YOUNG_GC = "GC pause (young)";
}