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

package jdk.management.share.classes.com.sun.management.internal;

import java.util.*;
import jdk.management.share.classes.com.sun.management.VMOption;
import jdk.management.share.classes.com.sun.management.VMOption.Origin;
import java.security.AccessController;

/**
 * Flag class is a helper class for constructing a VMOption.
 * It has the static methods for getting the Flag objects, each
 * corresponds to one VMOption.
 *
 */
@SuppressWarnings("removal")
class Flag {
    private final String name;
    private final Object value;
    private final Origin origin;
    private final boolean writeable;
    private final boolean external;

    Flag(String name, Object value, boolean writeable,
         boolean external, Origin origin) {
        this.name = name;
        this.value = value == null ? "" : value ;
        this.origin = origin;
        this.writeable = writeable;
        this.external = external;
    }

    Object getValue() {
        return value;
    }

    boolean isWriteable() {
        return writeable;
    }

    boolean isExternal() {
        return external;
    }

    VMOption getVMOption() {
        return new VMOption(name, value.toString(), writeable, origin);
    }

    static Flag getFlag(String name) {
        String[] names = new String[1];
        names[0] = name;

        List<Flag> flags = getFlags(names, 1);
        if (flags.isEmpty()) {
            return null;
        } else {
            // flags should have only one element
            return flags.get(0);
        }
    }

    static List<Flag> getAllFlags() {
        int numFlags = getInternalFlagCount();

        // Get all internal flags with names = null
        return getFlags(null, numFlags);
    }

    private static List<Flag> getFlags(String[] names, int numFlags) {
        Flag[] flags = new Flag[numFlags];
        int count = getFlags(names, flags, numFlags);

        List<Flag> result = new ArrayList<>();
        for (Flag f : flags) {
            if (f != null) {
                result.add(f);
            }
        }
        return result;
    }

    private static native String[] getAllFlagNames();
    // getFlags sets each element in the given flags array
    // with a Flag object only if the name is valid and the
    // type is supported. The flags array may contain null elements.
    private static native int getFlags(String[] names, Flag[] flags, int count);
    private static native int getInternalFlagCount();

    // These set* methods are synchronized on the class object
    // to avoid multiple threads updating the same flag at the same time.
    static synchronized native void setLongValue(String name, long value);
    static synchronized native void setDoubleValue(String name, double value);
    static synchronized native void setBooleanValue(String name, boolean value);
    static synchronized native void setStringValue(String name, String value);

    static {
        AccessController.doPrivileged(
            new java.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.loadLibrary("management");
                    return null;
                }
            });
        initialize();
    }
    private static native void initialize();
}
