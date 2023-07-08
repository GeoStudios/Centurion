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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.jaxp.validation;


import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.grammars.Grammar;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarDescription;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * <p>Filter {@link XMLGrammarPool} that exposes a
 * read-only view of the underlying pool.</p>
 *
 */
final class ReadOnlyGrammarPool implements XMLGrammarPool {

    private final XMLGrammarPool core;

    public ReadOnlyGrammarPool( XMLGrammarPool pool ) {
        this.core = pool;
    }

    public void cacheGrammars(String grammarType, Grammar[] grammars) {
        // noop. don't let caching to happen
    }

    public void clear() {
        // noop. cache is read-only.
    }

    public void lockPool() {
        // noop. this pool is always read-only
    }

    public Grammar retrieveGrammar(XMLGrammarDescription desc) {
        return core.retrieveGrammar(desc);
    }

    public Grammar[] retrieveInitialGrammarSet(String grammarType) {
        return core.retrieveInitialGrammarSet(grammarType);
    }

    public void unlockPool() {
        // noop. this pool is always read-only.
    }

} // ReadOnlyGrammarPool
