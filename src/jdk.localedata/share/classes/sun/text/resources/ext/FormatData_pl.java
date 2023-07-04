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

package sun.text.resources.ext;

import sun.util.resources.ParallelListResourceBundle;

public class FormatData_pl extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "stycznia",
                    "lutego",
                    "marca",
                    "kwietnia",
                    "maja",
                    "czerwca",
                    "lipca",
                    "sierpnia",
                    "wrze\u015bnia",
                    "pa\u017adziernika",
                    "listopada",
                    "grudnia",
                    "",
                }
            },
            { "standalone.MonthNames",
                new String[] {
                    "stycze\u0144", // january
                    "luty", // february
                    "marzec", // march
                    "kwiecie\u0144", // april
                    "maj", // may
                    "czerwiec", // june
                    "lipiec", // july
                    "sierpie\u0144", // august
                    "wrzesie\u0144", // september
                    "pa\u017adziernik", // october
                    "listopad", // november
                    "grudzie\u0144", // december
                    "" // month 13 if applicable
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "sty", // abb january
                    "lut", // abb february
                    "mar", // abb march
                    "kwi", // abb april
                    "maj", // abb may
                    "cze", // abb june
                    "lip", // abb july
                    "sie", // abb august
                    "wrz", // abb september
                    "pa\u017a", // abb october
                    "lis", // abb november
                    "gru", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "standalone.MonthAbbreviations",
                new String[] {
                    "sty",
                    "lut",
                    "mar",
                    "kwi",
                    "maj",
                    "cze",
                    "lip",
                    "sie",
                    "wrz",
                    "pa\u017a",
                    "lis",
                    "gru",
                    "",
                }
            },
            { "MonthNarrows",
                new String[] {
                    "s",
                    "l",
                    "m",
                    "k",
                    "m",
                    "c",
                    "l",
                    "s",
                    "w",
                    "p",
                    "l",
                    "g",
                    "",
                }
            },
            { "standalone.MonthNarrows",
                new String[] {
                    "s",
                    "l",
                    "m",
                    "k",
                    "m",
                    "c",
                    "l",
                    "s",
                    "w",
                    "p",
                    "l",
                    "g",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "niedziela", // Sunday
                    "poniedzia\u0142ek", // Monday
                    "wtorek", // Tuesday
                    "\u015broda", // Wednesday
                    "czwartek", // Thursday
                    "pi\u0105tek", // Friday
                    "sobota" // Saturday
                }
            },
            { "standalone.DayNames",
                new String[] {
                    "niedziela",
                    "poniedzia\u0142ek",
                    "wtorek",
                    "\u015broda",
                    "czwartek",
                    "pi\u0105tek",
                    "sobota",
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "N", // abb Sunday
                    "Pn", // abb Monday
                    "Wt", // abb Tuesday
                    "\u015ar", // abb Wednesday
                    "Cz", // abb Thursday
                    "Pt", // abb Friday
                    "So" // abb Saturday
                }
            },
            { "standalone.DayAbbreviations",
                new String[] {
                    "niedz.",
                    "pon.",
                    "wt.",
                    "\u015br.",
                    "czw.",
                    "pt.",
                    "sob.",
                }
            },
            { "DayNarrows",
                new String[] {
                    "N",
                    "P",
                    "W",
                    "\u015a",
                    "C",
                    "P",
                    "S",
                }
            },
            { "standalone.DayNarrows",
                new String[] {
                    "N",
                    "P",
                    "W",
                    "\u015a",
                    "C",
                    "P",
                    "S",
                }
            },
            { "Eras",
                new String[] { // era strings
                    "p.n.e.",
                    "n.e."
                }
            },
            { "NumberElements",
                new String[] {
                    ",", // decimal separator
                    "\u00a0", // group (thousands) separator
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
                    "EEEE, d MMMM yyyy", // full date pattern
                    "d MMMM yyyy", // long date pattern
                    "yyyy-MM-dd", // medium date pattern
                    "yy-MM-dd", // short date pattern
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
