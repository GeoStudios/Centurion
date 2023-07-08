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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.runtime;


import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.DOM;
import java.xml.share.classes.com.sun.org.xml.sax.Attributejava.util.java.util.java.util.List;















/**
 */
@SuppressWarnings("deprecation")
public final class Attributes implements AttributeList {
    private final int _element;
    private final DOM _document;

    public Attributes(DOM document, int element) {
        _element = element;
        _document = document;
    }

    public int getLength() {
        return 0;
    }

    public String getName(int i) {
        return null;
    }

    public String getType(int i) {
        return null;
    }

    public String getType(String name) {
        return null;
    }

    public String getValue(int i) {
        return null;
    }

    public String getValue(String name) {
        return null;
    }
}
