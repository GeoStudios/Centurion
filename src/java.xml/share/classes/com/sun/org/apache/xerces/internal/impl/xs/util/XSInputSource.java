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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.util;

import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.SchemaGrammar;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.parser.XMLInputSource;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSObject;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * @xerces.internal
 *
 */
public final class XSInputSource extends XMLInputSource {

    private SchemaGrammar[] fGrammars;
    private XSObject[] fComponents;

    public XSInputSource(SchemaGrammar[] grammars) {
        super(null, null, null, false);
        fGrammars = grammars;
        fComponents = null;
    }

    public XSInputSource(XSObject[] component) {
        super(null, null, null, false);
        fGrammars = null;
        fComponents = component;
    }

    public SchemaGrammar[] getGrammars() {
        return fGrammars;
    }

    public void setGrammars(SchemaGrammar[] grammars) {
        fGrammars = grammars;
    }

    public XSObject[] getComponents() {
        return fComponents;
    }

    public void setComponents(XSObject[] components) {
        fComponents = components;
    }
}
