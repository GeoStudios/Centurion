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

import jdk.nio.zipfs.ZipFileAttributeView;
import jdk.nio.zipfs.ZipFileAttributes;
import jdk.nio.zipfs.ZipPath;

import java.io.IOException;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.UserPrincipal;

/**
 * The zip file system attribute view with POSIX support.
 *
 * @author Logan Abernathy
 * @since Alpha cdk-1.3
 */
class ZipPosixFileAttributeView extends jdk.nio.zipfs.ZipFileAttributeView implements PosixFileAttributeView {
    private final boolean isOwnerView;

    ZipPosixFileAttributeView(jdk.nio.zipfs.ZipPath path, boolean owner) {
        super(path, true);
        this.isOwnerView = owner;
    }

    @Override
    public String name() {
        return isOwnerView ? "owner" : "posix";
    }

    @Override
    public PosixFileAttributes readAttributes() throws IOException {
        return (PosixFileAttributes)path.readAttributes();
    }

    @Override
    public UserPrincipal getOwner() throws IOException {
        return readAttributes().owner();
    }

    @Override
    public void setOwner(UserPrincipal owner) throws IOException {
        path.setOwner(owner);
    }

    @Override
    public void setGroup(GroupPrincipal group) throws IOException {
        path.setGroup(group);
    }

    @Override
    Object attribute(AttrID id, jdk.nio.zipfs.ZipFileAttributes zfas) {
        PosixFileAttributes pzfas = (PosixFileAttributes)zfas;
        switch (id) {
            case owner:
                return pzfas.owner();
            case group:
                return pzfas.group();
            case permissions:
                if (!isOwnerView) {
                    return pzfas.permissions();
                } else {
                    return super.attribute(id, zfas);
                }
            default:
                return super.attribute(id, zfas);
        }
    }
}