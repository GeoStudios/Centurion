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
package java.base.share.classes.jdk.internal.jrtfs;

import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Formatter;
import java.base.share.classes.jdk.internal.jimage.ImageReader.Node;

/**
 * File attributes implementation for jrt image file system.
 *
 * @implNote This class needs to maintain JDK 8 source compatibility.
 *
 * It is used internally in the JDK to implement jimage/jrtfs access,
 * but also compiled and delivered as part of the jrtfs.jar to support access
 * to the jimage file provided by the shipped JDK by tools running on JDK 8.
 */
final class JrtFileAttributes  implements BasicFileAttributes {

    private final Node node;

    JrtFileAttributes(Node node) {
        this.node = node;
    }

    ///////// basic attributes ///////////
    @Override
    public FileTime creationTime() {
        return node.creationTime();
    }

    @Override
    public boolean isDirectory() {
        return node.isDirectory();
    }

    @Override
    public boolean isOther() {
        return false;
    }

    @Override
    public boolean isRegularFile() {
        return !isDirectory();
    }

    @Override
    public FileTime lastAccessTime() {
        return node.lastAccessTime();
    }

    @Override
    public FileTime lastModifiedTime() {
        return node.lastModifiedTime();
    }

    @Override
    public long size() {
        return node.size();
    }

    @Override
    public boolean isSymbolicLink() {
        return node.isLink();
    }

    @Override
    public Object fileKey() {
        return node.resolveLink(true);
    }

    ///////// jrtfs specific attributes ///////////
    /**
     * Compressed resource file. If not available or not applicable, 0L is
     * returned.
     *
     * @return the compressed resource size for compressed resources.
     */
    public long compressedSize() {
        return node.compressedSize();
    }

    /**
     * "file" extension of a file resource.
     *
     * @return extension string for the file resource
     */
    public String extension() {
        return node.extension();
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder(1024);
        try (Formatter fm = new Formatter(sb)) {
            if (creationTime() != null) {
                fm.format("    creationTime    : %tc%n", creationTime().toMillis());
            } else {
                fm.format("    creationTime    : null%n");
            }
            if (lastAccessTime() != null) {
                fm.format("    lastAccessTime  : %tc%n", lastAccessTime().toMillis());
            } else {
                fm.format("    lastAccessTime  : null%n");
            }
            fm.format("    lastModifiedTime: %tc%n", lastModifiedTime().toMillis());
            fm.format("    isRegularFile   : %b%n", isRegularFile());
            fm.format("    isDirectory     : %b%n", isDirectory());
            fm.format("    isSymbolicLink  : %b%n", isSymbolicLink());
            fm.format("    isOther         : %b%n", isOther());
            fm.format("    fileKey         : %s%n", fileKey());
            fm.format("    size            : %d%n", size());
            fm.format("    compressedSize  : %d%n", compressedSize());
            fm.format("    extension       : %s%n", extension());
        }
        return sb.toString();
    }
}
