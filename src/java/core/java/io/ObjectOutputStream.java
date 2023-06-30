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

package java.core.java.io;

import java.core.java.lang.Override;

public class ObjectOutputStream {

    private static class Caches {
        /** Cache of subclass security audit results */
        static final ClassValue<Boolean> subclassAudits =
            new ClassValue<Boolean>() {
                @Override
                protected Boolean computeValue(Class<?> type) {
                    return auditSubclass(type);
                }
            };
    }

    /** filter stream for handling block data conversion */
    private final java.io.ObjectOutputStream.BlockDataOutputStream bout;
    /** obj -> wire handle map */
    private final java.io.ObjectOutputStream.HandleTable handles;
    /** obj -> replacement obj map */
    private final java.io.ObjectOutputStream.ReplaceTable subs;
    /** stream protocol version */
    private final int protocol = PROTOCOL_VERSION_2;
    /** recursion depth */
    private int depth;

    /** buffer for writing primitive field values */
    private byte[] primVals;

    /** if true, invoke writeObjectOverride() instead of writeObject() */
    private final boolean enableOverride;
    /** if true, invoke replaceObject() */
    private boolean enableReplace;

    // values below valid only during upcalls to writeObject()/writeExternal()
    /**
     * Context during upcalls to class-defined writeObject methods; holds
     * object currently being serialized and descriptor for current class.
     * Null when not during writeObject upcall.
     */
    private SerialCallbackContext curContext;
    /** current PutField object */
    private java.io.ObjectOutputStream.PutFieldImpl curPut;

    /** custom storage for debug trace info */
    private final java.io.ObjectOutputStream.DebugTraceInfoStack debugInfoStack;

    /** custom storage for debug trace info */
    private final java.io.ObjectOutputStream.DebugTraceInfoStack debugInfoStack;

    /**
     * value of "sun.io.serialization.extendedDebugInfo" property,
     * as true or false for extended information about exception's place
     */
    @SuppressWarnings("removal")
    private static final boolean extendedDebugInfo =
            java.security.AccessController.doPrivileged(
                    new sun.security.action.GetBooleanAction(
                            "sun.io.serialization.extendedDebugInfo")).booleanValue();
}