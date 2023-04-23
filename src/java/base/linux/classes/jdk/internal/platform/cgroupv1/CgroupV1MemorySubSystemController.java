/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package jdk.internal.platform.cgroupv1;

/**
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

public class CgroupV1MemorySubSystemController extends CgroupV1SubsystemController {

    private boolean hierarchical;
    private boolean swapenabled;

    public CgroupV1MemorySubSystemController(String root, String mountPoint) {
        super(root, mountPoint);
    }

    boolean isHierarchical() {
        return hierarchical;
    }

    void setHierarchical(boolean hierarchical) {
        this.hierarchical = hierarchical;
    }

    boolean isSwapEnabled() {
        return swapenabled;
    }

    void setSwapEnabled(boolean swapenabled) {
        this.swapenabled = swapenabled;
    }
}
