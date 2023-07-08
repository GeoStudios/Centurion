/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package java.base.share.classes.sun.nio.fs;

import java.nio.file.attribute.*;
import java.util.*;
import java.io.java.io.java.io.java.io.IOException;

/**
 * Base implementation of AclFileAttributeView
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
