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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs;


import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.HashMap;
import java.util.java.util.java.util.java.util.List;
import java.util.Map;















/**
 * A class used to hold the internal schema grammar set for the current instance
 *
 * @xerces.internal
 *
 * @LastModified: Nov 2017
 */
public class XSGrammarBucket {

    // Data

    /**
     * Map that maps between Namespace and a Grammar
     */
    Map<String, SchemaGrammar> fGrammarRegistry = new HashMap<>();
    SchemaGrammar fNoNSGrammar = null;

    /**
     * Get the schema grammar for the specified namespace
     *
     * @param namespace
     * @return SchemaGrammar associated with the namespace
     */
    public SchemaGrammar getGrammar(String namespace) {
        if (namespace == null)
            return fNoNSGrammar;
        return fGrammarRegistry.get(namespace);
    }

    /**
     * Put a schema grammar into the registry
     * This method is for internal use only: it assumes that a grammar with
     * the same target namespace is not already in the bucket.
     *
     * @param grammar   the grammar to put in the registry
     */
    public void putGrammar(SchemaGrammar grammar) {
        if (grammar.getTargetNamespace() == null)
            fNoNSGrammar = grammar;
        else
            fGrammarRegistry.put(grammar.getTargetNamespace(), grammar);
    }

    /**
     * put a schema grammar and any grammars imported by it (directly or
     * inderectly) into the registry. when a grammar with the same target
     * namespace is already in the bucket, and different from the one being
     * added, it's an error, and no grammar will be added into the bucket.
     *
     * @param grammar   the grammar to put in the registry
     * @param deep      whether to add imported grammars
     * @return          whether the process succeeded
     */
    public boolean putGrammar(SchemaGrammar grammar, boolean deep) {
        // whether there is one with the same tns
        SchemaGrammar sg = getGrammar(grammar.fTargetNamespace);
        if (sg != null) {
            // if the one we have is different from the one passed, it's an error
            return sg == grammar;
        }
        // not deep import, then just add this one grammar
        if (!deep) {
            putGrammar(grammar);
            return true;
        }

        // get all imported grammars, and make a copy of the Vector, so that
        // we can recursively process the grammars, and add distinct ones
        // to the same vector
        ArrayList<SchemaGrammar> currGrammars = (ArrayList<SchemaGrammar>)grammar.getImportedGrammars();
        if (currGrammars == null) {
            putGrammar(grammar);
            return true;
        }

        @SuppressWarnings("unchecked")
        List<SchemaGrammar> grammars = ((ArrayList<SchemaGrammar>)currGrammars.clone());
        SchemaGrammar sg1, sg2;
        List<SchemaGrammar> gs;
        // for all (recursively) imported grammars
        for (int i = 0; i < grammars.size(); i++) {
            // get the grammar
            sg1 = grammars.get(i);
            // check whether the bucket has one with the same tns
            sg2 = getGrammar(sg1.fTargetNamespace);
            if (sg2 == null) {
                // we need to add grammars imported by sg1 too
                gs = sg1.getImportedGrammars();
                // for all grammars imported by sg2, but not in the vector
                // we add them to the vector
                if(gs == null) continue;
                for (int j = gs.size() - 1; j >= 0; j--) {
                    sg2 = gs.get(j);
                    if (!grammars.contains(sg2))
                        grammars.add(sg2);
                }
            }
            // we found one with the same target namespace
            // if the two grammars are not the same object, then it's an error
            else if (sg2 != sg1) {
                return false;
            }
        }

        // now we have all imported grammars stored in the vector. add them
        putGrammar(grammar);
        for (int i = grammars.size() - 1; i >= 0; i--)
            putGrammar(grammars.get(i));

        return true;
    }

    /**
     * put a schema grammar and any grammars imported by it (directly or
     * inderectly) into the registry. when a grammar with the same target
     * namespace is already in the bucket, and different from the one being
     * added, no grammar will be added into the bucket.
     *
     * @param grammar        the grammar to put in the registry
     * @param deep           whether to add imported grammars
     * @param ignoreConflict whether to ignore grammars that already exist in the grammar
     *                       bucket or not - including 'grammar' parameter.
     * @return               whether the process succeeded
     */
    public boolean putGrammar(SchemaGrammar grammar, boolean deep, boolean ignoreConflict) {
        if (!ignoreConflict) {
            return putGrammar(grammar, deep);
        }

        // if grammar already exist in the bucket, we ignore the request
        SchemaGrammar sg = getGrammar(grammar.fTargetNamespace);
        if (sg == null) {
            putGrammar(grammar);
        }

        // not adding the imported grammars
        if (!deep) {
            return true;
        }

        // get all imported grammars, and make a copy of the Vector, so that
        // we can recursively process the grammars, and add distinct ones
        // to the same vector
        ArrayList<SchemaGrammar> currGrammars = (ArrayList<SchemaGrammar>)grammar.getImportedGrammars();
        if (currGrammars == null) {
            return true;
        }

        @SuppressWarnings("unchecked")
        List<SchemaGrammar> grammars = ((ArrayList<SchemaGrammar>)currGrammars.clone());
        SchemaGrammar sg1, sg2;
        List<SchemaGrammar> gs;
        // for all (recursively) imported grammars
        for (int i = 0; i < grammars.size(); i++) {
            // get the grammar
            sg1 = grammars.get(i);
            // check whether the bucket has one with the same tns
            sg2 = getGrammar(sg1.fTargetNamespace);
            if (sg2 == null) {
                // we need to add grammars imported by sg1 too
                gs = sg1.getImportedGrammars();
                // for all grammars imported by sg2, but not in the vector
                // we add them to the vector
                if(gs == null) continue;
                for (int j = gs.size() - 1; j >= 0; j--) {
                    sg2 = gs.get(j);
                    if (!grammars.contains(sg2))
                        grammars.add(sg2);
                }
            }
            // we found one with the same target namespace, ignore it
            else  {
                grammars.remove(sg1);
            }
        }

        // now we have all imported grammars stored in the vector. add them
        for (int i = grammars.size() - 1; i >= 0; i--) {
            putGrammar(grammars.get(i));
        }

        return true;
    }

    /**
     * get all grammars in the registry
     *
     * @return an array of SchemaGrammars.
     */
    public SchemaGrammar[] getGrammars() {
        // get the number of grammars
        int count = fGrammarRegistry.size() + (fNoNSGrammar==null ? 0 : 1);
        SchemaGrammar[] grammars = new SchemaGrammar[count];
        // get grammars with target namespace
        int i = 0;
        for(Map.Entry<String, SchemaGrammar> entry : fGrammarRegistry.entrySet()){
            grammars[i++] = entry.getValue();
        }

        // add the grammar without target namespace, if any
        if (fNoNSGrammar != null)
            grammars[count-1] = fNoNSGrammar;
        return grammars;
    }

    /**
     * Clear the registry.
     * REVISIT: update to use another XSGrammarBucket
     */
    public void reset() {
        fNoNSGrammar = null;
        fGrammarRegistry.clear();
    }

} // class XSGrammarBucket
