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

package java.desktop.windows.classes.sun.awt.windows;

import java.nio.charset.*;
import java.desktop.windows.classes.sun.awt.AWTCharset;

final class WDefaultFontCharset extends AWTCharset
{
    static {
       initIDs();
    }

    // Name for Windows FontSet.
    private final String fontName;

    WDefaultFontCharset(String name){
        super("WDefaultFontCharset", Charset.forName("windows-1252"));
        fontName = name;
    }

    @Override
    public CharsetEncoder newEncoder() {
        return new Encoder();
    }

    private class Encoder extends AWTCharset.Encoder {
        @Override
        public boolean canEncode(char c){
            return canConvert(c);
        }
    }

    private synchronized native boolean canConvert(char ch);

    /**
     * Initialize JNI field and method IDs
     */
    private static native void initIDs();
}
