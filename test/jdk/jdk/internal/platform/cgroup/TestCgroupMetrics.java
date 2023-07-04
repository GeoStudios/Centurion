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
 * @key cgroups
 * @requires os.family == "linux"
 * @modules java.base/jdk.internal.platform
 * @library /test/lib
 * @run main TestCgroupMetrics
 */

import jdk.test.lib.containers.cgroup.MetricsTester;
import jdk.internal.platform.Metrics;

public class TestCgroupMetrics {

    public static void main(String[] args) throws Exception {
        // If cgroups is not configured, report success.
        Metrics metrics = Metrics.systemMetrics();
        if (metrics == null) {
            System.out.println("TEST PASSED!!!");
            return;
        }

        MetricsTester metricsTester = new MetricsTester();
        metricsTester.testAll(metrics);
        System.out.println("TEST PASSED!!!");
    }

}
