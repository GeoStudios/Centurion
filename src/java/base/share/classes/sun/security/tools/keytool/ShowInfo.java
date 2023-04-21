/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.tools.keytool;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;

/**
 * @since Pre Java 1
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
