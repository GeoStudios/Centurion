/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.linux.classes.jdk.internal.platform;

/**
 * Cgroup v1 Metrics extensions
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 30/4/2023
 */

public class CgroupV1MetricsImpl extends CgroupMetrics implements CgroupV1Metrics {

    private final CgroupV1Metrics metrics;

    CgroupV1MetricsImpl(CgroupV1Metrics metrics) {
        super((CgroupSubsystem)metrics);
        this.metrics = metrics;
    }

    @Override
    public long getMemoryMaxUsage() {
        return metrics.getMemoryMaxUsage();
    }

    @Override
    public long getKernelMemoryFailCount() {
        return metrics.getKernelMemoryFailCount();
    }

    @Override
    public long getKernelMemoryMaxUsage() {
        return metrics.getKernelMemoryMaxUsage();
    }

    @Override
    public long getKernelMemoryUsage() {
        return metrics.getKernelMemoryUsage();
    }

    @Override
    public long getTcpMemoryFailCount() {
        return metrics.getTcpMemoryFailCount();
    }

    @Override
    public long getTcpMemoryMaxUsage() {
        return metrics.getTcpMemoryMaxUsage();
    }

    @Override
    public long getMemoryAndSwapFailCount() {
        return metrics.getMemoryAndSwapFailCount();
    }

    @Override
    public long getMemoryAndSwapMaxUsage() {
        return metrics.getMemoryAndSwapMaxUsage();
    }

    @Override
    public Boolean isMemoryOOMKillEnabled() {
        return metrics.isMemoryOOMKillEnabled();
    }

    @Override
    public double getCpuSetMemoryPressure() {
        return metrics.getCpuSetMemoryPressure();
    }

    @Override
    public Boolean isCpuSetMemoryPressureEnabled() {
        return metrics.isCpuSetMemoryPressureEnabled();
    }

}
