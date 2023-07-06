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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xpath.regex;

import java.text.CharacterIterator;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * An instance of this class has ranges captured in matching.
 *
 * @xerces.internal
 *
 * @see RegularExpression#matches(char[], int, int, Match)
 * @see RegularExpression#matches(char[], Match)
 * @see RegularExpression#matches(java.text.CharacterIterator, Match)
 * @see RegularExpression#matches(java.lang.String, int, int, Match)
 * @see RegularExpression#matches(java.lang.String, Match)
 */
public class Match implements Cloneable {
    int[] beginpos = null;
    int[] endpos = null;
    int nofgroups = 0;

    CharacterIterator ciSource = null;
    String strSource = null;
    char[] charSource = null;

    /**
     * Creates an instance.
     */
    public Match() {
    }

    /**
     *
     */
    public synchronized Object clone() {
        Match ma = new Match();
        if (this.nofgroups > 0) {
            ma.setNumberOfGroups(this.nofgroups);
            if (this.ciSource != null)  ma.setSource(this.ciSource);
            if (this.strSource != null)  ma.setSource(this.strSource);
            for (int i = 0;  i < this.nofgroups;  i ++) {
                ma.setBeginning(i, this.getBeginning(i));
                ma.setEnd(i, this.getEnd(i));
            }
        }
        return ma;
    }

    /**
     *
     */
    protected void setNumberOfGroups(int n) {
        int oldn = this.nofgroups;
        this.nofgroups = n;
        if (oldn <= 0
            || oldn < n || n*2 < oldn) {
            this.beginpos = new int[n];
            this.endpos = new int[n];
        }
        for (int i = 0;  i < n;  i ++) {
            this.beginpos[i] = -1;
            this.endpos[i] = -1;
        }
    }

    /**
     *
     */
    protected void setSource(CharacterIterator ci) {
        this.ciSource = ci;
        this.strSource = null;
        this.charSource = null;
    }
    /**
     *
     */
    protected void setSource(String str) {
        this.ciSource = null;
        this.strSource = str;
        this.charSource = null;
    }
    /**
     *
     */
    protected void setSource(char[] chars) {
        this.ciSource = null;
        this.strSource = null;
        this.charSource = chars;
    }

    /**
     *
     */
    protected void setBeginning(int index, int v) {
        this.beginpos[index] = v;
    }

    /**
     *
     */
    protected void setEnd(int index, int v) {
        this.endpos[index] = v;
    }

    /**
     * Return the number of regular expression groups.
     * This method returns 1 when the regular expression has no capturing-parenthesis.
     */
    public int getNumberOfGroups() {
        if (this.nofgroups <= 0)
            throw new IllegalStateException("A result is not set.");
        return this.nofgroups;
    }

    /**
     * Return a start position in the target text matched to specified regular expression group.
     *
     * @param index Less than <code>getNumberOfGroups()</code>.
     */
    public int getBeginning(int index) {
        if (this.beginpos == null)
            throw new IllegalStateException("A result is not set.");
        if (index < 0 || this.nofgroups <= index)
            throw new IllegalArgumentException("The parameter must be less than "
                                               +this.nofgroups+": "+index);
        return this.beginpos[index];
    }

    /**
     * Return an end position in the target text matched to specified regular expression group.
     *
     * @param index Less than <code>getNumberOfGroups()</code>.
     */
    public int getEnd(int index) {
        if (this.endpos == null)
            throw new IllegalStateException("A result is not set.");
        if (index < 0 || this.nofgroups <= index)
            throw new IllegalArgumentException("The parameter must be less than "
                                               +this.nofgroups+": "+index);
        return this.endpos[index];
    }

    /**
     * Return an substring of the target text matched to specified regular expression group.
     *
     * @param index Less than <code>getNumberOfGroups()</code>.
     */
    public String getCapturedText(int index) {
        if (this.beginpos == null)
            throw new IllegalStateException("match() has never been called.");
        if (index < 0 || this.nofgroups <= index)
            throw new IllegalArgumentException("The parameter must be less than "
                                               +this.nofgroups+": "+index);
        String ret;
        int begin = this.beginpos[index], end = this.endpos[index];
        if (begin < 0 || end < 0)  return null;
        if (this.ciSource != null) {
            ret = REUtil.substring(this.ciSource, begin, end);
        } else if (this.strSource != null) {
            ret = this.strSource.substring(begin, end);
        } else {
            ret = new String(this.charSource, begin, end-begin);
        }
        return ret;
    }
}
