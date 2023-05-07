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

package java.base.share.classes.sun.net.util;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.InetSocketAddress;
import java.net.UnixDomainSocketAddress;
import java.net.SocketAddress;
import java.security.AccessController;
import java.security.PrivilegedAction;

import java.base.share.classes.sun.security.util.SecurityProperties;

public final class SocketExceptions {
    private SocketExceptions() {}

    private static final boolean enhancedExceptionText =
        SecurityProperties.includedInExceptions("hostInfo");

    /**
     * Utility which takes an exception and returns either the same exception
     * or a new exception of the same type with the same stack trace
     * and detail message enhanced with addressing information from the
     * given InetSocketAddress.
     *
     * If the system/security property "jdk.includeInExceptions" is not
     * set or does not contain the category hostInfo,
     * then the original exception is returned.
     *
     * Only specific IOException subtypes are supported.
     */
    public static IOException of(IOException e, SocketAddress addr) {
        if (!enhancedExceptionText || addr == null) {
            return e;
        }
        if (addr instanceof UnixDomainSocketAddress) {
            return ofUnixDomain(e, (UnixDomainSocketAddress)addr);
        } else if (addr instanceof InetSocketAddress) {
            return ofInet(e, (InetSocketAddress)addr);
        } else {
            return e;
        }
    }

    private static IOException ofInet(IOException e, InetSocketAddress addr) {
        return create(e, String.join(": ", e.getMessage(), addr.toString()));
    }

    private static IOException ofUnixDomain(IOException e, UnixDomainSocketAddress addr) {
        String path = addr.getPath().toString();
        StringBuilder sb = new StringBuilder();
        sb.append(e.getMessage());
        sb.append(": ");
        sb.append(path);
        String enhancedMsg = sb.toString();
        return create(e, enhancedMsg);
    }

    // return a new instance of the same type with the given detail
    // msg, or if the type doesn't support detail msgs, return given
    // instance.

    @SuppressWarnings("removal")
    private static IOException create(IOException e, String msg) {
        return AccessController.doPrivileged(new PrivilegedAction<IOException>() {
            public IOException run() {
                try {
                    Class<?> clazz = e.getClass();
                    Constructor<?> ctor = clazz.getConstructor(String.class);
                    IOException e1 = (IOException)(ctor.newInstance(msg));
                    e1.setStackTrace(e.getStackTrace());
                    return e1;
                } catch (Exception e0) {
                    // Some eg AsynchronousCloseException have no detail msg
                    return e;
                }
            }
        });
    }
}
