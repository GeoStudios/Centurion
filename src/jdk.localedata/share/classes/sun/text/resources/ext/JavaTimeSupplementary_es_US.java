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




public class JavaTimeSupplementary_es_US extends OpenListResourceBundle {
    @Override
    protected final Object[][] getContents() {
        final String[] sharedAmPmMarkers = {
            "AM",
            "PM",
        };

        final String[] sharedDatePatterns = {
            "GGGG y MMMM d, EEEE",
            "GGGG y MMMM d",
            "GGGG y MMM d",
            "dd/MM/yy G",
        };

        final String[] sharedDayNarrows = {
            "d",
            "l",
            "m",
            "m",
            "j",
            "v",
            "s",
        };

        final String[] sharedTimePatterns = {
            "h:mm:ss a zzzz",
            "h:mm:ss a z",
            "h:mm:ss a",
            "h:mm a",
        };

        final String[] sharedAbbreviatedAmPmMarkers = {
            "a.m.",
            "p.m.",
        };

        final String[] sharedNarrowAmPmMarkers = {
            "a",
            "p",
        };

        final String[] sharedJavaTimeDatePatterns = {
            "G y MMMM d, EEEE",
            "G y MMMM d",
            "G y MMM d",
            "dd/MM/yy GGGGG",
        };

        return new Object[][] {
            { "field.dayperiod",
                "a.m./p.m." },
            { "field.zone",
                "Zona horaria" },
            { "islamic.AmPmMarkers",
                sharedAmPmMarkers },
            { "islamic.DatePatterns",
                sharedDatePatterns },
            { "islamic.DayNarrows",
                sharedDayNarrows },
            { "islamic.TimePatterns",
                sharedTimePatterns },
            { "islamic.abbreviated.AmPmMarkers",
                sharedAbbreviatedAmPmMarkers },
            { "islamic.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
            { "java.time.buddhist.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.islamic.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.roc.DatePatterns",
                sharedJavaTimeDatePatterns },
            { "java.time.short.Eras",
                new String[] {
                    "a.C.",
                    "d.C.",
                }
            },
            { "roc.AmPmMarkers",
                sharedAmPmMarkers },
            { "roc.DatePatterns",
                sharedDatePatterns },
            { "roc.DayNarrows",
                sharedDayNarrows },
            { "roc.MonthAbbreviations",
                new String[] {
                    "ene.",
                    "feb.",
                    "mar.",
                    "abr.",
                    "may.",
                    "jun.",
                    "jul.",
                    "ago.",
                    "sep.",
                    "oct.",
                    "nov.",
                    "dic.",
                    "",
                }
            },
            { "roc.MonthNarrows",
                new String[] {
                    "e",
                    "f",
                    "m",
                    "a",
                    "m",
                    "j",
                    "j",
                    "a",
                    "s",
                    "o",
                    "n",
                    "d",
                    "",
                }
            },
            { "roc.TimePatterns",
                sharedTimePatterns },
            { "roc.abbreviated.AmPmMarkers",
                sharedAbbreviatedAmPmMarkers },
            { "roc.narrow.AmPmMarkers",
                sharedNarrowAmPmMarkers },
        };
    }
}
