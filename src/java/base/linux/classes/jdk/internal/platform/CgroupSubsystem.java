/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.linux.classes.jdk.internal.platform;

/**
 * Marker interface for cgroup-based metrics
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */
public interface CgroupSubsystem extends Metrics {

    /**
     * Returned for metrics of type long if the underlying implementation
     * has determined that no limit is being imposed.
     */
    public static final long LONG_RETVAL_UNLIMITED = -1;
    public static final String MAX_VAL = "max";

    public static long limitFromString(String strVal) {
        if (strVal == null || MAX_VAL.equals(strVal)) {
            return CgroupSubsystem.LONG_RETVAL_UNLIMITED;
        }
        return Long.parseLong(strVal);
    }

}
