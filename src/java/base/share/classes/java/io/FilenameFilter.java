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
