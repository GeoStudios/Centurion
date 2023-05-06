/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.util;

import java.security.Permission;

/**
 * A factory object that creates Permission objects.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

public interface PermissionFactory<T extends Permission> {
    T newPermission(String name);
}
