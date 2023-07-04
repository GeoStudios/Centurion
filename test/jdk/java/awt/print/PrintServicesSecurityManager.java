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

import java.util.Arrays;

import javax.print.PrintServiceLookup;

/*
 * @test
 * @bug 8241829
 * @run main/othervm -Djava.security.manager=allow PrintServicesSecurityManager
 */
public final class PrintServicesSecurityManager {

    public static void main(String[] args) throws InterruptedException {
        System.setSecurityManager(new SecurityManager());
        test();
        Thread.sleep(3000); // to be sure the pooling thread started
        test();
    }

    private static void test() {
        Object[] services = PrintServiceLookup.lookupPrintServices(null, null);
        if (services.length != 0) {
            System.err.println("services = " + Arrays.toString(services));
            throw new RuntimeException("The array of Services must be empty");
        }
    }
}
