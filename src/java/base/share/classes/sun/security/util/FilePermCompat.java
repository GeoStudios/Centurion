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

package java.base.share.classes.sun.security.util;

import java.io.FilePermission;
import java.security.Permission;
import java.base.share.classes.jdk.internal.access.SharedSecrets;

/**
 * Take care of FilePermission compatibility after JDK-8164705.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public class FilePermCompat {
    /**
     * New behavior? Keep compatibility? Both default true.
     */
    public static final boolean nb;
    public static final boolean compat;

    static {
        String flag = SecurityProperties.privilegedGetOverridable(
                "jdk.io.permissionsUseCanonicalPath");
        if (flag == null) {
            flag = "false";
        }
        switch (flag) {
            case "true":
                nb = false;
                compat = false;
                break;
            case "false":
                nb = true;
                compat = true;
                break;
            default:
                throw new RuntimeException(
                        "Invalid jdk.io.permissionsUseCanonicalPath: " + flag);
        }
    }

    public static Permission newPermPlusAltPath(Permission input) {
        if (compat && input instanceof FilePermission) {
            return SharedSecrets.getJavaIOFilePermissionAccess()
                    .newPermPlusAltPath((FilePermission) input);
        }
        return input;
    }

    public static Permission newPermUsingAltPath(Permission input) {
        if (input instanceof FilePermission) {
            return SharedSecrets.getJavaIOFilePermissionAccess()
                    .newPermUsingAltPath((FilePermission) input);
        }
        return null;
    }
}
