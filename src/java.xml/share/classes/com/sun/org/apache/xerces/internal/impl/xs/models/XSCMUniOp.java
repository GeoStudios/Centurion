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

import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dtd.models.CMNode;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dtd.models.CMStateSet;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.XSParticleDecl;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 *
 * Content model Uni-Op node.
 *
 * @xerces.internal
 *
 * @version $$
 */
public class XSCMUniOp extends CMNode {
    // -------------------------------------------------------------------
    //  Constructors
    // -------------------------------------------------------------------
    public XSCMUniOp(int type, CMNode childNode) {
        super(type);

        // Insure that its one of the types we require
        if ((type() != XSParticleDecl.PARTICLE_ZERO_OR_ONE)
        &&  (type() != XSParticleDecl.PARTICLE_ZERO_OR_MORE)
        &&  (type() != XSParticleDecl.PARTICLE_ONE_OR_MORE)) {
            throw new RuntimeException("ImplementationMessages.VAL_UST");
        }

        // Store the node and init any data that needs it
        fChild = childNode;
    }

    // -------------------------------------------------------------------
    //  Package, final methods
    // -------------------------------------------------------------------
    final CMNode getChild() {
        return fChild;
    }

    // -------------------------------------------------------------------
    //  Package, inherited methods
    // -------------------------------------------------------------------
    public boolean isNullable() {
        //
        //  For debugging purposes, make sure we got rid of all non '*'
        //  repetitions. Otherwise, '*' style nodes are always nullable.
        //
        if (type() == XSParticleDecl.PARTICLE_ONE_OR_MORE)
                return fChild.isNullable();
            else
                return true;
    }

    // -------------------------------------------------------------------
    //  Protected, inherited methods
    // -------------------------------------------------------------------
    protected void calcFirstPos(CMStateSet toSet) {
        // Its just based on our child node's first pos
        toSet.setTo(fChild.firstPos());
    }

    protected void calcLastPos(CMStateSet toSet) {
        // Its just based on our child node's last pos
        toSet.setTo(fChild.lastPos());
    }

    /**
     * Allows the user to set arbitrary data on this content model
     * node. This is used by the a{n,m} optimization that runs
     * in constant space. For convenience, set user data in
     * children node too.
     */
    @Override
    public void setUserData(Object userData) {
        super.setUserData(userData);
        fChild.setUserData(userData);
    }

    // -------------------------------------------------------------------
    //  Private data members
    //
    //  fChild
    //      This is the reference to the one child that we have for this
    //      unary operation.
    // -------------------------------------------------------------------
    private final CMNode  fChild;
} // XSCMUniOp
