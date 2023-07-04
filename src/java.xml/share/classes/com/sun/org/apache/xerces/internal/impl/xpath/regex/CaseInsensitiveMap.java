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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.impl.xpath.regex;

/**
 */

final class CaseInsensitiveMap {

    private static final int CHUNK_SHIFT = 10;           /* 2^10 = 1k */
    private static final int CHUNK_SIZE = (1<<CHUNK_SHIFT);
    private static final int CHUNK_MASK = (CHUNK_SIZE-1);
    private static final int INITIAL_CHUNK_COUNT = 64;   /* up to 0xFFFF */

    private static int[][][] caseInsensitiveMap;

    private static final int LOWER_CASE_MATCH = 1;
    private static final int UPPER_CASE_MATCH = 2;

    static {
        buildCaseInsensitiveMap();
    }

    /**
     *  Return a list of code point characters (not including the input value)
     *  that can be substituted in a case insensitive match
     */
    static public int[] get(int codePoint) {
        return (codePoint < 0x10000) ? getMapping(codePoint) : null;
    }

    private static int[] getMapping(int codePoint) {
        int chunk = codePoint >>> CHUNK_SHIFT;
        int offset = codePoint & CHUNK_MASK;

        return caseInsensitiveMap[chunk][offset];
    }

    private static void buildCaseInsensitiveMap() {
        caseInsensitiveMap = new int[INITIAL_CHUNK_COUNT][CHUNK_SIZE][];
        int lc, uc;
        for (int i=0; i<0x10000; i++) {
            lc = Character.toLowerCase(i);
            uc = Character.toUpperCase(i);

            // lower/upper case value is not the same as code point
            if (lc != uc || lc != i) {
                int[] map = new int[2];
                int index = 0;

                if (lc != i) {
                    map[index++] = lc;
                    map[index++] = LOWER_CASE_MATCH;
                    int[] lcMap = getMapping(lc);
                    if (lcMap != null) {
                        map = updateMap(i, map, lc, lcMap, LOWER_CASE_MATCH);
                    }
                }

                if (uc != i) {
                    if (index == map.length) {
                        map = expandMap(map, 2);
                    }
                    map[index++] = uc;
                    map[index++] = UPPER_CASE_MATCH;
                    int[] ucMap = getMapping(uc);
                    if (ucMap != null) {
                        map = updateMap(i, map, uc, ucMap, UPPER_CASE_MATCH);
                    }
                }

                set(i, map);
            }
        }
    }

    private static int[] expandMap(int[] srcMap, int expandBy) {
        final int oldLen = srcMap.length;
        int[] newMap = new int[oldLen + expandBy];

        System.arraycopy(srcMap, 0, newMap, 0, oldLen);
        return newMap;
    }

    private static void set(int codePoint, int[] map) {
        int chunk = codePoint >>> CHUNK_SHIFT;
        int offset = codePoint & CHUNK_MASK;

        caseInsensitiveMap[chunk][offset] = map;
    }

    private static int[] updateMap(int codePoint, int[] codePointMap,
            int ciCodePoint, int[] ciCodePointMap, int matchType) {
        for (int i=0; i<ciCodePointMap.length; i+=2) {
            int c = ciCodePointMap[i];
            int[] cMap = getMapping(c);
            if (cMap != null) {
                if (contains(cMap, ciCodePoint, matchType)) {
                    if (!contains(cMap, codePoint)) {
                        cMap = expandAndAdd(cMap, codePoint, matchType);
                        set(c, cMap);
                    }
                    if (!contains(codePointMap, c)) {
                        codePointMap = expandAndAdd(codePointMap, c,matchType);
                    }
                }
            }
        }

        if (!contains(ciCodePointMap, codePoint)) {
            ciCodePointMap = expandAndAdd(ciCodePointMap, codePoint, matchType);
            set(ciCodePoint, ciCodePointMap);
        }

        return codePointMap;
    }

    private static boolean contains(int[] map, int codePoint) {
        for (int i=0; i<map.length; i += 2) {
            if (map[i] == codePoint) {
                return true;
            }
        }
        return false;
    }

    private static boolean contains(int[] map, int codePoint, int matchType) {
        for (int i=0; i<map.length; i += 2) {
            if (map[i] == codePoint && map[i+1] == matchType) {
                return true;
            }
        }
        return false;
    }

    private static int[] expandAndAdd(int[] srcMap, int codePoint, int matchType) {
        final int oldLen = srcMap.length;
        int[] newMap = new int[oldLen + 2];

        System.arraycopy(srcMap, 0, newMap, 0, oldLen);
        newMap[oldLen] = codePoint;
        newMap[oldLen+1] = matchType;
        return newMap;
    }
}
