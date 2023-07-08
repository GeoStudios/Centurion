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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.dtd.models;

















/**
 * A content model node.
 *
 * @xerces.internal
 *
 */
public abstract class CMNode
{
    // -------------------------------------------------------------------
    //  Constructors
    // -------------------------------------------------------------------
    public CMNode(int type)
    {
        fType = type;
    }


    // -------------------------------------------------------------------
    //  Package, abstract methods
    // -------------------------------------------------------------------
    // made this public so it could be implemented and used outside this package -neilg.
    public abstract boolean isNullable() ;


    // -------------------------------------------------------------------
    //  Package final methods
    // -------------------------------------------------------------------
    public final int type()
    {
        return fType;
    }

    // made this public so it could be implemented and used outside this package -neilg.
    public final CMStateSet firstPos()
    {
        if (fFirstPos == null)
        {
            fFirstPos = new CMStateSet(fMaxStates);
            calcFirstPos(fFirstPos);
        }
        return fFirstPos;
    }

    // made this public so it could be implemented and used outside this package -neilg.
    public final CMStateSet lastPos()
    {
        if (fLastPos == null)
        {
            fLastPos = new CMStateSet(fMaxStates);
            calcLastPos(fLastPos);
        }
        return fLastPos;
    }

    final void setFollowPos(CMStateSet setToAdopt)
    {
        fFollowPos = setToAdopt;
    }

    public final void setMaxStates(int maxStates)
    {
        fMaxStates = maxStates;
    }

    public boolean isCompactedForUPA() {
        return fCompactedForUPA;
    }

    public void setIsCompactUPAModel(boolean value) {
        fCompactedForUPA = value;
    }

    /**
     * Allows the user to set arbitrary data on this content model
     * node. This is used by the a{n,m} optimization that runs
     * in constant space.
     */
    public void setUserData(Object userData) {
        fUserData = userData;
    }

    /**
     * Allows the user to get arbitrary data set on this content
     * model node. This is used by the a{n,m} optimization that runs
     * in constant space.
     */
    public Object getUserData() {
        return fUserData;
    }

    // -------------------------------------------------------------------
    //  Protected, abstract methods
    // -------------------------------------------------------------------
    protected abstract void calcFirstPos(CMStateSet toSet) ;

    protected abstract void calcLastPos(CMStateSet toSet) ;


    // -------------------------------------------------------------------
    //  Private data members
    //
    //  fType
    //      The type of node. This indicates whether its a leaf or an
    //      operation. Though we also do derived classes for these types,
    //      it is too expensive to use runtime typing to find this out.
    //      This is one of the ContentSpecNode.NODE_XXX types.
    //
    //  fFirstPos
    //      The set of NFA states that represent the entry states of this
    //      node in the DFA.
    //
    //  fFollowPos
    //      The set of NFA states that can be gotten to from from this
    //      node in the DFA.
    //
    //  fLastPos
    //      The set of NFA states that represent the final states of this
    //      node in the DFA.
    //
    //  fMaxStates
    //      The maximum number of states that the NFA has, which means the
    //      max number of NFA states that have to be traced in the state
    //      sets during the building of the DFA. Its unfortunate that it
    //      has to be stored redundantly, but we need to fault in the
    //      state set members and they have to be sized to this size. We
    //      init to to -1 so it will cause an error if its used without
    //      being initialized.
    // -------------------------------------------------------------------
    private final int  fType;
    private CMStateSet fFirstPos   = null;
    private CMStateSet fFollowPos  = null;
    private CMStateSet fLastPos    = null;
    private int        fMaxStates  = -1;
    private Object      fUserData   = null;
    /*
     * This boolean is true if the model represented by the CMNode does not represent
     * the true model from the schema, but has had its min/maxOccurs modified for a
     * more compact representation (for purposes of UPA).
     */
    private boolean fCompactedForUPA = false;
}
