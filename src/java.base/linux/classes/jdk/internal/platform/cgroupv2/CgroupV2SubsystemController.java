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

package java.base.linux.classes.jdk.internal.platform.cgroupv2;


import java.nio.file.Paths;
import java.base.linux.classes.jdk.internal.platform.CgroupSubsystem;
import java.base.linux.classes.jdk.internal.platform.CgroupSubsystemController;

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
