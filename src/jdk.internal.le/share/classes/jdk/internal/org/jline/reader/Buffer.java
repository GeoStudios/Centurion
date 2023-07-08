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

public interface Buffer {

    /*
     * Read access
     */

    int cursor();

    int atChar(int i);

    int length();

    int currChar();

    int prevChar();

    int nextChar();

    /*
     * Movement
     */

    boolean cursor(int position);

    int move(int num);

    boolean up();

    boolean down();

    boolean moveXY(int dx, int dy);

    /*
     * Modification
     */

    boolean clear();

    boolean currChar(int c);

    void write(int c);

    void write(int c, boolean overTyping);

    void write(CharSequence str);

    void write(CharSequence str, boolean overTyping);

    boolean backspace();

    int backspace(int num);

    boolean delete();

    int delete(int num);

    /*
     * String
     */

    String substring(int start);

    String substring(int start, int end);

    String upToCursor();

    String toString();

    /*
     * Copy
     */

    Buffer copy();

    void copyFrom(Buffer buffer);

}
