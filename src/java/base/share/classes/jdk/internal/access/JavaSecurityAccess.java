/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package jdk.internal.access;

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
