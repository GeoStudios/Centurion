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


import jdk.jdi.share.classes.com.sun.jdi.Field;
import jdk.jdi.share.classes.com.sun.jdi.ObjectCollectedException;
import jdk.jdi.share.classes.com.sun.jdi.ObjectReference;
import jdk.jdi.share.classes.com.sun.jdi.Value;
import jdk.jdi.share.classes.com.sun.jdi.VirtualMachine;















/**
 * Notification of a field triggered event encountered by a thread in the
 * target VM.
 *
 * @see EventQueue
 * @see VirtualMachine
 *
 */
public interface WatchpointEvent extends LocatableEvent {

    /**
     * Returns the field that is about to be accessed/modified.
     *
     * @return a {@link Field} which mirrors the field
     * in the target VM.
     * @throws ObjectCollectedException may be thrown if class
     * has been garbage collected.
     */
    Field field();

    /**
     * Returns the object whose field is about to be accessed/modified.
     * Return null is the access is to a static field.
     *
     * @return a {@link ObjectReference} which mirrors the event's
     * object in the target VM.
     */
    ObjectReference object();

    /**
     * Current value of the field.
     * @throws ObjectCollectedException if object or class have been
     * garbage collected.
     */
    Value valueCurrent();
}
