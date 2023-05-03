/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.action;

import java.io.*;

import java.security.PrivilegedExceptionAction;

/**
 * A convenience class for opening a FileInputStream as a privileged action.
 *
 * @since Java 2
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public class OpenFileInputStreamAction
        implements PrivilegedExceptionAction<FileInputStream> {

    private final File file;

    public OpenFileInputStreamAction(File file) {
        this.file = file;
    }

    public OpenFileInputStreamAction(String filename) {
        this.file = new File(filename);
    }

    public FileInputStream run() throws Exception {
        return new FileInputStream(file);
    }
}
