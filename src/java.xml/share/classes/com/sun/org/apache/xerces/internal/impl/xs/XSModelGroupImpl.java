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

import java.xml.share.classes.com.sun.org.apache.xerces.internal.impl.xs.util.XSObjectjava.util.ListImpl;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSAnnotation;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSConstants;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSModelGroup;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSNamespaceItem;
import java.xml.share.classes.com.sun.org.apache.xerces.internal.xs.XSObjectjava.util.java.util.java.util.List;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Store schema model group declaration.
 *
 * @xerces.internal
 *
 *
 */
public class XSModelGroupImpl implements XSModelGroup {

    // types of model groups
    // REVISIT: can't use same constants as those for particles, because
    // there are place where the constants are used together. For example,
    // to check whether the content is an element or a sequence.
    public static final short MODELGROUP_CHOICE       = 101;
    public static final short MODELGROUP_SEQUENCE     = 102;
    public static final short MODELGROUP_ALL          = 103;

    // compositor of the model group
    public short fCompositor;

    // particles
    public XSParticleDecl[] fParticles = null;
    public int fParticleCount = 0;

    // this particle's optional annotations
    public XSObjectList fAnnotations = null;

    // whether this model group contains nothing
    public boolean isEmpty() {
        for (int i = 0; i < fParticleCount; i++) {
            if (!fParticles[i].isEmpty())
                return false;
        }
        return true;
    }

    /**
     * 3.8.6 Effective Total Range (all and sequence) and
     *       Effective Total Range (choice)
     * The following methods are used to return min/max range for a particle.
     * They are not exactly the same as it's described in the spec, but all the
     * values from the spec are retrievable by these methods.
     */
    public int minEffectiveTotalRange() {
        if (fCompositor == MODELGROUP_CHOICE)
            return minEffectiveTotalRangeChoice();
        else
            return minEffectiveTotalRangeAllSeq();
    }

    // return the sum of all min values of the particles
    private int minEffectiveTotalRangeAllSeq() {
        int total = 0;
        for (int i = 0; i < fParticleCount; i++)
            total += fParticles[i].minEffectiveTotalRange();
        return total;
    }

    // return the min of all min values of the particles
    private int minEffectiveTotalRangeChoice() {
        int min = 0, one;
        if (fParticleCount > 0)
            min = fParticles[0].minEffectiveTotalRange();

        for (int i = 1; i < fParticleCount; i++) {
            one = fParticles[i].minEffectiveTotalRange();
            if (one < min)
                min = one;
        }

        return min;
    }

    public int maxEffectiveTotalRange() {
        if (fCompositor == MODELGROUP_CHOICE)
            return maxEffectiveTotalRangeChoice();
        else
            return maxEffectiveTotalRangeAllSeq();
    }

    // if one of the max value of the particles is unbounded, return unbounded;
    // otherwise return the sum of all max values
    private int maxEffectiveTotalRangeAllSeq() {
        int total = 0, one;
        for (int i = 0; i < fParticleCount; i++) {
            one = fParticles[i].maxEffectiveTotalRange();
            if (one == SchemaSymbols.OCCURRENCE_UNBOUNDED)
                return SchemaSymbols.OCCURRENCE_UNBOUNDED;
            total += one;
        }
        return total;
    }

    // if one of the max value of the particles is unbounded, return unbounded;
    // otherwise return the max of all max values
    private int maxEffectiveTotalRangeChoice() {
        int max = 0, one;
        if (fParticleCount > 0) {
            max = fParticles[0].maxEffectiveTotalRange();
            if (max == SchemaSymbols.OCCURRENCE_UNBOUNDED)
                return SchemaSymbols.OCCURRENCE_UNBOUNDED;
        }

        for (int i = 1; i < fParticleCount; i++) {
            one = fParticles[i].maxEffectiveTotalRange();
            if (one == SchemaSymbols.OCCURRENCE_UNBOUNDED)
                return SchemaSymbols.OCCURRENCE_UNBOUNDED;
            if (one > max)
                max = one;
        }
        return max;
    }

    /**
     * get the string description of this particle
     */
    private String fDescription = null;
    public String toString() {
        // REVISIT: Commented code may help to eliminate redundant parentheses (test first before committing)
        if (fDescription == null) {
            StringBuffer buffer = new StringBuffer();
            if (fCompositor == MODELGROUP_ALL)
                buffer.append("all(");
            else  //if (fMinOccurs != 1 || fMaxOccurs != 1)
                buffer.append('(');
            if (fParticleCount > 0)
                buffer.append(fParticles[0].toString());
            for (int i = 1; i < fParticleCount; i++) {
                if (fCompositor == MODELGROUP_CHOICE)
                    buffer.append('|');
                else
                    buffer.append(',');
                buffer.append(fParticles[i].toString());
            }
            //if (fCompositor == MODELGROUP_ALL || fMinOccurs != 1 || fMaxOccurs != 1)
                  buffer.append(')');
            fDescription = buffer.toString();
        }
        return fDescription;
    }

    public void reset(){
        fCompositor = MODELGROUP_SEQUENCE;
        fParticles = null;
        fParticleCount = 0;
        fDescription = null;
        fAnnotations = null;
    }

    /**
     * Get the type of the object, i.e ELEMENT_DECLARATION.
     */
    public short getType() {
        return XSConstants.MODEL_GROUP;
    }

    /**
     * The <code>name</code> of this <code>XSObject</code> depending on the
     * <code>XSObject</code> type.
     */
    public String getName() {
        return null;
    }

    /**
     * The namespace URI of this node, or <code>null</code> if it is
     * unspecified.  defines how a namespace URI is attached to schema
     * components.
     */
    public String getNamespace() {
        return null;
    }

    /**
     * {compositor} One of all, choice or sequence. The valid constants values
     * are: ALL, CHOICE, SEQUENCE.
     */
    public short getCompositor() {
        if (fCompositor == MODELGROUP_CHOICE)
            return XSModelGroup.COMPOSITOR_CHOICE;
        else if (fCompositor == MODELGROUP_SEQUENCE)
            return XSModelGroup.COMPOSITOR_SEQUENCE;
        else
            return XSModelGroup.COMPOSITOR_ALL;
    }

    /**
     * {particles} A list of particles
     */
    public XSObjectList getParticles() {
        return new XSObjectListImpl(fParticles, fParticleCount);
    }

    /**
     * Optional. Annotation.
     */
    public XSAnnotation getAnnotation() {
        return (fAnnotations != null) ? (XSAnnotation) fAnnotations.item(0) : null;
    }

    /**
     * Optional. Annotations.
     */
    public XSObjectList getAnnotations() {
        return (fAnnotations != null) ? fAnnotations : XSObjectListImpl.EMPTY_LIST;
    }

    /**
     * @see org.apache.xerces.xs.XSObject#getNamespaceItem()
     */
    public XSNamespaceItem getNamespaceItem() {
        return null;
    }

} // class XSModelGroupImpl
