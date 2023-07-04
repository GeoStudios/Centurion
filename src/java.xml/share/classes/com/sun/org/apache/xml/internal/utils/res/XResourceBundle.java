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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xml.internal.utils.res;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * The default (english) resource bundle.
 *
 * @xsl.usage internal
 */
public class XResourceBundle extends ListResourceBundle {

    /**
     * Error resource constants
     */
    public static final String ERROR_RESOURCES =
            "com.sun.org.apache.xalan.internal.res.XSLTErrorResources", XSLT_RESOURCE =
            "com.sun.org.apache.xml.internal.utils.res.XResourceBundle", LANG_BUNDLE_NAME =
            "com.sun.org.apache.xml.internal.utils.res.XResources", MULT_ORDER =
            "multiplierOrder", MULT_PRECEDES = "precedes", MULT_FOLLOWS =
            "follows", LANG_ORIENTATION = "orientation", LANG_RIGHTTOLEFT =
            "rightToLeft", LANG_LEFTTORIGHT = "leftToRight", LANG_NUMBERING =
            "numbering", LANG_ADDITIVE = "additive", LANG_MULT_ADD =
            "multiplicative-additive", LANG_MULTIPLIER =
            "multiplier", LANG_MULTIPLIER_CHAR =
            "multiplierChar", LANG_NUMBERGROUPS = "numberGroups", LANG_NUM_TABLES =
            "tables", LANG_ALPHABET = "alphabet", LANG_TRAD_ALPHABET = "tradAlphabet";


    /**
     * Get the association list.
     *
     * @return The association list.
     */
    public Object[][] getContents() {
        return new Object[][]{
                    {"ui_language", "en"}, {"help_language", "en"}, {"language", "en"},
                    {"alphabet", new CharArrayWrapper(new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G',
                            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                            'V', 'W', 'X', 'Y', 'Z'})},
                    {"tradAlphabet", new CharArrayWrapper(new char[]{'A', 'B', 'C', 'D', 'E', 'F',
                            'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                            'U', 'V', 'W', 'X', 'Y', 'Z'})},
                    //language orientation
                    {"orientation", "LeftToRight"},
                    //language numbering
                    {"numbering", "additive"},};
    }
}
