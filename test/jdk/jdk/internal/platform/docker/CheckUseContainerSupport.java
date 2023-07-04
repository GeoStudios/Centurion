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

import jdk.internal.platform.Metrics;

public class CheckUseContainerSupport {

    // Usage: boolean value of -XX:+/-UseContainerSupport
    //        passed as the only argument
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            throw new RuntimeException("Expected only one boolean argument");
        }
        boolean expectedContainerSupport = Boolean.parseBoolean(args[0]);
        boolean actualContainerSupport = (Metrics.systemMetrics() != null);
        if (expectedContainerSupport != actualContainerSupport) {
            String msg = "-XX:" + ( expectedContainerSupport ? "+" : "-") + "UseContainerSupport, but got " +
                         "Metrics.systemMetrics() == " + (Metrics.systemMetrics() == null ? "null" : "non-null");
            System.out.println(msg);
            System.out.println("TEST FAILED!!!");
            return;
        }
        System.out.println("TEST PASSED!!!");
    }

}
