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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xerces.internal.impl.dv;

import java.util.Locale;

/**
 * ValidationContext has all the information required for the
 * validation of: id, idref, entity, notation, qname
 *
 * @xerces.internal
 *
 */
public interface ValidationContext {
    // whether to validate against facets
    boolean needFacetChecking();

    // whether to do extra id/idref/entity checking
    boolean needExtraChecking();

    // whether we need to normalize the value that is passed!
    boolean needToNormalize();

    // are namespaces relevant in this context?
    boolean useNamespaces();

    // entity
    boolean isEntityDeclared (String name);
    boolean isEntityUnparsed (String name);

    // id
    boolean isIdDeclared (String name);
    void    addId(String name);

    // idref
    void addIdRef(String name);

    // get symbol from symbol table
    String getSymbol (String symbol);

    // qname
    String getURI(String prefix);

    // Locale
    Locale getLocale();

}
