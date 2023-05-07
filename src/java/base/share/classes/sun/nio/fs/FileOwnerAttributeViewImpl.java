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

package java.base.share.classes.sun.nio.fs;

import java.nio.file.attribute.*;
import java.util.*;
import java.io.IOException;

/**
 * An implementation of FileOwnerAttributeView that delegates to a given
 * PosixFileAttributeView or AclFileAttributeView object.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

final class FileOwnerAttributeViewImpl
    implements FileOwnerAttributeView, DynamicFileAttributeView
{
    private static final String OWNER_NAME = "owner";

    private final FileAttributeView view;
    private final boolean isPosixView;

    FileOwnerAttributeViewImpl(PosixFileAttributeView view) {
        this.view = view;
        this.isPosixView = true;
    }

    FileOwnerAttributeViewImpl(AclFileAttributeView view) {
        this.view = view;
        this.isPosixView = false;
    }

    @Override
    public String name() {
        return "owner";
    }

    @Override
    public void setAttribute(String attribute, Object value)
        throws IOException
    {
        if (attribute.equals(OWNER_NAME)) {
            setOwner((UserPrincipal)value);
        } else {
            throw new IllegalArgumentException("'" + name() + ":" +
                attribute + "' not recognized");
        }
    }

    @Override
    public Map<String,Object> readAttributes(String[] attributes) throws IOException {
        Map<String,Object> result = new HashMap<>();
        for (String attribute: attributes) {
            if (attribute.equals("*") || attribute.equals(OWNER_NAME)) {
                result.put(OWNER_NAME, getOwner());
            } else {
                throw new IllegalArgumentException("'" + name() + ":" +
                    attribute + "' not recognized");
            }
        }
        return result;
    }

    @Override
    public UserPrincipal getOwner() throws IOException {
        if (isPosixView) {
            return ((PosixFileAttributeView)view).readAttributes().owner();
        } else {
            return ((AclFileAttributeView)view).getOwner();
        }
    }

    @Override
    public void setOwner(UserPrincipal owner)
        throws IOException
    {
        if (isPosixView) {
            ((PosixFileAttributeView)view).setOwner(owner);
        } else {
            ((AclFileAttributeView)view).setOwner(owner);
        }
    }
 }
