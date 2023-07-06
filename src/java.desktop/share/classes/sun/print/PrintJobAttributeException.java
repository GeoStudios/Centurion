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

import javax.print.AttributeException;
import javax.print.PrintException;
import javax.print.attribute.Attribute;

@SuppressWarnings("serial") // JDK-implementation class
class PrintJobAttributeException extends PrintException
    implements AttributeException {

    private final Attribute attr;
    private final Class<?> category;

    PrintJobAttributeException(String s, Class<?> cat, Attribute attrval) {
        super(s);
        attr = attrval;
        category = cat;
    }

    public Class<?>[] getUnsupportedAttributes() {
        if (category == null) {
            return null;
        } else {
            Class<?>[] cats = { category};
            return cats;
        }
    }

    public Attribute[] getUnsupportedValues() {
        if (attr == null) {
            return null;
        } else {
            Attribute [] attrs = { attr};
            return attrs;
        }
    }
}
