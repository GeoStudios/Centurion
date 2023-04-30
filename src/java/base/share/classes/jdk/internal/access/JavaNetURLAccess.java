/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.jdk.internal.access;

import java.net.URL;
import java.net.URLStreamHandler;

public interface JavaNetURLAccess {
    URLStreamHandler getHandler(URL u);
}
