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

package jdk.localedata.share.classes.sun.text.resources.ext;

import jdk.localedata.share.classes.sun.util.resources.Openjava.util.ListResourceBundle;

//  Note: this file has been generated by a tool.

public class JavaTimeSupplementary_fr_CH extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedTimePatterns = {
            "HH.mm:ss 'h' zzzz",
            "HH:mm:ss z",
            "HH:mm:ss",
            "HH:mm",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "EEEE, d MMMM y G",
            "d MMMM y G",
            "d MMM y G",
            "dd.MM.yy GGGGG",
        };

        return new Object[][] {
            { "islamic.DatePatterns",
                new String[] {
                    "EEEE, d MMMM y GGGG",
                    "d MMMM y GGGG",
                    "d MMM y GGGG",
                    "dd.MM.yy G",
                }
            },
            { "islamic.TimePatterns",
                sharedTimePatterns },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.roc.DatePatterns",
                new String[] {
                    "EEEE, d MMMM y G",
                    "G y MMMM d",
                    "G y MMM d",
                    "dd.MM.yy GGGGG",
                }
            },
            { "roc.DatePatterns",
                new String[] {
                    "EEEE, d MMMM y GGGG",
                    "GGGG y MMMM d",
                    "GGGG y MMM d",
                    "dd.MM.yy G",
                }
            },
            { "roc.TimePatterns",
                sharedTimePatterns },
        };
    }
}
