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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs;


import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.Stringjava.util.java.util.java.util.List;
import java.util.Abstractjava.util.java.util.java.util.List;















/**
 * StringList implementation for schema error codes and error messages.
 *
 * @xerces.internal
 *
 *
 * @LastModified: Oct 2017
 */
final class PSVIErrorList extends AbstractList<String> implements StringList {

    private final String[] fArray;
    private final int fLength;
    private final int fOffset;

    public PSVIErrorList(String[] array, boolean even) {
        fArray = array;
        fLength = (fArray.length >> 1);
        fOffset = even ? 0 : 1;
    }

    public boolean contains(String item) {
        if (item == null) {
            for (int i = 0; i < fLength; ++i) {
                if (fArray[(i << 1) + fOffset] == null) {
                    return true;
                }
            }
        }
        else {
            for (int i = 0; i < fLength; ++i) {
                if (item.equals(fArray[(i << 1) + fOffset])) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getLength() {
        return fLength;
    }

    public String item(int index) {
        if (index < 0 || index >= fLength) {
            return null;
        }
        return fArray[(index << 1) + fOffset];
    }

    /*
     * List methods
     */

    public String get(int index) {
        if (index >= 0 && index < fLength) {
            return fArray[(index << 1) + fOffset];
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    public int size() {
        return getLength();
    }

} // class PSVIErrorList
