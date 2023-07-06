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

package java.desktop.share.classes.sun.print;


import javax.print.attribute.Attribute;
import javax.print.attribute.PrintRequestAttribute;















/*
 * A class used to determine the range of pages to be printed.
 */
@SuppressWarnings("serial") // JDK implementation class
public final class SunPageSelection implements PrintRequestAttribute {

    public static final SunPageSelection ALL = new SunPageSelection(0);
    public static final SunPageSelection RANGE = new SunPageSelection(1);
    public static final SunPageSelection SELECTION = new SunPageSelection(2);

    private final int pages;

    public SunPageSelection(int value) {
        pages = value;
    }

    public Class<? extends Attribute> getCategory() {
        return SunPageSelection.class;
    }

    public String getName() {
        return "sun-page-selection";
    }

    public String toString() {
       return "page-selection: " + pages;
    }

}
