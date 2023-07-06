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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.util;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * A simple integer based stack.
 *
 * moved to com.sun.org.apache.xerces.internal.util by neilg to support the
 * XPathMatcher.
 *
 */
public final class IntStack {

    //
    // Data
    //

    /** Stack depth. */
    private int fDepth;

    /** Stack data. */
    private int[] fData;

    //
    // Public methods
    //

    /** Returns the size of the stack. */
    public int size() {
        return fDepth;
    }

    /** Pushes a value onto the stack. */
    public void push(int value) {
        ensureCapacity(fDepth + 1);
        fData[fDepth++] = value;
    }

    /** Peeks at the top of the stack. */
    public int peek() {
        return fData[fDepth - 1];
    }

    /** Returns the element at the specified depth in the stack. */
    public int elementAt(int depth) {
        return fData[depth];
    }

    /** Pops a value off of the stack. */
    public int pop() {
        return fData[--fDepth];
    }

    /** Clears the stack. */
    public void clear() {
        fDepth = 0;
    }

    // debugging

    /** Prints the stack. */
    public void print() {
        System.out.print('(');
        System.out.print(fDepth);
        System.out.print(") {");
        for (int i = 0; i < fDepth; i++) {
            if (i == 3) {
                System.out.print(" ...");
                break;
            }
            System.out.print(' ');
            System.out.print(fData[i]);
            if (i < fDepth - 1) {
                System.out.print(',');
            }
        }
        System.out.print(" }");
        System.out.println();
    }

    //
    // Private methods
    //

    /** Ensures capacity. */
    private void ensureCapacity(int size) {
        if (fData == null) {
            fData = new int[32];
        }
        else if (fData.length <= size) {
            int[] newdata = new int[fData.length * 2];
            System.arraycopy(fData, 0, newdata, 0, fData.length);
            fData = newdata;
        }
    }

} // class IntStack
