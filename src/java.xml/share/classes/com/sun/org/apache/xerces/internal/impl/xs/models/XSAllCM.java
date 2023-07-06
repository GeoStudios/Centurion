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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.models;


import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.SubstitutionGroupHandler;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.XMLSchemaException;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.XSConstraints;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.XSElementDecl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.QName;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;















/**
 * XSAllCM implements XSCMValidator and handles &lt;all&gt;.
 *
 * @xerces.internal
 *
 * @LastModified: Oct 2017
 */
public class XSAllCM implements XSCMValidator {

    //
    // Constants
    //

    // start the content model: did not see any children
    private static final short STATE_START = 0;
    private static final short STATE_VALID = 1;
    private static final short STATE_CHILD = 1;


    //
    // Data
    //

    private final XSElementDecl[] fAllElements;
    private final boolean[] fIsOptionalElement;
    private boolean fHasOptionalContent = false;
    private int fNumElements = 0;

    //
    // Constructors
    //

    public XSAllCM (boolean hasOptionalContent, int size) {
        fHasOptionalContent = hasOptionalContent;
        fAllElements = new XSElementDecl[size];
        fIsOptionalElement = new boolean[size];
    }

    public void addElement (XSElementDecl element, boolean isOptional) {
        fAllElements[fNumElements] = element;
        fIsOptionalElement[fNumElements] = isOptional;
        fNumElements++;
    }


    //
    // XSCMValidator methods
    //

    /**
     * This methods to be called on entering a first element whose type
     * has this content model. It will return the initial state of the
     * content model
     *
     * @return Start state of the content model
     */
    public int[] startContentModel() {

        int[] state = new int[fNumElements + 1];

        for (int i = 0; i <= fNumElements; i++) {
            state[i] = STATE_START;
        }
        return state;
    }

    // convinient method: when error occurs, to find a matching decl
    // from the candidate elements.
    Object findMatchingDecl(QName elementName, SubstitutionGroupHandler subGroupHandler) {
        Object matchingDecl = null;
        for (int i = 0; i < fNumElements; i++) {
            matchingDecl = subGroupHandler.getMatchingElemDecl(elementName, fAllElements[i]);
            if (matchingDecl != null)
                break;
        }
        return matchingDecl;
    }

    /**
     * The method corresponds to one transition in the content model.
     *
     * @param elementName
     * @param currentState  Current state
     * @return an element decl object
     */
    public Object oneTransition (QName elementName, int[] currentState, SubstitutionGroupHandler subGroupHandler) {

        // error state
        if (currentState[0] < 0) {
            currentState[0] = XSCMValidator.SUBSEQUENT_ERROR;
            return findMatchingDecl(elementName, subGroupHandler);
        }

        // seen child
        currentState[0] = STATE_CHILD;

        Object matchingDecl = null;

        for (int i = 0; i < fNumElements; i++) {
            // we only try to look for a matching decl if we have not seen
            // this element yet.
            if (currentState[i+1] != STATE_START)
                continue;
            matchingDecl = subGroupHandler.getMatchingElemDecl(elementName, fAllElements[i]);
            if (matchingDecl != null) {
                // found the decl, mark this element as "seen".
                currentState[i+1] = STATE_VALID;
                return matchingDecl;
            }
        }

        // couldn't find the decl, change to error state.
        currentState[0] = XSCMValidator.FIRST_ERROR;
        return findMatchingDecl(elementName, subGroupHandler);
    }


    /**
     * The method indicates the end of list of children
     *
     * @param currentState  Current state of the content model
     * @return true if the last state was a valid final state
     */
    public boolean endContentModel (int[] currentState) {

        int state = currentState[0];

        if (state == XSCMValidator.FIRST_ERROR || state == XSCMValidator.SUBSEQUENT_ERROR) {
            return false;
        }

        // If <all> has minOccurs of zero and there are
        // no children to validate, it is trivially valid
        if (fHasOptionalContent && state == STATE_START) {
            return true;
        }

        for (int i = 0; i < fNumElements; i++) {
            // if one element is required, but not present, then error
            if (!fIsOptionalElement[i] && currentState[i+1] == STATE_START)
                return false;
        }

        return true;
    }

    /**
     * check whether this content violates UPA constraint.
     *
     * @param subGroupHandler the substitution group handler
     * @return true if this content model contains other or list wildcard
     */
    public boolean checkUniqueParticleAttribution(SubstitutionGroupHandler subGroupHandler) throws XMLSchemaException {
        // check whether there is conflict between any two leaves
        for (int i = 0; i < fNumElements; i++) {
            for (int j = i+1; j < fNumElements; j++) {
                if (XSConstraints.overlapUPA(fAllElements[i], fAllElements[j], subGroupHandler)) {
                    // REVISIT: do we want to report all errors? or just one?
                    throw new XMLSchemaException("cos-nonambig", new Object[]{fAllElements[i].toString(),
                                                                              fAllElements[j].toString()});
                }
            }
        }

        return false;
    }

    /**
     * Check which elements are valid to appear at this point. This method also
     * works if the state is in error, in which case it returns what should
     * have been seen.
     *
     * @param state  the current state
     * @return       a list whose entries are instances of
     *               either XSWildcardDecl or XSElementDecl.
     */
    public List<Object> whatCanGoHere(int[] state) {
        List<Object> ret = new ArrayList<>();
        for (int i = 0; i < fNumElements; i++) {
            // we only try to look for a matching decl if we have not seen
            // this element yet.
            if (state[i+1] == STATE_START) {
                ret.add(fAllElements[i]);
            }
        }
        return ret;
    }

    public List<String> checkMinMaxBounds() {
        return null;
    }

    public int [] occurenceInfo(int[] state) {
        return null;
    }

    public String getTermName(int termId) {
        return null;
    }

    public boolean isCompactedForUPA() {
        return false;
    }
} // class XSAllCM
