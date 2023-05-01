/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.linux.classes.jdk.internal.platform;

/**
 * Cgroup v1 Metrics extensions
 *
 * @since Pre Java 1
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
