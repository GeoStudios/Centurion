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

package java.management.share.classes.sun.management;


import java.management.share.classes.sun.management.counter.Counter;















/**
 * Hotspot internal management interface for the thread system.
 */
public interface HotspotThreadMBean {

    /**
     * Returns the current number of VM internal threads.
     *
     * @return the current number of VM internal threads.
     */
    int getInternalThreadCount();

    /**
     * Returns a {@code Map} of the name of all VM internal threads
     * to the thread CPU time in nanoseconds.  The returned value is
     * of nanoseconds precision but not necessarily nanoseconds accuracy.
     *
     * @return a {@code Map} object of the name of all VM internal threads
     * to the thread CPU time in nanoseconds.
     *
     * @throws java.lang.UnsupportedOperationException if the Java virtual
     * machine does not support CPU time measurement.
     *
     * @see java.lang.management.ThreadMXBean#isThreadCpuTimeSupported
     */
    java.util.Map<String,Long> getInternalThreadCpuTimes();

    /**
     * Returns a list of internal counters maintained in the Java
     * virtual machine for the thread system.
     *
     * @return a list of internal counters maintained in the VM
     * for the thread system.
     */
    java.util.List<Counter> getInternalThreadingCounters();
}
