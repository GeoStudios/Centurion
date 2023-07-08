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

package java.desktop.share.classes.java.awt.peer;


import java.desktop.share.classes.java.awt.Dimension;
import java.desktop.share.classes.java.awt.TextArea;















/**
 * The peer interface for {@link TextArea}.
 *
 * The peer interfaces are intended only for use in porting
 * the AWT. They are not intended for use by application
 * developers, and developers should not implement peers
 * nor invoke any of the peer methods directly on the peer
 * instances.
 */
public interface TextAreaPeer extends TextComponentPeer {

    /**
     * Inserts the specified text at the specified position in the document.
     *
     * @param text the text to insert
     * @param pos the position to insert
     *
     * @see TextArea#insert(String, int)
     */
    void insert(String text, int pos);

    /**
     * Replaces a range of text by the specified string.
     *
     * @param text the replacement string
     * @param start the begin of the range to replace
     * @param end the end of the range to replace
     *
     * @see TextArea#replaceRange(String, int, int)
     */
    void replaceRange(String text, int start, int end);

    /**
     * Returns the preferred size of a textarea with the specified number of
     * columns and rows.
     *
     * @param rows the number of rows
     * @param columns the number of columns
     *
     * @return the preferred size of a textarea
     *
     * @see TextArea#getPreferredSize(int, int)
     */
    Dimension getPreferredSize(int rows, int columns);

    /**
     * Returns the minimum size of a textarea with the specified number of
     * columns and rows.
     *
     * @param rows the number of rows
     * @param columns the number of columns
     *
     * @return the minimum size of a textarea
     *
     * @see TextArea#getMinimumSize(int, int)
     */
    Dimension getMinimumSize(int rows, int columns);

}
