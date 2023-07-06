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

package test;
















public class TestGetElementReferenceDataWithErrors {

    class Constructors extends BaseWithConstructor {
        Constructors(String name) {
            System.err.println();
            this(name/*getElement:PARAMETER:name*/.length/*getElement:METHOD:java.lang.String.length()*/());
            super(name/*getElement:PARAMETER:name*/.length/*getElement:METHOD:java.lang.String.length()*/());
            this(name/*getElement:PARAMETER:name*/.length/*getElement:METHOD:java.lang.String.length()*/());
        }
        Constructors(int name) {
            System.err.println();
            super(name/*getElement:PARAMETER:name*/);
            this(name/*getElement:PARAMETER:name*/);
            super(String/*getElement:CLASS:java.lang.String*/.valueOf(name/*getElement:PARAMETER:name*/));
        }
    }
    class BaseWithConstructor {
        BaseWithConstructor(int len) {}
    }
}
