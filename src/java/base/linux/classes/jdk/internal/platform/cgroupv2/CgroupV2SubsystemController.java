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

package java.base.linux.classes.jdk.internal.platform.cgroupv2;

import java.nio.file.Paths;

import java.base.linux.classes.jdk.internal.platform.CgroupSubsystem;
import java.base.linux.classes.jdk.internal.platform.CgroupSubsystemController;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

public class CgroupV2SubsystemController implements CgroupSubsystemController {

    private final String path;

    public CgroupV2SubsystemController(String mountPath, String cgroupPath) {
        this.path = Paths.get(mountPath, cgroupPath).toString();
    }

    @Override
    public String path() {
        return path;
    }

    public static long convertStringToLong(String strval) {
        return CgroupSubsystemController.convertStringToLong(strval,
                                                             CgroupSubsystem.LONG_RETVAL_UNLIMITED /* overflow retval */,
                                                             CgroupSubsystem.LONG_RETVAL_UNLIMITED /* default retval on error */);
    }

    public static long getLongEntry(CgroupSubsystemController controller, String param, String entryname) {
        return CgroupSubsystemController.getLongEntry(controller,
                                                      param,
                                                      entryname,
                                                      CgroupSubsystem.LONG_RETVAL_UNLIMITED /* retval on error */);
    }
}
