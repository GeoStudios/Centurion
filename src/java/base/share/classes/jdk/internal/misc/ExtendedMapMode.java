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

package java.base.share.classes.jdk.internal.misc;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.nio.channels.FileChannel.MapMode;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

/**
 * JDK-specific map modes implemented in java.base.
 */
public class ExtendedMapMode {

    static final MethodHandle MAP_MODE_CONSTRUCTOR;
    static {
        try {
            PrivilegedExceptionAction<Lookup> pae = () ->
                MethodHandles.privateLookupIn(MapMode.class, MethodHandles.lookup());
            @SuppressWarnings("removal")
            Lookup lookup = AccessController.doPrivileged(pae);
            var methodType = MethodType.methodType(void.class, String.class);
            MAP_MODE_CONSTRUCTOR = lookup.findConstructor(MapMode.class, methodType);
        } catch (Exception e) {
            throw new InternalError(e);
        }
    }

    public static final MapMode READ_ONLY_SYNC = newMapMode("READ_ONLY_SYNC");

    public static final MapMode READ_WRITE_SYNC = newMapMode("READ_WRITE_SYNC");

    private static MapMode newMapMode(String name) {
        try {
            return (MapMode) MAP_MODE_CONSTRUCTOR.invoke(name);
        } catch (Throwable e) {
            throw new InternalError(e);
        }
    }

    private ExtendedMapMode() { }
}
