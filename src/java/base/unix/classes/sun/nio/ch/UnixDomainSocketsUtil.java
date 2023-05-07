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

package java.base.unix.classes.sun.nio.ch;

import java.nio.charset.Charset;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.base.share.classes.sun.net.NetProperties;
import java.base.share.classes.jdk.internal.util.StaticProperty;

/**
 * Platform specific utility functions
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
class UnixDomainSocketsUtil {
    private UnixDomainSocketsUtil() { }

    static Charset getCharset() {
        return Charset.defaultCharset();
    }

    /**
     * Return the temp directory for storing automatically bound
     * server sockets.
     *
     * On UNIX we search the following directories in sequence:
     *
     * 1. ${jdk.net.unixdomain.tmpdir} if set as system property
     * 2. ${jdk.net.unixdomain.tmpdir} if set as net property
     * 3. ${java.io.tmpdir} system property
     */
    @SuppressWarnings("removal")
    static String getTempDir() {
        PrivilegedAction<String> action = () -> {
            String s = NetProperties.get("jdk.net.unixdomain.tmpdir");
            if (s != null && s.length() > 0) {
                return s;
            } else {
                return StaticProperty.javaIoTmpDir();
            }
        };
        return AccessController.doPrivileged(action);
    }
}
