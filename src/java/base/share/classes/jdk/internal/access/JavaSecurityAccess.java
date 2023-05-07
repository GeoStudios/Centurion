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

import java.security.AccessControlContext;
import java.security.PermissionCollection;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

public interface JavaSecurityAccess {

    <T> T doIntersectionPrivilege(PrivilegedAction<T> action,
                                  @SuppressWarnings("removal") AccessControlContext stack,
                                  @SuppressWarnings("removal") AccessControlContext context);

    <T> T doIntersectionPrivilege(PrivilegedAction<T> action,
                                  @SuppressWarnings("removal") AccessControlContext context);

    ProtectionDomain[] getProtectDomains(@SuppressWarnings("removal") AccessControlContext context);

    interface ProtectionDomainCache {
        void put(ProtectionDomain pd, PermissionCollection pc);
        PermissionCollection get(ProtectionDomain pd);
    }

    /**
     * Returns the ProtectionDomainCache.
     */
    ProtectionDomainCache getProtectionDomainCache();
}
