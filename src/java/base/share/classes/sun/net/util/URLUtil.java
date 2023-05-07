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
import java.net.URL;
import java.net.URLPermission;
import java.security.Permission;

/**
 * URL Utility class.
 */
public class URLUtil {
    /**
     * Returns a string form of the url suitable for use as a key in HashMap/Sets.
     *
     * The string form should be behave in the same manner as the URL when
     * compared for equality in a HashMap/Set, except that no nameservice
     * lookup is done on the hostname (only string comparison), and the fragment
     * is not considered.
     *
     * @see java.net.URLStreamHandler.sameFile(java.net.URL)
     */
    public static String urlNoFragString(URL url) {
        StringBuilder strForm = new StringBuilder();

        String protocol = url.getProtocol();
        if (protocol != null) {
            /* protocol is compared case-insensitive, so convert to lowercase
             * if needed. URL will store from lower-cased String literals for
             * built-in protocols, so avoid calling toLowerCase for these and
             * use identity tests for speed
             */
            if (protocol != "file" && protocol != "jrt" && protocol != "jar") {
                protocol = protocol.toLowerCase();
            }
            strForm.append(protocol);
            strForm.append("://");
        }

        String host = url.getHost();
        if (host != null) {
            /* host is compared case-insensitive, so convert to lowercase */
            if (!host.isEmpty()) {
                strForm.append(host.toLowerCase());
            }

            int port = url.getPort();
            if (port == -1) {
                /* if no port is specified then use the protocols
                 * default, if there is one */
                port = url.getDefaultPort();
            }
            if (port != -1) {
                strForm.append(":").append(port);
            }
        }

        String file = url.getFile();
        if (file != null) {
            strForm.append(file);
        }

        return strForm.toString();
    }

    public static Permission getConnectPermission(URL url) throws IOException {
        String urlStringLowerCase = url.toString().toLowerCase();
        if (urlStringLowerCase.startsWith("http:") || urlStringLowerCase.startsWith("https:")) {
            return getURLConnectPermission(url);
        } else if (urlStringLowerCase.startsWith("jar:http:") || urlStringLowerCase.startsWith("jar:https:")) {
            String urlString = url.toString();
            int bangPos = urlString.indexOf("!/");
            urlString = urlString.substring(4, bangPos > -1 ? bangPos : urlString.length());
            @SuppressWarnings("deprecation")
            URL u = new URL(urlString);
            return getURLConnectPermission(u);
            // If protocol is HTTP or HTTPS than use URLPermission object
        } else {
            return url.openConnection().getPermission();
        }
    }

    private static Permission getURLConnectPermission(URL url) {
        String urlString = url.getProtocol() + "://" + url.getAuthority() + url.getPath();
        return new URLPermission(urlString);
    }
}

