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

/*
 * @test
 * @bug 6731826
 * @summary  There should be no RuntimeException.
 * @run main TestRaceCond
 */

import javax.print.PrintService;
import javax.print.PrintServiceLookup;


public class TestRaceCond {

    public static void main(String argv[]) {
        trial();
    }

    static void trial() {
        PrintService pserv1 = PrintServiceLookup.lookupDefaultPrintService();
        PrintService[] pservs = PrintServiceLookup.lookupPrintServices(null, null);
        PrintService pserv2 = PrintServiceLookup.lookupDefaultPrintService();

        if ((pserv1 == null) || (pserv2==null)) {
            return;
        }

        if (pserv1.hashCode() != pserv2.hashCode()) {
            throw new RuntimeException("Different hashCodes for equal print "
                            + "services: " + pserv1.hashCode() + " "
                            + pserv2.hashCode());
        }
    }
}

