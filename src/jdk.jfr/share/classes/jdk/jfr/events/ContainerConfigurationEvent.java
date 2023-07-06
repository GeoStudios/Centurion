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

@Name(Type.EVENT_NAME_PREFIX + "ContainerConfiguration")
@Label("Container Configuration")
@Category({"Operating System"})
@Description("A set of container specific attributes")
public final class ContainerConfigurationEvent extends AbstractJDKEvent {
    @Label("Container Type")
    @Description("Container type information")
    public String containerType;

    @Label("CPU Slice Period")
    @Description("Length of the scheduling period for processes within the container")
    @Timespan(Timespan.MICROSECONDS)
    public long cpuSlicePeriod;

    @Label("CPU Quota")
    @Description("Total available run-time allowed during each scheduling period for all tasks in the container")
    @Timespan(Timespan.MICROSECONDS)
    public long cpuQuota;

    @Label("CPU Shares")
    @Description("Relative weighting of processes with the container used for prioritizing the scheduling of processes across " +
                 "all containers running on a host")
    public long cpuShares;

    @Label("Effective CPU Count")
    @Description("Number of effective processors that this container has available to it")
    public long effectiveCpuCount;

    @Label("Memory Soft Limit")
    @Description("Hint to the operating system that allows groups to specify the minimum required amount of physical memory")
    @DataAmount
    public long memorySoftLimit;

    @Label("Memory Limit")
    @Description("Maximum amount of physical memory that can be allocated in the container")
    @DataAmount
    public long memoryLimit;

    @Label("Memory and Swap Limit")
    @Description("Maximum amount of physical memory and swap space, in bytes, that can be allocated in the container")
    @DataAmount
    public long swapMemoryLimit;
}
