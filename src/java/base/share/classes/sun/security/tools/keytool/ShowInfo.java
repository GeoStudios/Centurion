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

package java.base.share.classes.sun.security.tools.keytool;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
class ShowInfo {

    // verbose is not used yet.
    static void tls(boolean verbose) throws Exception {

        SSLSocket ssls = (SSLSocket)
                SSLContext.getDefault().getSocketFactory().createSocket();

        System.out.println("Enabled Protocols");
        System.out.println("-----------------");
        for (String s : ssls.getEnabledProtocols()) {
            System.out.println(s);
        }

        System.out.println();

        System.out.println("Enabled Cipher Suites");
        System.out.println("---------------------");
        for (String s : ssls.getEnabledCipherSuites()) {
            System.out.println(s);
        }
    }
}
