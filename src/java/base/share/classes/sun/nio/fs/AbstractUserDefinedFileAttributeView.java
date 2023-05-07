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

import java.nio.ByteBuffer;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.io.IOException;
import java.util.*;

/**
 * Base implementation of UserDefinedAttributeView
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

abstract class AbstractUserDefinedFileAttributeView
    implements UserDefinedFileAttributeView, DynamicFileAttributeView
{
    protected AbstractUserDefinedFileAttributeView() { }

    protected void checkAccess(String file,
                               boolean checkRead,
                               boolean checkWrite)
    {
        assert checkRead || checkWrite;
        @SuppressWarnings("removal")
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            if (checkRead)
                sm.checkRead(file);
            if (checkWrite)
                sm.checkWrite(file);
            sm.checkPermission(new RuntimePermission("accessUserDefinedAttributes"));
        }
    }

    @Override
    public final String name() {
        return "user";
    }

    @Override
    public final void setAttribute(String attribute, Object value)
        throws IOException
    {
        ByteBuffer bb;
        if (value instanceof byte[]) {
            bb = ByteBuffer.wrap((byte[])value);
        } else {
            bb = (ByteBuffer)value;
        }
        write(attribute, bb);
    }

    @Override
    public final Map<String,Object> readAttributes(String[] attributes)
        throws IOException
    {
        // names of attributes to return
        List<String> names = new ArrayList<>();
        for (String name: attributes) {
            if (name.equals("*")) {
                names = list();
                break;
            } else {
                if (name.isEmpty())
                    throw new IllegalArgumentException();
                names.add(name);
            }
        }

        // read each value and return in map
        Map<String,Object> result = new HashMap<>();
        for (String name: names) {
            int size = size(name);
            byte[] buf = new byte[size];
            int n = read(name, ByteBuffer.wrap(buf));
            byte[] value = (n == size) ? buf : Arrays.copyOf(buf, n);
            result.put(name, value);
        }
        return result;
    }
}
