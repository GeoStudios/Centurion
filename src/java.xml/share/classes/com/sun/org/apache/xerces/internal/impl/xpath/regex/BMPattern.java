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

import java.text.CharacterIterator;

/**
 * Boyer-Moore searcher.
 *
 * @xerces.internal
 *
 */
public class BMPattern {
    final char[] pattern;
    final int[] shiftTable;
    final boolean ignoreCase;

    public BMPattern(String pat, boolean ignoreCase) {
        this(pat, 256, ignoreCase);
    }

    public BMPattern(String pat, int tableSize, boolean ignoreCase) {
        this.pattern = pat.toCharArray();
        this.shiftTable = new int[tableSize];
        this.ignoreCase = ignoreCase;

        int length = pattern.length;
        for (int i = 0;  i < this.shiftTable.length;  i ++)
            this.shiftTable[i] = length;

        for (int i = 0;  i < length;  i ++) {
            char ch = this.pattern[i];
            int diff = length-i-1;
            int index = ch % this.shiftTable.length;
            if (diff < this.shiftTable[index])
                this.shiftTable[index] = diff;
            if (this.ignoreCase) {
                ch = Character.toUpperCase(ch);
                index = ch % this.shiftTable.length;
                if (diff < this.shiftTable[index])
                    this.shiftTable[index] = diff;
                ch = Character.toLowerCase(ch);
                index = ch % this.shiftTable.length;
                if (diff < this.shiftTable[index])
                    this.shiftTable[index] = diff;
            }
        }
    }

    /**
     *
     * @return -1 if <var>iterator</var> does not contain this pattern.
     */
    public int matches(CharacterIterator iterator, int start, int limit) {
        if (this.ignoreCase)  return this.matchesIgnoreCase(iterator, start, limit);
        int plength = this.pattern.length;
        if (plength == 0)  return start;
        int index = start+plength;
        while (index <= limit) {
            int pindex = plength;
            int nindex = index+1;
            char ch;
            do {
                if ((ch = iterator.setIndex(--index)) != this.pattern[--pindex])
                    break;
                if (pindex == 0)
                    return index;
            } while (pindex > 0);
            index += this.shiftTable[ch % this.shiftTable.length]+1;
            if (index < nindex)  index = nindex;
        }
        return -1;
    }

    /**
     *
     * @return -1 if <var>str</var> does not contain this pattern.
     */
    public int matches(String str, int start, int limit) {
        if (this.ignoreCase)  return this.matchesIgnoreCase(str, start, limit);
        int plength = this.pattern.length;
        if (plength == 0)  return start;
        int index = start+plength;
        while (index <= limit) {
            //System.err.println("Starts at "+index);
            int pindex = plength;
            int nindex = index+1;
            char ch;
            do {
                if ((ch = str.charAt(--index)) != this.pattern[--pindex])
                    break;
                if (pindex == 0)
                    return index;
            } while (pindex > 0);
            index += this.shiftTable[ch % this.shiftTable.length]+1;
            if (index < nindex)  index = nindex;
        }
        return -1;
    }
    /**
     *
     * @return -1 if <var>chars</char> does not contain this pattern.
     */
    public int matches(char[] chars, int start, int limit) {
        if (this.ignoreCase)  return this.matchesIgnoreCase(chars, start, limit);
        int plength = this.pattern.length;
        if (plength == 0)  return start;
        int index = start+plength;
        while (index <= limit) {
            //System.err.println("Starts at "+index);
            int pindex = plength;
            int nindex = index+1;
            char ch;
            do {
                if ((ch = chars[--index]) != this.pattern[--pindex])
                    break;
                if (pindex == 0)
                    return index;
            } while (pindex > 0);
            index += this.shiftTable[ch % this.shiftTable.length]+1;
            if (index < nindex)  index = nindex;
        }
        return -1;
    }

    int matchesIgnoreCase(CharacterIterator iterator, int start, int limit) {
        int plength = this.pattern.length;
        if (plength == 0)  return start;
        int index = start+plength;
        while (index <= limit) {
            int pindex = plength;
            int nindex = index+1;
            char ch;
            do {
                char ch1 = ch = iterator.setIndex(--index);
                char ch2 = this.pattern[--pindex];
                if (ch1 != ch2) {
                    ch1 = Character.toUpperCase(ch1);
                    ch2 = Character.toUpperCase(ch2);
                    if (ch1 != ch2 && Character.toLowerCase(ch1) != Character.toLowerCase(ch2))
                        break;
                }
                if (pindex == 0)
                    return index;
            } while (pindex > 0);
            index += this.shiftTable[ch % this.shiftTable.length]+1;
            if (index < nindex)  index = nindex;
        }
        return -1;
    }

    int matchesIgnoreCase(String text, int start, int limit) {
        int plength = this.pattern.length;
        if (plength == 0)  return start;
        int index = start+plength;
        while (index <= limit) {
            int pindex = plength;
            int nindex = index+1;
            char ch;
            do {
                char ch1 = ch = text.charAt(--index);
                char ch2 = this.pattern[--pindex];
                if (ch1 != ch2) {
                    ch1 = Character.toUpperCase(ch1);
                    ch2 = Character.toUpperCase(ch2);
                    if (ch1 != ch2 && Character.toLowerCase(ch1) != Character.toLowerCase(ch2))
                        break;
                }
                if (pindex == 0)
                    return index;
            } while (pindex > 0);
            index += this.shiftTable[ch % this.shiftTable.length]+1;
            if (index < nindex)  index = nindex;
        }
        return -1;
    }
    int matchesIgnoreCase(char[] chars, int start, int limit) {
        int plength = this.pattern.length;
        if (plength == 0)  return start;
        int index = start+plength;
        while (index <= limit) {
            int pindex = plength;
            int nindex = index+1;
            char ch;
            do {
                char ch1 = ch = chars[--index];
                char ch2 = this.pattern[--pindex];
                if (ch1 != ch2) {
                    ch1 = Character.toUpperCase(ch1);
                    ch2 = Character.toUpperCase(ch2);
                    if (ch1 != ch2 && Character.toLowerCase(ch1) != Character.toLowerCase(ch2))
                        break;
                }
                if (pindex == 0)
                    return index;
            } while (pindex > 0);
            index += this.shiftTable[ch % this.shiftTable.length]+1;
            if (index < nindex)  index = nindex;
        }
        return -1;
    }

    /*
    public static void main(String[] argv) {
        try {
            int[] shiftTable = new int[256];
            initializeBoyerMoore(argv[0], shiftTable, true);
            int o = -1;
            CharacterIterator ite = new java.text.StringCharacterIterator(argv[1]);
            long start = System.currentTimeMillis();
            //for (int i = 0;  i < 10000;  i ++)
                o = searchIgnoreCasesWithBoyerMoore(ite, 0, argv[0], shiftTable);
            start = System.currentTimeMillis()-start;
            System.out.println("Result: "+o+", Elapsed: "+start);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }*/
}
