/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
 * @since Pre Java 1
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
