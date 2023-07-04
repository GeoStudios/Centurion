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
package com.sun.org.apache.xerces.internal.xni.grammars;

/**
 * A generic grammar for use in validating XML documents. The Grammar
 * object stores the validation information in a compiled form. Specific
 * subclasses extend this class and "populate" the grammar by compiling
 * the specific syntax (DTD, Schema, etc) into the data structures used
 * by this object.
 * <p>
 * <strong>Note:</strong> The Grammar object is not useful as a generic
 * grammar access or query object. In other words, you cannot round-trip
 * specific grammar syntaxes with the compiled grammar information in
 * the Grammar object. You <em>can</em> create equivalent validation
 * rules in your choice of grammar syntax but there is no guarantee that
 * the input and output will be the same.
 *
 * <p> Right now, this class is largely a shell; eventually,
 * it will be enriched by having more expressive methods added. </p>
 * will be moved from dtd.Grammar here.
 *
 *
 */

public interface Grammar {

    /**
     * get the <code>XMLGrammarDescription</code> associated with this
     * object
     */
    XMLGrammarDescription getGrammarDescription ();
} // interface Grammar
