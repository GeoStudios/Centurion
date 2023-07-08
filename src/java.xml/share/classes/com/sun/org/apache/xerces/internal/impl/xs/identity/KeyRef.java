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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.identity;


import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSIDCDefinition;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * Schema key reference identity constraint.
 *
 * @xerces.internal
 *
 */
public class KeyRef
    extends IdentityConstraint {

    //
    // Data
    //

    /** The key (or unique) being referred to. */
    protected UniqueOrKey fKey;

    //
    // Constructors
    //

    /** Constructs a keyref with the specified name. */
    public KeyRef(String namespace, String identityConstraintName,
                  String elemName, UniqueOrKey key) {
        super(namespace, identityConstraintName, elemName);
        fKey = key;
        type = IC_KEYREF;
    } // <init>(String,String,String)

    //
    // Public methods
    //

    /** Returns the key being referred to.  */
    public UniqueOrKey getKey() {
        return fKey;
    } // getKey(): int

    /**
     * {referenced key} Required if {identity-constraint category} is keyref,
     * forbidden otherwise. An identity-constraint definition with
     * {identity-constraint category} equal to key or unique.
     */
    public XSIDCDefinition getRefKey() {
        return fKey;
    }

} // class KeyRef
