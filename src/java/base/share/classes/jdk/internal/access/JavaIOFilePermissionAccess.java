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
package java.base.share.classes.jdk.internal.access;

import java.io.FilePermission;

public interface JavaIOFilePermissionAccess {

    /**
     * Returns a new FilePermission plus an alternative path.
     *
     * @param input the input
     * @return the new FilePermission plus the alt path (as npath2)
     *         or the input itself if no alt path is available.
     */
    FilePermission newPermPlusAltPath(FilePermission input);

    /**
     * Returns a new FilePermission using an alternative path.
     *
     * @param input the input
     * @return the new FilePermission using the alt path (as npath)
     *         or null if no alt path is available
     */
    FilePermission newPermUsingAltPath(FilePermission input);
}
