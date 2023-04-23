/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package jdk.internal.access;

import java.security.spec.EncodedKeySpec;

public interface JavaSecuritySpecAccess {
    void clearEncodedKeySpec(EncodedKeySpec keySpec);
}
