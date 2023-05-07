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

package java.base.windows.classes.sun.nio.ch;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.base.share.classes.sun.net.NetProperties;
import java.base.share.classes.jdk.internal.util.StaticProperty;

/*
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 19/4/2023 
 */

class UnixDomainSocketsUtil {
    private UnixDomainSocketsUtil() { }

    static Charset getCharset() {
        return StandardCharsets.UTF_8;
    }

    /**
     * Return the temp directory for storing automatically bound
     * server sockets.
     *
     * On Windows we search the following directories in sequence:
     *
     * 1. ${jdk.net.unixdomain.tmpdir} if set as system property
     * 2. ${jdk.net.unixdomain.tmpdir} if set as net property
     * 3. %TEMP%
     * 4. ${java.io.tmpdir}
     */
    @SuppressWarnings("removal")
    static String getTempDir() {
        PrivilegedAction<String> action = () -> {
            String s = NetProperties.get("jdk.net.unixdomain.tmpdir");
            if (s != null) {
                return s;
            }
            String temp = System.getenv("TEMP");
            if (temp != null) {
                return temp;
            }
            return StaticProperty.javaIoTmpDir();
        };
        return AccessController.doPrivileged(action);
    }
}