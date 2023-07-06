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

public class FormatData_sr_BA extends ParallelListResourceBundle {
    protected final Object[][] getContents() {
        return new Object[][] {
            { "MonthNames",
                new String[] {
                    "\u0458\u0430\u043d\u0443\u0430\u0440",
                    "\u0444\u0435\u0431\u0440\u0443\u0430\u0440",
                    "\u043c\u0430\u0440\u0442",
                    "\u0430\u043f\u0440\u0438\u043b",
                    "\u043c\u0430\u0458",
                    "\u0458\u0443\u043d\u0438",
                    "\u0458\u0443\u043b\u0438",
                    "\u0430\u0432\u0433\u0443\u0441\u0442",
                    "\u0441\u0435\u043f\u0442\u0435\u043c\u0431\u0430\u0440",
                    "\u043e\u043a\u0442\u043e\u0431\u0430\u0440",
                    "\u043d\u043e\u0432\u0435\u043c\u0431\u0430\u0440",
                    "\u0434\u0435\u0446\u0435\u043c\u0431\u0430\u0440",
                    "",
                }
            },
            { "DayNames",
                new String[] {
                    "\u043d\u0435\u0434\u0435\u0459\u0430",
                    "\u043f\u043e\u043d\u0435\u0434\u0435\u0459\u0430\u043a",
                    "\u0443\u0442\u043e\u0440\u0430\u043a",
                    "\u0441\u0440\u0438\u0458\u0435\u0434\u0430",
                    "\u0447\u0435\u0442\u0432\u0440\u0442\u0430\u043a",
                    "\u043f\u0435\u0442\u0430\u043a",
                    "\u0441\u0443\u0431\u043e\u0442\u0430",
                }
            },
            { "DayAbbreviations",
                new String[] {
                    "\u043d\u0435\u0434",
                    "\u043f\u043e\u043d",
                    "\u0443\u0442\u043e",
                    "\u0441\u0440\u0438",
                    "\u0447\u0435\u0442",
                    "\u043f\u0435\u0442",
                    "\u0441\u0443\u0431",
                }
            },
            { "TimePatterns",
                new String[] {
                    "HH '\u0447\u0430\u0441\u043e\u0432\u0430', mm '\u043c\u0438\u043d\u0443\u0442\u0430', ss' \u0441\u0435\u043a\u0443\u043d\u0434\u0438'",
                    "HH.mm.ss z",
                    "HH:mm:ss",
                    "HH:mm",
                }
            },
            { "DatePatterns",
                new String[] {
                    "EEEE, dd. MMMM yyyy.",
                    "dd. MMMM yyyy.",
                    "yyyy-MM-dd",
                    "yy-MM-dd",
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
