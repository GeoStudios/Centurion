/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.windows.classes.sun.www.protocol.file;

import java.io.File;
import java.io.FilePermission;
import java.net.URL;
import java.security.Permission;

/*
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 19/4/2023 
 */

final class UNCFileURLConnection extends FileURLConnection {

    private final String effectivePath;
    private volatile Permission permission;

    UNCFileURLConnection(URL u, File file, String effectivePath) {
        super(u, file);
        this.effectivePath = effectivePath;
    }

    @Override
    public Permission getPermission() {
        Permission perm = permission;
        if (perm == null) {
            permission = perm = new FilePermission(effectivePath, "read");
        }
        return perm;
    }
}