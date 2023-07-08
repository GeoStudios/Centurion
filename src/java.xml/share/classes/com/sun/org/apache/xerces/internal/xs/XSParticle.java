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

package java.xml.share.classes.com.sun.org.apache.xerces.internal.xs;

















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */


/**
 * This interface represents the Particle schema component.
 */
public interface XSParticle extends XSObject {
    /**
     * [min occurs]: determines the minimum number of terms that can occur.
     */
    int getMinOccurs();

    /**
     *  [max occurs]: determines the maximum number of terms that can occur.
     * To query for the value of unbounded use
     * <code>maxOccursUnbounded</code>. When the value of
     * <code>maxOccursUnbounded</code> is <code>true</code>, the value of
     * <code>maxOccurs</code> is unspecified.
     */
    int getMaxOccurs();

    /**
     * [max occurs]: whether the maxOccurs value is unbounded.
     */
    boolean getMaxOccursUnbounded();

    /**
     * [term]: one of a model group, a wildcard, or an element declaration.
     */
    XSTerm getTerm();

    /**
     * A sequence of [annotations] or an empty <code>XSObjectList</code>.
     */
    XSObjectList getAnnotations();
}
