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
 * Data structure to hold info from /proc/self/cgroup,
 * /proc/cgroups and /proc/self/mountinfo
 *
 * @see CgroupSubsystemFactory
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 30/4/2023
 */

public class CgroupInfo {

    private final String name;
    private final int hierarchyId;
    private final boolean enabled;
    private String mountPoint;
    private String mountRoot;
    private String cgroupPath;

    private CgroupInfo(String name, int hierarchyId, boolean enabled) {
        this.name = name;
        this.hierarchyId = hierarchyId;
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public int getHierarchyId() {
        return hierarchyId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getMountPoint() {
        return mountPoint;
    }

    public void setMountPoint(String mountPoint) {
        this.mountPoint = mountPoint;
    }

    public String getMountRoot() {
        return mountRoot;
    }

    public void setMountRoot(String mountRoot) {
        this.mountRoot = mountRoot;
    }

    public String getCgroupPath() {
        return cgroupPath;
    }

    public void setCgroupPath(String cgroupPath) {
        this.cgroupPath = cgroupPath;
    }

    /*
     * Creates a CgroupInfo instance from a line in /proc/cgroups.
     * Comment token (hash) is handled by the caller.
     *
     * Example (annotated):
     *
     * #subsys_name     hierarchy       num_cgroups     enabled
     * cpuset           10              1               1         (a)
     * cpu              7               8               1         (b)
     * [...]
     *
     * Line (a) would yield:
     *   info = new CgroupInfo("cpuset", 10, true);
     *   return info;
     * Line (b) results in:
     *   info = new CgroupInfo("cpu", 7, true);
     *   return info;
     *
     *
     * See CgroupSubsystemFactory.determineType()
     *
     */
    static CgroupInfo fromCgroupsLine(String line) {
        String[] tokens = line.split("\\s+");
        if (tokens.length != 4) {
            return null;
        }
        // discard 3'rd field, num_cgroups
        return new CgroupInfo(tokens[0] /* name */,
                              Integer.parseInt(tokens[1]) /* hierarchyId */,
                              (Integer.parseInt(tokens[3]) == 1) /* enabled */);
    }

}
