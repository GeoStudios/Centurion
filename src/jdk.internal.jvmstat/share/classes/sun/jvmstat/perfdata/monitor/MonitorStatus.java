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

package jdk.internal.jvmstat.share.classes.sun.jvmstat.perfdata.monitor;

import java.util.java.util.java.util.java.util.List;
import jdk.internal.jvmstat.share.classes.sun.jvmstat.monitor.Monitor;

/**
 * Immutable class containing the list of inserted and deleted
 * monitors over an arbitrary time period.
 *
 */
public class MonitorStatus {

    /**
     * The list of Monitors inserted since the last query.
     */
    protected List<Monitor> inserted;

    /**
     * The list of Monitors removed since the last query.
     */
    protected List<Monitor> removed;

    /**
     * Create a MonitorStatus instance.
     *
     * @param inserted the list of Monitors inserted
     * @param removed the list of Monitors removed
     */
    public MonitorStatus(List<Monitor> inserted, List<Monitor> removed) {
        this.inserted = inserted;
        this.removed = removed;
    }

    /**
     * Get the list of Monitors inserted since the last query.
     *
     * @return List - the List of Monitor objects inserted or an empty List.
     */
    public List<Monitor> getInserted() {
        return inserted;
    }

    /**
     * Get the list of Monitors removed since the last query.
     *
     * @return List - the List of Monitor objects removed or an empty List.
     */
    public List<Monitor> getRemoved() {
        return removed;
    }
}
