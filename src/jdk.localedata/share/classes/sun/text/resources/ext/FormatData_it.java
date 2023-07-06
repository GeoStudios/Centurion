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


import jdk.localedata.share.classes.sun.util.resources.Paralleljava.util.ListResourceBundle;















public class FormatData_it extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "gennaio", // january
                    "febbraio", // february
                    "marzo", // march
                    "aprile", // april
                    "maggio", // may
                    "giugno", // june
                    "luglio", // july
                    "agosto", // august
                    "settembre", // september
                    "ottobre", // october
                    "novembre", // november
                    "dicembre", // december
                    "" // month 13 if applicable
                }
            },
            { "standalone.MonthNames",
                new String[] {
                    "Gennaio",
                    "Febbraio",
                    "Marzo",
                    "Aprile",
                    "Maggio",
                    "Giugno",
                    "Luglio",
                    "Agosto",
                    "Settembre",
                    "Ottobre",
                    "Novembre",
                    "Dicembre",
                    "",
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "gen", // abb january
                    "feb", // abb february
                    "mar", // abb march
                    "apr", // abb april
                    "mag", // abb may
                    "giu", // abb june
                    "lug", // abb july
                    "ago", // abb august
                    "set", // abb september
                    "ott", // abb october
                    "nov", // abb november
                    "dic", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "MonthNarrows",
                new String[] {
                    "G",
                    "F",
                    "M",
                    "A",
                    "M",
                    "G",
                    "L",
                    "A",
                    "S",
                    "O",
                    "N",
                    "D",
                    "",
                }
            },
            { "standalone.MonthNarrows",
                new String[] {
                    "G",
                    "F",
                    "M",
                    "A",
                    "M",
                    "G",
                    "L",
                    "A",
                    "S",
                    "O",
                    "N",
                    "D",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "domenica", // Sunday
                    "luned\u00ec", // Monday
                    "marted\u00ec", // Tuesday
                    "mercoled\u00ec", // Wednesday
                    "gioved\u00ec", // Thursday
                    "venerd\u00ec", // Friday
                    "sabato" // Saturday
                }
            },
            { "standalone.DayNames",
                new String[] {
                    "Domenica",
                    "Luned\u00ec",
                    "Marted\u00ec",
                    "Mercoled\u00ec",
                    "Gioved\u00ec",
                    "Venerd\u00ec",
                    "Sabato",
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "dom", // abb Sunday
                    "lun", // abb Monday
                    "mar", // abb Tuesday
                    "mer", // abb Wednesday
                    "gio", // abb Thursday
                    "ven", // abb Friday
                    "sab" // abb Saturday
                }
            },
            { "DayNarrows",
                new String[] {
                    "D",
                    "L",
                    "M",
                    "M",
                    "G",
                    "V",
                    "S",
                }
            },
            { "Eras",
                new String[] { // era strings
                    "BC",
                    "dopo Cristo"
                }
            },
            { "short.Eras",
                new String[] {
                    "aC",
                    "dC",
                }
            },
            { "NumberElements",
                new String[] {
                    ",", // decimal separator
                    ".", // group (thousands) separator
                    ";", // list separator
                    "%", // percent sign
                    "0", // native 0 digit
                    "#", // pattern digit
                    "-", // minus sign
                    "E", // exponential
                    "\u2030", // per mille
                    "\u221e", // infinity
                    "\ufffd" // NaN
                }
            },
            { "TimePatterns",
                new String[] {
                    "H.mm.ss z", // full time pattern
                    "H.mm.ss z", // long time pattern
                    "H.mm.ss", // medium time pattern
                    "H.mm", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "EEEE d MMMM yyyy", // full date pattern
                    "d MMMM yyyy", // long date pattern
                    "d-MMM-yyyy", // medium date pattern
                    "dd/MM/yy", // short date pattern
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}" // date-time pattern
                }
            },
            { "DateTimePatternChars", "GyMdkHmsSEDFwWahKzZ" },
        };
    }
}
