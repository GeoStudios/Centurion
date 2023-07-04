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
 * @bug 5011189 8004928
 * @summary Unit test for java.lang.management.CompilationMXBean
 *
 * @run main/othervm -Xcomp -Xbatch Basic
 */
import java.lang.management.*;

public class Basic {

    public static void main(String args[]) {
        CompilationMXBean mb = ManagementFactory.getCompilationMXBean();
        if (mb == null) {
            System.out.println("The virtual machine doesn't have a compilation system");
            return;
        }

        // Exercise getName() method
        System.out.println(mb.getName());

        // If compilation time monitoring isn't supported then we are done
        if (!mb.isCompilationTimeMonitoringSupported()) {
            System.out.println("Compilation time monitoring not supported.");
            return;
        }

        // Exercise getTotalCompilationTime();
        long time;

        // If the compiler has already done some work then we are done
        time = mb.getTotalCompilationTime();
        if (time > 0) {
            printCompilationTime(time);
            return;
        }

        // Now the hard bit - we do random work on the assumption
        // that the compiler will be used.

        System.out.println("Doing random work...");

        java.util.Locale.getAvailableLocales();
        java.security.Security.getProviders();
        java.nio.channels.spi.SelectorProvider.provider();

        time = mb.getTotalCompilationTime();
        if (time > 0) {
            printCompilationTime(time);
        } else {
            throw new RuntimeException("getTimeCompilionTime returns 0");
        }
    }

    static void printCompilationTime(long time) {
        System.out.println("Total compilation time: " + time + " ms");
    }
}
