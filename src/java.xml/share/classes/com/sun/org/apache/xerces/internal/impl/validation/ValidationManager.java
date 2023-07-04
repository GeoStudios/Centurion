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

package com.sun.org.apache.xerces.internal.impl.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * ValidationManager is a coordinator property for validators in the
 * pipeline. Each validator must know how to interact with
 * this property. Validators are not required to know what kind of
 * other validators present in the pipeline, but should understand
 * that there are others and that some coordination is required.
 *
 * @xerces.internal
 *
 * @LastModified: Oct 2017
 */
public class ValidationManager {

    protected final List<ValidationState> fVSs = new ArrayList<>();
    protected boolean fGrammarFound = false;

    // used by the DTD validator to tell other components that it has a
    // cached DTD in hand so there's no reason to
    // scan external subset or entity decls.
    protected boolean fCachedDTD = false;

    /**
     * Each validator should call this method to add its ValidationState into
     * the validation manager.
     */
    public final void addValidationState(ValidationState vs) {
        fVSs.add(vs);
    }

    /**
     * Set the information required to validate entity values.
     */
    public final void setEntityState(EntityState state) {
        for (int i = fVSs.size()-1; i >= 0; i--) {
            (fVSs.get(i)).setEntityState(state);
        }
    }

    public final void setGrammarFound(boolean grammar){
        fGrammarFound = grammar;
    }

    public final boolean isGrammarFound(){
        return fGrammarFound;
    }

    public final void setCachedDTD(boolean cachedDTD) {
        fCachedDTD = cachedDTD;
    } // setCachedDTD(boolean)

    public final boolean isCachedDTD() {
        return fCachedDTD;
    } // isCachedDTD():  boolean


    public final void reset (){
        fVSs.clear();
        fGrammarFound = false;
        fCachedDTD = false;
    }
}
