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















public class FormatData_ro extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "ianuarie", // january
                    "februarie", // february
                    "martie", // march
                    "aprilie", // april
                    "mai", // may
                    "iunie", // june
                    "iulie", // july
                    "august", // august
                    "septembrie", // september
                    "octombrie", // october
                    "noiembrie", // november
                    "decembrie", // december
                    "" // month 13 if applicable
                }
            },
            { "standalone.MonthNames",
                new String[] {
                    "ianuarie",
                    "februarie",
                    "martie",
                    "aprilie",
                    "mai",
                    "iunie",
                    "iulie",
                    "august",
                    "septembrie",
                    "octombrie",
                    "noiembrie",
                    "decembrie",
                    "",
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "Ian", // abb january
                    "Feb", // abb february
                    "Mar", // abb march
                    "Apr", // abb april
                    "Mai", // abb may
                    "Iun", // abb june
                    "Iul", // abb july
                    "Aug", // abb august
                    "Sep", // abb september
                    "Oct", // abb october
                    "Nov", // abb november
                    "Dec", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "standalone.MonthAbbreviations",
                new String[] {
                    "ian.",
                    "feb.",
                    "mar.",
                    "apr.",
                    "mai",
                    "iun.",
                    "iul.",
                    "aug.",
                    "sept.",
                    "oct.",
                    "nov.",
                    "dec.",
                    "",
                }
            },
            { "MonthNarrows",
                new String[] {
                    "I",
                    "F",
                    "M",
                    "A",
                    "M",
                    "I",
                    "I",
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
                    "I",
                    "F",
                    "M",
                    "A",
                    "M",
                    "I",
                    "I",
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
                    "duminic\u0103", // Sunday
                    "luni", // Monday
                    "mar\u0163i", // Tuesday
                    "miercuri", // Wednesday
                    "joi", // Thursday
                    "vineri", // Friday
                    "s\u00e2mb\u0103t\u0103" // Saturday
                }
            },
            { "standalone.DayNames",
                new String[] {
                    "duminic\u0103",
                    "luni",
                    "mar\u021bi",
                    "miercuri",
                    "joi",
                    "vineri",
                    "s\u00e2mb\u0103t\u0103",
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "D", // abb Sunday
                    "L", // abb Monday
                    "Ma", // abb Tuesday
                    "Mi", // abb Wednesday
                    "J", // abb Thursday
                    "V", // abb Friday
                    "S" // abb Saturday
                }
            },
            { "standalone.DayAbbreviations",
                new String[] {
                    "Du",
                    "Lu",
                    "Ma",
                    "Mi",
                    "Jo",
                    "Vi",
                    "S\u00e2",
                }
            },
            { "DayNarrows",
                new String[] {
                    "D",
                    "L",
                    "M",
                    "M",
                    "J",
                    "V",
                    "S",
                }
            },
            { "standalone.DayNarrows",
                new String[] {
                    "D",
                    "L",
                    "M",
                    "M",
                    "J",
                    "V",
                    "S",
                }
            },
            { "Eras",
                new String[] { // era strings
                    "d.C.",
                    "\u00ee.d.C."
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
                    "HH:mm:ss z", // full time pattern
                    "HH:mm:ss z", // long time pattern
                    "HH:mm:ss", // medium time pattern
                    "HH:mm", // short time pattern
                }
            },
            { "DatePatterns",
                new String[] {
                    "dd MMMM yyyy", // full date pattern
                    "dd MMMM yyyy", // long date pattern
                    "dd.MM.yyyy", // medium date pattern
                    "dd.MM.yyyy", // short date pattern
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}" // date-time pattern
                }
            },
            { "DateTimePatternChars", "GanjkHmsSEDFwWxhKzZ" },
        };
    }
}
