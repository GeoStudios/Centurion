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
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xni.QName;
import java.util.java.util.java.util.java.util.List;

/**
 * Note: State of the content model is stored in the validator
 *
 * @xerces.internal
 *
 * @LastModified: Oct 2017
 */
public interface XSCMValidator {

    short FIRST_ERROR = -1;

    // on subsequent errors the validator should not report
    // an error
    //
    short SUBSEQUENT_ERROR = -2;

    /**
     * This methods to be called on entering a first element whose type
     * has this content model. It will return the initial state of the content model
     *
     * @return Start state of the content model
     */
    int[] startContentModel();

    /**
     * The method corresponds to one transaction in the content model.
     *
     * @param elementName
     * @param state  Current state
     * @return element decl or wildcard decl that
     *         corresponds to the element from the Schema grammar
     */
    Object oneTransition (QName elementName, int[] state, SubstitutionGroupHandler subGroupHandler);

    /**
     * The method indicates the end of list of children
     *
     * @param state  Current state of the content model
     * @return true if the last state was a valid final state
     */
    boolean endContentModel (int[] state);

    /**
     * check whether this content violates UPA constraint.
     *
     * @param subGroupHandler the substitution group handler
     * @return true if this content model contains other or list wildcard
     */
    boolean checkUniqueParticleAttribution(SubstitutionGroupHandler subGroupHandler) throws XMLSchemaException;

    /**
     * Check which elements are valid to appear at this point. This method also
     * works if the state is in error, in which case it returns what should
     * have been seen.
     *
     * @param state  the current state
     * @return       a list whose entries are instances of
     *               either XSWildcardDecl or XSElementDecl.
     */
    List<Object> whatCanGoHere(int[] state);

    /**
     * Used by constant space algorithm for a{n,m} for n > 1 and
     * m <= unbounded. Called by a validator if validation of
     * countent model succeeds after subsuming a{n,m} to a*
     * (or a+) to check the n and m bounds.
     *
     * @return <code>null</code> if validation of bounds is
     * successful. Returns a list of strings with error info
     * if not. Even entries in list returned are error codes
     * (used to look up properties) and odd entries are parameters
     * to be passed when formatting error message. Each parameter
     * is associated with the error code that proceeds it in
     * the list.
     */
    List<String> checkMinMaxBounds();

     /**
     * <p>Returns an array containing information about the current repeating term
     * or <code>null</code> if no occurrence counting was being performed at the
     * current state.</p>
     *
     * <p>If an array is returned it will have a length == 4 and will contain:
     *  <ul>
     *   <li>a[0] :: min occurs</li>
     *   <li>a[1] :: max occurs</li>
     *   <li>a[2] :: current value of the counter</li>
     *   <li>a[3] :: identifier for the repeating term</li>
     *  </ul>
     * </p>
     *
     * @param state the current state
     * @return an array containing information about the current repeating term
     */
     int [] occurenceInfo(int[] state);

    /**
     * Returns the name of the term (element or wildcard) for the given identifier.
     *
     * @param termId identifier for the element declaration or wildcard
     * @return the name of the element declaration or wildcard
     */
    String getTermName(int termId);

    /**
     * Checks if this content model has had its min/maxOccurs values reduced for
     * purposes of speeding up UPA.  If so, this content model should not be used
     * for any purpose other than checking unique particle attribution
     *
     * @return a boolean that says whether this content has been compacted for UPA
     */
    boolean isCompactedForUPA();
} // XSCMValidator
