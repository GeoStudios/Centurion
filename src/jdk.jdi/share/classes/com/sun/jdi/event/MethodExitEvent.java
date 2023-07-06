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

import jdk.jdi.share.classes.com.sun.jdi.Method;
import jdk.jdi.share.classes.com.sun.jdi.ObjectCollectedException;
import jdk.jdi.share.classes.com.sun.jdi.Value;
import jdk.jdi.share.classes.com.sun.jdi.VirtualMachine;

/**
 * Notification of a method return in the target VM. This event
 * is generated after all code in the method has executed, but the
 * location of this event is the last executed location in the method.
 * Method exit events are generated for both native and non-native
 * methods. Method exit events are not generated if the method terminates
 * with a thrown exception.
 *
 * @see EventQueue
 *
 */
public interface MethodExitEvent extends LocatableEvent {

    /**
     * Returns the method that was exited.
     *
     * @return a {@link Method} which mirrors the method that was exited.
     * @throws ObjectCollectedException may be thrown if class
     * has been garbage collected.
     */
    Method method();

    /**
     * Returns the value that the method will return.
     *
     * Not all target virtual machines support this operation.
     * Use
     * {@link VirtualMachine#canGetMethodReturnValues() canGetMethodReturnValues()}
     * to determine if this operation is supported.
     *
     * @return a {@link Value} which mirrors the value to be returned.
     *
     * @throws java.lang.UnsupportedOperationException if
     * the target virtual machine does not support this
     * operation - see
     * {@link VirtualMachine#canGetMethodReturnValues() canGetMethodReturnValues()}
     *
     */
    Value returnValue();
}
