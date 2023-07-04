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

public class FormatData_mt extends ParallelListResourceBundle {
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "Jannar",
                    "Frar",
                    "Marzu",
                    "April",
                    "Mejju",
                    "\u0120unju",
                    "Lulju",
                    "Awwissu",
                    "Settembru",
                    "Ottubru",
                    "Novembru",
                    "Di\u010bembru",
                    "",
                }
            },
            { "MonthAbbreviations",
                new String[] {
                    "Jan",
                    "Fra",
                    "Mar",
                    "Apr",
                    "Mej",
                    "\u0120un",
                    "Lul",
                    "Aww",
                    "Set",
                    "Ott",
                    "Nov",
                    "Di\u010b",
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
                    "\u0120",
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
                    "Il-\u0126add",
                    "It-Tnejn",
                    "It-Tlieta",
                    "L-Erbg\u0127a",
                    "Il-\u0126amis",
                    "Il-\u0120img\u0127a",
                    "Is-Sibt",
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "\u0126ad",
                    "Tne",
                    "Tli",
                    "Erb",
                    "\u0126am",
                    "\u0120im",
                    "Sib",
                }
            },
            { "DayNarrows",
                new String[] {
                    "\u0126",
                    "T",
                    "T",
                    "E",
                    "\u0126",
                    "\u0120",
                    "S",
                }
            },
            { "AmPmMarkers",
                new String[] {
                    "QN",
                    "WN",
                }
            },
            { "Eras",
                new String[] {
                    "QK",
                    "WK",
                }
            },
            { "NumberPatterns",
                new String[] {
                    "#,##0.###",
                    "\u00a4 #,##0.00",
                    "#,##0%",
                }
            },
            { "NumberElements",
                new String[] {
                    ".",
                    ",",
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
                    "HH:mm:ss z",
                    "HH:mm:ss z",
                    "HH:mm:ss",
                    "HH:mm",
                }
            },
            { "DatePatterns",
                new String[] {
                    "EEEE, d 'ta\u2019' MMMM yyyy",
                    "d 'ta\u2019' MMMM yyyy",
                    "dd MMM yyyy",
                    "dd/MM/yyyy",
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
