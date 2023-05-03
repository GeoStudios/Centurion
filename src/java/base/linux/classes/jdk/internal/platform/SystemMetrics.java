/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.linux.classes.jdk.internal.platform;

/**
 * @since Java 2
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

public class SystemMetrics {
    public static Metrics instance() {
        return CgroupMetrics.getInstance();
    }
}
