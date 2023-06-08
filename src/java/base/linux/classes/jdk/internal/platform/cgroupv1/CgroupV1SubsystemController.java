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

package java.base.linux.classes.jdk.internal.platform.cgroupv1;

import java.base.linux.classes.jdk.internal.platform.CgroupSubsystem;
import java.base.linux.classes.jdk.internal.platform.CgroupSubsystemController;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

public class CgroupV1SubsystemController implements CgroupSubsystemController {

    private static final double DOUBLE_RETVAL_UNLIMITED = CgroupSubsystem.LONG_RETVAL_UNLIMITED;
    // Values returned larger than this number are unlimited.
    static long UNLIMITED_MIN = 0x7FFFFFFFFF000000L;
    String root;
    String mountPoint;
    String path;

    public CgroupV1SubsystemController(String root, String mountPoint) {
        this.root = root;
        this.mountPoint = mountPoint;
    }

    public void setPath(String cgroupPath) {
        if (root != null && cgroupPath != null) {
            if (root.equals("/")) {
                if (!cgroupPath.equals("/")) {
                    path = mountPoint + cgroupPath;
                }
                else {
                    path = mountPoint;
                }
            }
            else {
                if (root.equals(cgroupPath)) {
                    path = mountPoint;
                }
                else {
                    if (cgroupPath.startsWith(root)) {
                        if (cgroupPath.length() > root.length()) {
                            String cgroupSubstr = cgroupPath.substring(root.length());
                            path = mountPoint + cgroupSubstr;
                        }
                    }
                }
            }
        }
    }

    @Override
    public String path() {
        return path;
    }

    public static long getLongEntry(CgroupSubsystemController controller, String param, String entryname) {
        return CgroupSubsystemController.getLongEntry(controller,
                                                      param,
                                                      entryname,
                                                      CgroupSubsystem.LONG_RETVAL_UNLIMITED /* retval on error */);
    }

    public static double getDoubleValue(CgroupSubsystemController controller, String param) {
        return CgroupSubsystemController.getDoubleValue(controller,
                                                        param,
                                                        DOUBLE_RETVAL_UNLIMITED /* retval on error */);
    }

    public static long convertStringToLong(String strval) {
        return CgroupSubsystemController.convertStringToLong(strval,
                                                             Long.MAX_VALUE /* overflow value */,
                                                             CgroupSubsystem.LONG_RETVAL_UNLIMITED /* retval on error */);
    }

    public static long longValOrUnlimited(long value) {
        return value > UNLIMITED_MIN ? CgroupSubsystem.LONG_RETVAL_UNLIMITED : value;
    }

    public static long getLongValueMatchingLine(CgroupSubsystemController controller,
                                                String param,
                                                String match) {
        return CgroupSubsystemController.getLongValueMatchingLine(controller,
                                                                  param,
                                                                  match,
                                                                  CgroupV1SubsystemController::convertHierachicalLimitLine,
                                                                  CgroupSubsystem.LONG_RETVAL_UNLIMITED);
    }

    public static long convertHierachicalLimitLine(String line) {
        String[] tokens = line.split("\\s");
        if (tokens.length == 2) {
            String strVal = tokens[1];
            return CgroupV1SubsystemController.convertStringToLong(strVal);
        }
        return CgroupV1SubsystemController.UNLIMITED_MIN + 1; // unlimited
    }

}
