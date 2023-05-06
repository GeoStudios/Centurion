/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.unix.classes.jdk.internal.loader;

import java.net.URL;
import java.io.File;
import java.base.share.classes.sun.net.www.ParseUtil;

/**
 * (Solaris) platform specific handling for file: URLs .
 * urls must not contain a hostname in the authority field
 * other than "localhost".
 *
 * This implementation could be updated to map such URLs
 * on to /net/host/...
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

public class FileURLMapper {

    URL url;
    String path;

    public FileURLMapper (URL url) {
        this.url = url;
    }

    /**
     * @return the platform specific path corresponding to the URL
     *  so long as the URL does not contain a hostname in the authority field.
     */

    public String getPath () {
        if (path != null) {
            return path;
        }
        String host = url.getHost();
        if (host == null || host.isEmpty() || "localhost".equalsIgnoreCase(host)) {
            path = url.getFile();
            path = ParseUtil.decode(path);
        }
        return path;
    }

    /**
     * Checks whether the file identified by the URL exists.
     */
    public boolean exists () {
        String s = getPath ();
        if (s == null) {
            return false;
        } else {
            File f = new File (s);
            return f.exists();
        }
    }
}
