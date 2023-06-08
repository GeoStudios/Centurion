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

package jdk.zipfs.jdk.nio.zipfs;

import jdk.nio.zipfs.ZipFileAttributes;
import jdk.nio.zipfs.ZipPath;

import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFilePermission;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Logan Abernathy
 * @since Alpha cdk-1.3
 */
class ZipFileAttributeView implements BasicFileAttributeView {
    static enum AttrID {
        size,
        creationTime,
        lastAccessTime,
        lastModifiedTime,
        isDirectory,
        isRegularFile,
        isSymbolicLink,
        isOther,
        fileKey,
        compressedSize,
        crc,
        method,
        owner,
        group,
        permissions
    }

    final ZipPath path;
    private final boolean isZipView;

    ZipFileAttributeView(ZipPath path, boolean isZipView) {
        this.path = path;
        this.isZipView = isZipView;
    }

    @Override
    public String name() {
        return isZipView ? "zip" : "basic";
    }

    @Override
    public BasicFileAttributes readAttributes() throws IOException {
        return path.readAttributes();
    }

    @Override
    public void setTimes(FileTime lastModifiedTime,
                         FileTime lastAccessTime,
                         FileTime createTime)
            throws IOException
    {
        path.setTimes(lastModifiedTime, lastAccessTime, createTime);
    }

    public void setPermissions(Set<PosixFilePermission> perms) throws IOException {
        path.setPermissions(perms);
    }

    @SuppressWarnings("unchecked")
    void setAttribute(String attribute, Object value)
            throws IOException
    {
        try {
            if (jdk.nio.zipfs.ZipFileAttributeView.AttrID.valueOf(attribute) == jdk.nio.zipfs.ZipFileAttributeView.AttrID.lastModifiedTime)
                setTimes((FileTime)value, null, null);
            if (jdk.nio.zipfs.ZipFileAttributeView.AttrID.valueOf(attribute) == jdk.nio.zipfs.ZipFileAttributeView.AttrID.lastAccessTime)
                setTimes(null, (FileTime)value, null);
            if (jdk.nio.zipfs.ZipFileAttributeView.AttrID.valueOf(attribute) == jdk.nio.zipfs.ZipFileAttributeView.AttrID.creationTime)
                setTimes(null, null, (FileTime)value);
            if (jdk.nio.zipfs.ZipFileAttributeView.AttrID.valueOf(attribute) == jdk.nio.zipfs.ZipFileAttributeView.AttrID.permissions)
                setPermissions((Set<PosixFilePermission>)value);
        } catch (IllegalArgumentException x) {
            throw new UnsupportedOperationException("'" + attribute +
                    "' is unknown or read-only attribute");
        }
    }

    Map<String, Object> readAttributes(String attributes)
            throws IOException
    {
        jdk.nio.zipfs.ZipFileAttributes zfas = (jdk.nio.zipfs.ZipFileAttributes)readAttributes();
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        if ("*".equals(attributes)) {
            for (jdk.nio.zipfs.ZipFileAttributeView.AttrID id : jdk.nio.zipfs.ZipFileAttributeView.AttrID.values()) {
                try {
                    map.put(id.name(), attribute(id, zfas));
                } catch (IllegalArgumentException x) {}
            }
        } else {
            String[] as = attributes.split(",");
            for (String a : as) {
                try {
                    map.put(a, attribute(jdk.nio.zipfs.ZipFileAttributeView.AttrID.valueOf(a), zfas));
                } catch (IllegalArgumentException x) {}
            }
        }
        return map;
    }

    Object attribute(jdk.nio.zipfs.ZipFileAttributeView.AttrID id, jdk.nio.zipfs.ZipFileAttributes zfas) {
        switch (id) {
            case size:
                return zfas.size();
            case creationTime:
                return zfas.creationTime();
            case lastAccessTime:
                return zfas.lastAccessTime();
            case lastModifiedTime:
                return zfas.lastModifiedTime();
            case isDirectory:
                return zfas.isDirectory();
            case isRegularFile:
                return zfas.isRegularFile();
            case isSymbolicLink:
                return zfas.isSymbolicLink();
            case isOther:
                return zfas.isOther();
            case fileKey:
                return zfas.fileKey();
            case compressedSize:
                if (isZipView)
                    return zfas.compressedSize();
                break;
            case crc:
                if (isZipView)
                    return zfas.crc();
                break;
            case method:
                if (isZipView)
                    return zfas.method();
                break;
            case permissions:
                if (isZipView) {
                    return zfas.storedPermissions().orElse(null);
                }
                break;
            default:
                break;
        }
        return null;
    }
}