/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.jdk.internal.access;

import java.net.URI;

public interface JavaNetUriAccess {
    /**
     * Create a URI of pre-validated scheme and path.
     */
    URI create(String scheme, String path);
}
