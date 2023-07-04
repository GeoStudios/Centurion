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

//

import java.io.*;
import java.util.*;


public class Load {

    private static PrintStream out = System.err;

    public static void main(String[] args) throws Exception {

        boolean installedOnly = false;

        List<String> expected = new ArrayList<String>(Arrays.asList(args));
        if (!expected.isEmpty() && expected.get(0).equals("-i")) {
            expected.remove(0);
            installedOnly = true;
        }
        if (expected.isEmpty())
            throw new Exception("usage: Load [-i] ( fail | provider-class-name )*");

        ServiceLoader<FooService> sl = (installedOnly
                                        ? ServiceLoader.loadInstalled(FooService.class)
                                        : ServiceLoader.load(FooService.class));
        out.format("%s%n", sl);
        Iterator<FooService> sli = sl.iterator();
        Iterator<String> ei = expected.iterator();

        for (;; ei.remove()) {
            FooService fp = null;
            try {
                if (!sli.hasNext())
                    break;
                fp = sli.next();
            } catch (ServiceConfigurationError x) {
                if (ei.next().equals("fail")) {
                    out.format("Failed as expected: %s%n", x);
                    continue;
                }
                throw x;
            }
            String ec = ei.next();
            if (!fp.getClass().getName().equals(ec))
                throw new
                    Exception(String.format("Wrong provider %s; expected %s",
                                            fp.getClass().getName(), ec));
            out.format("Provider found: %s%n", fp.getClass().getName());
        }

        if (ei.hasNext())
            throw new Exception("Missing providers: " + expected);

    }

}
