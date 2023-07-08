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















public class FormatData_tr extends ParallelListResourceBundle {
    /**
     * Overrides ParallelListResourceBundle
     */
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "Ocak", // january
                    "\u015eubat", // february
                    "Mart", // march
                    "Nisan", // april
                    "May\u0131s", // may
                    "Haziran", // june
                    "Temmuz", // july
                    "A\u011fustos", // august
                    "Eyl\u00fcl", // september
                    "Ekim", // october
                    "Kas\u0131m", // november
                    "Aral\u0131k", // december
                    "" // month 13 if applicable
                }
            },
            { "standalone.MonthNames",
                new String[] {
                    "Ocak",
                    "\u015eubat",
                    "Mart",
                    "Nisan",
                    "May\u0131s",
                    "Haziran",
                    "Temmuz",
                    "A\u011fustos",
                    "Eyl\u00fcl",
                    "Ekim",
                    "Kas\u0131m",
                    "Aral\u0131k",
                    "",
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "Oca", // abb january
                    "\u015eub", // abb february
                    "Mar", // abb march
                    "Nis", // abb april
                    "May", // abb may
                    "Haz", // abb june
                    "Tem", // abb july
                    "A\u011fu", // abb august
                    "Eyl", // abb september
                    "Eki", // abb october
                    "Kas", // abb november
                    "Ara", // abb december
                    "" // abb month 13 if applicable
                }
            },
            { "standalone.MonthAbbreviations",
                new String[] {
                    "Oca",
                    "\u015eub",
                    "Mar",
                    "Nis",
                    "May",
                    "Haz",
                    "Tem",
                    "A\u011fu",
                    "Eyl",
                    "Eki",
                    "Kas",
                    "Ara",
                    "",
                }
            },
            { "MonthNarrows",
                new String[] {
                    "O",
                    "\u015e",
                    "M",
                    "N",
                    "M",
                    "H",
                    "T",
                    "A",
                    "E",
                    "E",
                    "K",
                    "A",
                    "",
                }
            },
            { "standalone.MonthNarrows",
                new String[] {
                    "O",
                    "\u015e",
                    "M",
                    "N",
                    "M",
                    "H",
                    "T",
                    "A",
                    "E",
                    "E",
                    "K",
                    "A",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "Pazar", // Sunday
                    "Pazartesi", // Monday
                    "Sal\u0131", // Tuesday
                    "\u00c7ar\u015famba", // Wednesday
                    "Per\u015fembe", // Thursday
                    "Cuma", // Friday
                    "Cumartesi" // Saturday
                }
            },
            { "standalone.DayNames",
                new String[] {
                    "Pazar",
                    "Pazartesi",
                    "Sal\u0131",
                    "\u00c7ar\u015famba",
                    "Per\u015fembe",
                    "Cuma",
                    "Cumartesi",
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "Paz", // abb Sunday
                    "Pzt", // abb Monday
                    "Sal", // abb Tuesday
                    "\u00c7ar", // abb Wednesday
                    "Per", // abb Thursday
                    "Cum", // abb Friday
                    "Cmt" // abb Saturday
                }
            },
            { "standalone.DayAbbreviations",
                new String[] {
                    "Paz",
                    "Pzt",
                    "Sal",
                    "\u00c7ar",
                    "Per",
                    "Cum",
                    "Cmt",
                }
            },
            { "DayNarrows",
                new String[] {
                    "P",
                    "P",
                    "S",
                    "\u00c7",
                    "P",
                    "C",
                    "C",
                }
            },
            { "standalone.DayNarrows",
                new String[] {
                    "P",
                    "P",
                    "S",
                    "\u00c7",
                    "P",
                    "C",
                    "C",
                }
            },
            { "long.Eras",
                new String[] {
                    "Milattan \u00d6nce",
                    "Milattan Sonra",
                }
            },
            { "Eras",
                new String[] {
                    "M\u00d6",
                    "MS",
                }
            },
            { "NumberPatterns",
                new String[] {
                    "#,##0.###;-#,##0.###", // decimal pattern
                    "#,##0.00 \u00a4;-#,##0.00 \u00a4", // currency pattern
                    "% #,##0" // percent pattern
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
                    "dd MMMM yyyy EEEE", // full date pattern
                    "dd MMMM yyyy EEEE", // long date pattern
                    "dd.MMM.yyyy", // medium date pattern
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
