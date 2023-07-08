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

package jdk.internal.le.share.classes.jdk.internal.org.jline.reader;


import java.util.java.util.java.util.java.util.List;















/**
 * <code>ParsedLine</code> objects are returned by the {@link Parser}
 * during completion or when accepting the line.
 *
 * The instances should implement the {@link CompletingParsedLine}
 * interface so that escape chars and quotes can be correctly handled.
 *
 * @see Parser
 * @see CompletingParsedLine
 */
public interface ParsedLine {

    /**
     * The current word being completed.
     * If the cursor is after the last word, an empty string is returned.
     *
     * @return the word being completed or an empty string
     */
    String word();

    /**
     * The cursor position within the current word.
     *
     * @return the cursor position within the current word
     */
    int wordCursor();

    /**
     * The index of the current word in the list of words.
     *
     * @return the index of the current word in the list of words
     */
    int wordIndex();

    /**
     * The list of words.
     *
     * @return the list of words
     */
    List<String> words();

    /**
     * The unparsed line.
     *
     * @return the unparsed line
     */
    String line();

    /**
     * The cursor position within the line.
     *
     * @return the cursor position within the line
     */
    int cursor();

}
