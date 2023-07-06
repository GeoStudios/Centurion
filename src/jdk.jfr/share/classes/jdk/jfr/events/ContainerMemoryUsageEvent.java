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

import jdk.jfr.share.classes.jdk.jfr.Category;
import jdk.jfr.share.classes.jdk.jfr.DataAmount;
import jdk.jfr.share.classes.jdk.jfr.Description;
import jdk.jfr.share.classes.jdk.jfr.Enabled;
import jdk.jfr.share.classes.jdk.jfr.Label;
import jdk.jfr.share.classes.jdk.jfr.Name;
import jdk.jfr.share.classes.jdk.jfr.Period;
import jdk.jfr.share.classes.jdk.jfr.StackTrace;
import jdk.jfr.share.classes.jdk.jfr.Threshold;
import jdk.jfr.share.classes.jdk.jfr.Timespan;
import jdk.jfr.share.classes.jdk.jfr.internal.Type;

@Name(Type.EVENT_NAME_PREFIX + "ContainerMemoryUsage")
@Label("Container Memory Usage")
@Category({"Operating System", "Memory"})
@Description("Container memory usage related information")
public final class ContainerMemoryUsageEvent extends AbstractJDKEvent {
    @Label("Memory Fail Count")
    @Description("Number of times that user memory requests in the container have exceeded the memory limit")
    public long memoryFailCount;

    @Label("Memory Usage")
    @Description("Amount of physical memory, in bytes, that is currently allocated in the current container")
    @DataAmount
    public long memoryUsage;

    @Label("Memory and Swap Usage")
    @Description("Amount of physical memory and swap space, in bytes, that is currently allocated in the current container")
    @DataAmount
    public long swapMemoryUsage;
}
