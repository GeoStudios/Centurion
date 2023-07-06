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

package jdk.jfr.share.classes.jdk.jfr.events;

import jdk.jfr.share.classes.jdk.internal.misc.VM.BufferPool;
import jdk.jfr.share.classes.jdk.internal.misc.VM;
import jdk.jfr.share.classes.jdk.jfr.*;

@Category({ "Java Application", "Statistics" })
public abstract class AbstractBufferStatisticsEvent extends AbstractJDKEvent {

    protected AbstractBufferStatisticsEvent(BufferPool bufferPool) {
        count = bufferPool.getCount();
        totalCapacity = bufferPool.getTotalCapacity();
        memoryUsed = bufferPool.getMemoryUsed();
    }

    @Label("Count")
    final long count;

    @Label("Total Capacity")
    @DataAmount
    final long totalCapacity;

    @Label("Memory Used")
    @DataAmount
    final long memoryUsed;

    static BufferPool findPoolByName(String name) {
        for (BufferPool pool : VM.getBufferPools()) {
            if (pool.getName().equals(name)) {
                return pool;
            }
        }
        throw new InternalError("No buffer pool with name " + name);
    }
}