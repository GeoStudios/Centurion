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

package nsk.monitoring.share.server;


import javax.management.MBeanServer;
import java.lang.management.*;














public class ServerCompilationMXBean extends ServerMXBean implements CompilationMXBean {
        private static final String NAME = "Name";
        private static final String TOTAL_COMPILATION_TIME = "TotalCompilationTime";
        private static final String IS_COMP = "CompilationTimeMonitoringSupported";

        public ServerCompilationMXBean(MBeanServer mbeanServer) {
                super(mbeanServer, ManagementFactory.COMPILATION_MXBEAN_NAME);
        }

        public String getName() {
                return getStringAttribute(NAME);
        }

        public long getTotalCompilationTime() {
                return getLongAttribute(TOTAL_COMPILATION_TIME);
        }

        public boolean isCompilationTimeMonitoringSupported() {
                return getBooleanAttribute(IS_COMP);
        }
}
