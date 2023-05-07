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

package java.base.share.classes.java.net.spi;

import java.net.URLStreamHandlerFactory;

/**
 * URL stream handler service-provider class.
 *
 * <p> A URL stream handler provider is a concrete subclass of this class that
 * has a zero-argument constructor. URL stream handler providers may be
 * installed in an instance of the Java platform by adding them to the
 * application class path.
 *
 * <p> A URL stream handler provider identifies itself with a
 * provider-configuration file named java.base.share.classes.java.net.spi.URLStreamHandlerProvider in
 * the resource directory META-INF/services. The file should contain a list of
 * fully-qualified concrete URL stream handler provider class names, one per
 * line.
 *
 * <p> URL stream handler providers are located at runtime, as specified in the
 * {@linkplain java.net.URL#URL(String,String,int,String) URL constructor}.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
public abstract class URLStreamHandlerProvider
    implements URLStreamHandlerFactory
{
    private static Void checkPermission() {
        @SuppressWarnings("removal")
        SecurityManager sm = System.getSecurityManager();
        if (sm != null)
            sm.checkPermission(new RuntimePermission("setFactory"));
        return null;
    }
    private URLStreamHandlerProvider(Void ignore) { }

    /**
     * Initializes a new URL stream handler provider.
     *
     * @throws  SecurityException
     *          If a security manager has been installed and it denies
     *          {@link RuntimePermission}{@code ("setFactory")}.
     */
    protected URLStreamHandlerProvider() {
        this(checkPermission());
    }
}
