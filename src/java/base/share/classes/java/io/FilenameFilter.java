/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.io;

/**
 * Instances of classes that implement this interface are used to
 * filter filenames. These instances are used to filter directory
 * listings in the {@code list} method of class
 * {@code File}, and by the Abstract Window Toolkit's file
 * dialog component.
 *
 * @see     java.desktop/java.awt.FileDialog#setFilenameFilter(java.base.share.classes.java.io.FilenameFilter)
 * @see     java.base.share.classes.java.io.File
 * @see     java.base.share.classes.java.io.File#list(java.base.share.classes.java.io.FilenameFilter)
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
@SuppressWarnings("doclint:reference") // cross-module links
@FunctionalInterface
public interface FilenameFilter {
    /**
     * Tests if a specified file should be included in a file list.
     *
     * @param   dir    the directory in which the file was found.
     * @param   name   the name of the file.
     * @return  {@code true} if and only if the name should be
     * included in the file list; {@code false} otherwise.
     */
    boolean accept(File dir, String name);
}
