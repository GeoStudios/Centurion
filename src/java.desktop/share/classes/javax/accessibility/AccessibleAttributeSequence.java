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

package java.desktop.share.classes.javax.accessibility;


import java.desktop.share.classes.javax.swing.text.AttributeSet;















/**
 * This class collects together the span of text that share the same contiguous
 * set of attributes, along with that set of attributes. It is used by
 * implementors of the class {@code AccessibleContext} in order to generate
 * {@code ACCESSIBLE_TEXT_ATTRIBUTES_CHANGED} events.
 *
 * @see AccessibleContext
 * @see AccessibleContext#ACCESSIBLE_TEXT_ATTRIBUTES_CHANGED
 */
public class AccessibleAttributeSequence {

    /**
     * The start index of the text sequence.
     */
    public int startIndex;

    /**
     * The end index of the text sequence.
     */
    public int endIndex;

    /**
     * The text attributes.
     */
    public AttributeSet attributes;

    /**
     * Constructs an {@code AccessibleAttributeSequence} with the given
     * parameters.
     *
     * @param  start the beginning index of the span of text
     * @param  end the ending index of the span of text
     * @param  attr the {@code AttributeSet} shared by this text span
     */
    public AccessibleAttributeSequence(int start, int end, AttributeSet attr) {
        startIndex = start;
        endIndex = end;
        attributes = attr;
    }
}
