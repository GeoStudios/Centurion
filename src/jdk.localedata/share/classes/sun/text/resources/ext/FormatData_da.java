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

/*
 */

public class FormatData_da extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "januar", // january
                    "februar", // february
                    "marts", // march
                    "april", // april
                    "maj", // may
                    "juni", // june
                    "juli", // july
                    "august", // august
                    "september", // september
                    "oktober", // october
                    "november", // november
                    "december", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "jan.",
                    "feb.",
                    "mar.",
                    "apr.",
                    "maj",
                    "jun.",
                    "jul.",
                    "aug.",
                    "sep.",
                    "okt.",
                    "nov.",
                    "dec.",
                    "",
                }
            },
            { "MonthNarrows",
                new String[] {
                    "J",
                    "F",
                    "M",
                    "A",
                    "M",
                    "J",
                    "J",
                    "A",
                    "S",
                    "O",
                    "N",
                    "D",
                    "",
                }
            },
            { "standalone.MonthAbbreviations",
                new String[] {
                    "jan", // abb january
                    "feb", // abb february
                    "mar", // abb march
                    "apr", // abb april
                    "maj", // abb may
                    "jun", // abb june
                    "jul", // abb july
                    "aug", // abb august
                    "sep", // abb september
                    "okt", // abb october
                    "nov", // abb november
                    "dec", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "DayNames",
                new String[] {
                    "s\u00f8ndag", // Sunday
                    "mandag", // Monday
                    "tirsdag", // Tuesday
                    "onsdag", // Wednesday
                    "torsdag", // Thursday
                    "fredag", // Friday
                    "l\u00f8rdag" // Saturday
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "s\u00f8", // abb Sunday
                    "ma", // abb Monday
                    "ti", // abb Tuesday
                    "on", // abb Wednesday
                    "to", // abb Thursday
                    "fr", // abb Friday
                    "l\u00f8" // abb Saturday
                }
            },
            { "DayNarrows",
                new String[] {
                    "S",
                    "M",
                    "T",
                    "O",
                    "T",
                    "F",
                    "L",
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
            { "Eras",
                new String[] {
                    "f.Kr.",
                    "e.Kr.",
                }
            },
            { "short.Eras",
                new String[] {
                    "f.Kr.",
                    "e.Kr.",
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
                    "d. MMMM yyyy", // full date pattern
                    "d. MMMM yyyy", // long date pattern
                    "dd-MM-yyyy", // medium date pattern
                    "dd-MM-yy", // short date pattern
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}" // date-time pattern
                }
            },
            { "DateTimePatternChars", "GuMtkHmsSEDFwWahKzZ" },
        };
    }
}
