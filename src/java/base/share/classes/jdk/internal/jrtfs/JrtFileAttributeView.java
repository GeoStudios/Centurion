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

import java.nio.file.LinkOption;
import java.nio.file.attribute.*;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * File attribute view for jrt file system.
 *
 * @implNote This class needs to maintain JDK 8 source compatibility.
 *
 * It is used internally in the JDK to implement jimage/jrtfs access,
 * but also compiled and delivered as part of the jrtfs.jar to support access
 * to the jimage file provided by the shipped JDK by tools running on JDK 8.
 */
final class JrtFileAttributeView implements BasicFileAttributeView {

    private static enum AttrID {
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
        extension
    };

    private final JrtPath path;
    private final boolean isJrtView;
    private final LinkOption[] options;

    private JrtFileAttributeView(JrtPath path, boolean isJrtView, LinkOption... options) {
        this.path = path;
        this.isJrtView = isJrtView;
        this.options = options;
    }

    @SuppressWarnings("unchecked") // Cast to V
    static <V extends FileAttributeView> V get(JrtPath path, Class<V> type, LinkOption... options) {
        Objects.requireNonNull(type);
        if (type == BasicFileAttributeView.class) {
            return (V) new JrtFileAttributeView(path, false, options);
        }
        if (type == JrtFileAttributeView.class) {
            return (V) new JrtFileAttributeView(path, true, options);
        }
        return null;
    }

    static JrtFileAttributeView get(JrtPath path, String type, LinkOption... options) {
        Objects.requireNonNull(type);
        if (type.equals("basic")) {
            return new JrtFileAttributeView(path, false, options);
        }
        if (type.equals("jrt")) {
            return new JrtFileAttributeView(path, true, options);
        }
        return null;
    }

    @Override
    public String name() {
        return isJrtView ? "jrt" : "basic";
    }

    @Override
    public JrtFileAttributes readAttributes() throws IOException {
        return path.getAttributes(options);
    }

    @Override
    public void setTimes(FileTime lastModifiedTime,
                         FileTime lastAccessTime,
                         FileTime createTime) throws IOException {
        path.setTimes(lastModifiedTime, lastAccessTime, createTime);
    }

    static void setAttribute(JrtPath path, String attribute, Object value)
            throws IOException {
        int colonPos = attribute.indexOf(':');
        if (colonPos != -1) {    // type = "basic", if no ":"
            String type = attribute.substring(0, colonPos++);
            if (!type.equals("basic") && !type.equals("jrt")) {
                throw new UnsupportedOperationException(
                    "view <" + type + "> is not supported");
            }
            attribute = attribute.substring(colonPos);
        }
        try {
            AttrID id = AttrID.valueOf(attribute);
            if (id == AttrID.lastModifiedTime) {
                path.setTimes((FileTime) value, null, null);
            } else if (id == AttrID.lastAccessTime) {
                path.setTimes(null, (FileTime) value, null);
            } else if (id == AttrID.creationTime) {
                path.setTimes(null, null, (FileTime) value);
            }
            return;
        } catch (IllegalArgumentException x) {}
        throw new UnsupportedOperationException("'" + attribute
                + "' is unknown or read-only attribute");
    }

    static Map<String, Object> readAttributes(JrtPath path, String attributes,
                                              LinkOption... options)
            throws IOException {
        int colonPos = attributes.indexOf(':');
        boolean isJrtView = false;
        if (colonPos != -1) {    // type = "basic", if no ":"
            String type = attributes.substring(0, colonPos++);
            if (!type.equals("basic") && !type.equals("jrt")) {
                throw new UnsupportedOperationException("view <" + type +
                                                        "> is not supported");
            }
            isJrtView = true;
            attributes = attributes.substring(colonPos);
        }
        JrtFileAttributes jrtfas = path.getAttributes();
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        if ("*".equals(attributes)) {
            for (AttrID id : AttrID.values()) {
                map.put(id.name(), attribute(id, jrtfas, isJrtView));
            }
        } else {
            String[] as = attributes.split(",");
            for (String a : as) {
                //throw IllegalArgumentException
                map.put(a, attribute(AttrID.valueOf(a), jrtfas, isJrtView));
            }
        }
        return map;
    }

    static Object attribute(AttrID id, JrtFileAttributes jrtfas, boolean isJrtView) {
        switch (id) {
            case size:
                return jrtfas.size();
            case creationTime:
                return jrtfas.creationTime();
            case lastAccessTime:
                return jrtfas.lastAccessTime();
            case lastModifiedTime:
                return jrtfas.lastModifiedTime();
            case isDirectory:
                return jrtfas.isDirectory();
            case isRegularFile:
                return jrtfas.isRegularFile();
            case isSymbolicLink:
                return jrtfas.isSymbolicLink();
            case isOther:
                return jrtfas.isOther();
            case fileKey:
                return jrtfas.fileKey();
            case compressedSize:
                if (isJrtView) {
                    return jrtfas.compressedSize();
                }
                break;
            case extension:
                if (isJrtView) {
                    return jrtfas.extension();
                }
                break;
        }
        return null;
    }
}
