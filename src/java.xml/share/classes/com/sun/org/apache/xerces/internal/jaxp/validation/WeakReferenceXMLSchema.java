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

import java.lang.ref.WeakReference;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;

/**
 * <p>An implementation of Schema for W3C XML Schemas
 * that keeps a weak reference to its grammar pool. If
 * no validators currently have a reference to the
 * grammar pool, the garbage collector is free to reclaim
 * its memory.</p>
 *
 * @LastModified: Oct 2017
 */
final class WeakReferenceXMLSchema extends AbstractXMLSchema {

    /** Weak reference to grammar pool. */
    private WeakReference<XMLGrammarPool> fGrammarPool = new WeakReference<>(null);

    public WeakReferenceXMLSchema() {}

    /*
     * XSGrammarPoolContainer methods
     */

    public synchronized XMLGrammarPool getGrammarPool() {
        XMLGrammarPool grammarPool = fGrammarPool.get();
        // If there's no grammar pool then either we haven't created one
        // yet or the garbage collector has already cleaned out the previous one.
        if (grammarPool == null) {
            grammarPool = new SoftReferenceGrammarPool();
            fGrammarPool = new WeakReference<>(grammarPool);
        }
        return grammarPool;
    }

    public boolean isFullyComposed() {
        return false;
    }

} // WeakReferenceXMLSchema
