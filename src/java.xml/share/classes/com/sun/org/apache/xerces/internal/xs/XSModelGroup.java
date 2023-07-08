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
 * This interface represents the Model Group schema component.
 */
public interface XSModelGroup extends XSTerm {
    // Content model compositors
    /**
     * This constant value signifies a sequence operator.
     */
    short COMPOSITOR_SEQUENCE       = 1;
    /**
     * This constant value signifies a choice operator.
     */
    short COMPOSITOR_CHOICE         = 2;
    /**
     * This content model represents a simplified version of the SGML
     * &amp;-Connector and is limited to the top-level of any content model.
     * No element in the all content model may appear more than once.
     */
    short COMPOSITOR_ALL            = 3;

    /**
     * [compositor]: one of all, choice or sequence. The valid constant values
     * are:
     * <code>COMPOSITOR_SEQUENCE, COMPOSITOR_CHOICE, COMPOSITOR_ALL</code>.
     */
    short getCompositor();

    /**
     *  A list of [particles] if it exists, otherwise an empty
     * <code>XSObjectList</code>.
     */
    XSObjectList getParticles();

    /**
     * An annotation if it exists, otherwise <code>null</code>. If not null
     * then the first [annotation] from the sequence of annotations.
     */
    XSAnnotation getAnnotation();

    /**
     * A sequence of [annotations] or an empty <code>XSObjectList</code>.
     */
    XSObjectList getAnnotations();
}
