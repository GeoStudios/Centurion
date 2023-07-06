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

package nsk.monitoring.share.thread;

/**
 * ThreadMonitoringScenarioFactory is factory for creating ThreadMonitoringScenario's.
 */
public interface ThreadMonitoringScenarioFactory {
        /**
         * Obtain recommended scenario count for given basic threads count
         * (which is usually number of processors).
         *
         * @param basicCount
         */
        public int getScenarioCount(int basicThreadCount);

        /**
         * Create scenarios for given scenario count.
         *
         * @param count scenario count
         * @return array of scenarios
         */
        public ThreadMonitoringScenario[] createScenarios(int count);

        /**
         * Create scenario by type name.
         *
         * @param scenarioType type of scenario
         * @return created scenario
         */
        public ThreadMonitoringScenario createScenario(String scenarioType);

        /**
         * Clean up resources held by created scenarios. This should be called
         * after finish() is also called for all created scenarios.
         */
        public void finish();
}
