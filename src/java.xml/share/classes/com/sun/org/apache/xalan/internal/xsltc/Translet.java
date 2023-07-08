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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc;

import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import java.xml.share.classes.com.sun.org.apache.xml.internal.serializer.SerializationHandler;

/**
 * @LastModified: Oct 2017
 */
public interface Translet {

    void transform(DOM document, SerializationHandler handler)
        throws TransletException;
    void transform(DOM document, SerializationHandler[] handlers)
        throws TransletException;
    void transform(DOM document, DTMAxisIterator iterator,
                          SerializationHandler handler)
        throws TransletException;

    Object addParameter(String name, Object value);

    void buildKeys(DOM document, DTMAxisIterator iterator,
                          SerializationHandler handler, int root)
        throws TransletException;
    void addAuxiliaryClass(Class<?> auxClass);
    Class<?> getAuxiliaryClass(String className);
    String[] getNamesArray();
    String[] getUrisArray();
    int[]    getTypesArray();
    String[] getNamespaceArray();
    boolean overrideDefaultParser();
    void setOverrideDefaultParser(boolean flag);

}
