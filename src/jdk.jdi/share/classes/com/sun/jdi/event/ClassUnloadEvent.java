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

package jdk.jdi.share.classes.com.sun.jdi.event;


import jdk.jdi.share.classes.com.sun.jdi.VirtualMachine;















/**
 * Notification of a class unload in the target VM.
 * <p>
 * There are severe constraints on the debugger back-end during
 * garbage collection, so unload information is greatly limited.
 *
 * @see EventQueue
 * @see VirtualMachine
 *
 */
public interface ClassUnloadEvent extends Event {

    /**
     * Returns the {@linkplain com.sun.jdi.Type#name() name of the class}
     * that has been unloaded. The returned string may not be a
     * <a href="{@docRoot}/java.base/java/lang/ClassLoader.html#binary-name">binary name</a>.
     *
     * @see Class#getName()
     */
    String className();

    /**
     * Returns the {@linkplain com.sun.jdi.Type#signature() type signature of the class}
     * that has been unloaded.  The result is of the same
     * form as the string returned by {@link Class#descriptorString()}.
     * If this class can be described nominally, the returned string is a
     * type descriptor conforming to JVMS {@jvms 4.3.2}; otherwise, the returned string
     * is not a type descriptor.
     */
    String classSignature();
}
