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
 * Base implementation of AclFileAttributeView
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

abstract class AbstractAclFileAttributeView
    implements AclFileAttributeView, DynamicFileAttributeView
{
    private static final String OWNER_NAME = "owner";
    private static final String ACL_NAME = "acl";

    @Override
    public final String name() {
        return "acl";
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void setAttribute(String attribute, Object value)
        throws IOException
    {
        if (attribute.equals(OWNER_NAME)) {
            setOwner((UserPrincipal)value);
            return;
        }
        if (attribute.equals(ACL_NAME)) {
            setAcl((List<AclEntry>)value);
            return;
        }
        throw new IllegalArgumentException("'" + name() + ":" +
            attribute + "' not recognized");
    }

    @Override
    public final Map<String,Object> readAttributes(String[] attributes)
        throws IOException
    {
        boolean acl = false;
        boolean owner = false;
        for (String attribute: attributes) {
            if (attribute.equals("*")) {
                owner = true;
                acl = true;
                continue;
            }
            if (attribute.equals(ACL_NAME)) {
                acl = true;
                continue;
            }
            if (attribute.equals(OWNER_NAME)) {
                owner = true;
                continue;
            }
            throw new IllegalArgumentException("'" + name() + ":" +
                attribute + "' not recognized");
        }
        Map<String,Object> result = new HashMap<>(2);
        if (acl)
            result.put(ACL_NAME, getAcl());
        if (owner)
            result.put(OWNER_NAME, getOwner());
        return Collections.unmodifiableMap(result);
    }
}
