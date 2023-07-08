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

public class FormatData_sr_Latn extends ParallelListResourceBundle {
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "januar",
                    "februar",
                    "mart",
                    "april",
                    "maj",
                    "jun",
                    "jul",
                    "avgust",
                    "septembar",
                    "oktobar",
                    "novembar",
                    "decembar",
                    "",
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "jan",
                    "feb",
                    "mar",
                    "apr",
                    "maj",
                    "jun",
                    "jul",
                    "avg",
                    "sep",
                    "okt",
                    "nov",
                    "dec",
                    "",
                }
            },
            { "MonthNarrows",
                new String[] {
                    "j",
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
            { "DayNames",
                new String[] {
                    "nedelja",
                    "ponedeljak",
                    "utorak",
                    "sreda",
                    "\u010detvrtak",
                    "petak",
                    "subota",
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "ned",
                    "pon",
                    "uto",
                    "sre",
                    "\u010det",
                    "pet",
                    "sub",
                }
            },
            { "DayNarrows",
                new String[] {
                    "n",
                    "p",
                    "u",
                    "s",
                    "\u010d",
                    "p",
                    "s",
                }
            },
            { "Eras",
                new String[] {
                    "p. n. e.",
                    "n. e",
                }
            },
            { "NumberPatterns",
                new String[] {
                    "#,##0.###",
                    "\u00a4\u00a0#,##0.00",
                    "#,##0%",
                }
            },
            { "NumberElements",
                new String[] {
                    ",",
                    ".",
                    ";",
                    "%",
                    "0",
                    "#",
                    "-",
                    "E",
                    "\u2030",
                    "\u221e",
                    "NaN",
                }
            },
            { "TimePatterns",
                new String[] {
                    "HH.mm.ss zzzz",
                    "HH.mm.ss z",
                    "HH.mm.ss",
                    "HH.mm",
                }
            },
            { "DatePatterns",
                new String[] {
                    "EEEE, dd. MMMM y.",
                    "dd. MMMM y.",
                    "dd.MM.y.",
                    "d.M.yy.",
                }
            },
            { "DateTimePatterns",
                new String[] {
                    "{1} {0}",
                }
            },
        };
    }
}
