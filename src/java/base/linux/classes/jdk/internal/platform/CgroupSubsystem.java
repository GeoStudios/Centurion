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
 * Marker interface for cgroup-based metrics
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 30/4/2023
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
