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

package jdk.test.lib.hprof.util;


import java.util.Enumeration;
import java.util.NoSuchElementException;
import jdk.test.lib.hprof.model.JavaHeapObject;














/*
 * The Original Code is HAT. The Initial Developer of the
 * Original Code is Bill Foote, with contributions from others
 * at JavaSoft/Sun.
 */



public class CompositeEnumeration implements Enumeration<JavaHeapObject> {
    Enumeration<JavaHeapObject> e1;
    Enumeration<JavaHeapObject> e2;

    public CompositeEnumeration(Enumeration<JavaHeapObject> e1, Enumeration<JavaHeapObject> e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public boolean hasMoreElements() {
        return e1.hasMoreElements() || e2.hasMoreElements();
    }

    public JavaHeapObject nextElement() {
        if (e1.hasMoreElements()) {
            return e1.nextElement();
        }

        if (e2.hasMoreElements()) {
            return e2.nextElement();
        }

        throw new NoSuchElementException();
    }
}
