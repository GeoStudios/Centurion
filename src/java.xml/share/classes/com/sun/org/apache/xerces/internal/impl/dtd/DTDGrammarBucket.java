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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dtd;


import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarDescription;
import java.util.HashMap;
import java.util.Map;















/**
 * This very simple class is the skeleton of what the DTDValidator could use
 * to store various grammars that it gets from the GrammarPool.  As in the
 * case of XSGrammarBucket, one thinks of this object as being closely
 * associated with its validator; when fully mature, this class will be
 * filled from the GrammarPool when the DTDValidator is invoked on a
 * document, and, if a new DTD grammar is parsed, the new set will be
 * offered back to the GrammarPool for possible inclusion.
 *
 * @xerces.internal
 *
 *
*/
public class DTDGrammarBucket {

    // REVISIT:  make this class smarter and *way* more complete!

    //
    // Data
    //

    /** Grammars associated with element root name. */
    protected Map<XMLDTDDescription, DTDGrammar> fGrammars;

    // the unique grammar from fGrammars (or that we're
    // building) that is used in validation.
    protected DTDGrammar fActiveGrammar;

    // is the "active" grammar standalone?
    protected boolean fIsStandalone;

    //
    // Constructors
    //

    /** Default constructor. */
    public DTDGrammarBucket() {
        fGrammars = new HashMap<>();
    } // <init>()

    //
    // Public methods
    //

    /**
     * Puts the specified grammar into the grammar pool and associate it to
     * a root element name (this being internal, the lack of generality is irrelevant).
     *
     * @param grammar     The grammar.
     */
    public void putGrammar(DTDGrammar grammar) {
        XMLDTDDescription desc = (XMLDTDDescription)grammar.getGrammarDescription();
        fGrammars.put(desc, grammar);
    } // putGrammar(DTDGrammar)

    // retrieve a DTDGrammar given an XMLDTDDescription
    public DTDGrammar getGrammar(XMLGrammarDescription desc) {
        return fGrammars.get((XMLDTDDescription)desc);
    } // putGrammar(DTDGrammar)

    public void clear() {
        fGrammars.clear();
        fActiveGrammar = null;
        fIsStandalone = false;
    } // clear()

    // is the active grammar standalone?  This must live here because
    // at the time the validator discovers this we don't yet know
    // what the active grammar should be (no info about root)
    void setStandalone(boolean standalone) {
        fIsStandalone = standalone;
    }

    boolean getStandalone() {
        return fIsStandalone;
    }

    // set the "active" grammar:
    void setActiveGrammar (DTDGrammar grammar) {
        fActiveGrammar = grammar;
    }
    DTDGrammar getActiveGrammar () {
        return fActiveGrammar;
    }
} // class DTDGrammarBucket
