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




public class JavaTimeSupplementary_en_NZ extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedDatePatterns = {
            "GGGG y MMMM d, EEEE",
            "GGGG y MMMM d",
            "d/MM/y GGGG",
            "d/MM/y G",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "G y MMMM d, EEEE",
            "G y MMMM d",
            "d/MM/y G",
            "d/MM/y GGGGG",
        };

        return new Object[][] {
            { "islamic.DatePatterns",
                sharedDatePatterns },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.japanese.DatePatterns",
                new String[] {
                    "EEEE, MMMM d, y G",
                    "MMMM d, y G",
                    "d/MM/y G",
                    "d/MM/y GGGGG",
                }
            },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "roc.DatePatterns",
                sharedDatePatterns },
        };
    }
}
