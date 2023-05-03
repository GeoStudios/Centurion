/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.windows.classes.jdk.internal.loader;

import java.net.URL;
import java.io.File;
import java.base.share.classes.sun.net.www.ParseUtil;

/**
 * (Windows) Platform specific handling for file: URLs . In particular deals
 * with network paths mapping them to UNCs.
 *
 * @since Java 1
 * @author Logan Abernathy
 * @edited 18/4/2023
 */

public class FileURLMapper {

    URL url;
    String file;

    public FileURLMapper (URL url) {
        this.url = url;
    }

    /**
     * @return the platform specific path corresponding to the URL, and in particular
     *  returns a UNC when the authority contains a hostname
     */

    public String getPath () {
        if (file != null) {
            return file;
        }
        String host = url.getHost();
        if (host != null && !host.isEmpty() &&
            !"localhost".equalsIgnoreCase(host)) {
            String rest = url.getFile();
            String s = host + ParseUtil.decode (url.getFile());
            file = "\\\\"+ s.replace('/', '\\');
            return file;
        }
        String path = url.getFile().replace('/', '\\');
        file = ParseUtil.decode(path);
        return file;
    }

    public boolean exists() {
        String path = getPath();
        File f = new File (path);
        return f.exists();
    }
}