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

import java.io.File;
import java.io.FilePermission;
import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.util.Enumeration;

/**
 * This {@code PermissionCollection} implementation delegates to another
 * {@code PermissionCollection}, taking care to lazily add the permission needed
 * to read from the given {@code CodeSource} at first use, i.e., when either of
 * {@link #elements}, {@link #implies} or {@link #toString} is called, or when
 * the collection is serialized.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public final class LazyCodeSourcePermissionCollection
        extends PermissionCollection
{
    @java.io.Serial
    private static final long serialVersionUID = -6727011328946861783L;
    private final PermissionCollection perms;
    private final CodeSource cs;
    private volatile boolean permissionAdded;

    public LazyCodeSourcePermissionCollection(PermissionCollection perms,
                                              CodeSource cs) {
        this.perms = perms;
        this.cs = cs;
    }

    private void ensureAdded() {
        if (!permissionAdded) {
            synchronized(perms) {
                if (permissionAdded)
                    return;

                // open connection to determine the permission needed
                URL location = cs.getLocation();
                if (location != null) {
                    try {
                        Permission p = location.openConnection().getPermission();
                        if (p != null) {
                            // for directories then need recursive access
                            if (p instanceof FilePermission) {
                                String path = p.getName();
                                if (path.endsWith(File.separator)) {
                                    path += "-";
                                    p = new FilePermission(path,
                                            SecurityConstants.FILE_READ_ACTION);
                                }
                            }
                            perms.add(p);
                        }
                    } catch (IOException ioe) {
                    }
                }
                if (isReadOnly()) {
                    perms.setReadOnly();
                }
                permissionAdded = true;
            }
        }
    }

    @Override
    public void add(Permission permission) {
        if (isReadOnly())
            throw new SecurityException(
                    "attempt to add a Permission to a readonly PermissionCollection");
        perms.add(permission);
    }

    @Override
    public boolean implies(Permission permission) {
        ensureAdded();
        return perms.implies(permission);
    }

    @Override
    public Enumeration<Permission> elements() {
        ensureAdded();
        return perms.elements();
    }

    @Override
    public String toString() {
        ensureAdded();
        return perms.toString();
    }

    /**
     * On serialization, initialize and replace with the underlying
     * permissions. This removes the laziness on deserialization.
     */
    @java.io.Serial
    private Object writeReplace() {
        ensureAdded();
        return perms;
    }
}
