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

package com.sun.jdi.event;

import com.sun.jdi.Method;

/**
 * Notification of a method invocation in the target VM. This event
 * occurs after entry into the invoked method and before any
 * code has executed.
 * Method entry events are generated for both native and non-native
 * methods.
 * <P>
 * In some VMs method entry events can occur for a particular thread
 * before its {@link ThreadStartEvent} occurs if methods are called
 * as part of the thread's initialization.
 *
 * @see EventQueue
 *
 */
public interface MethodEntryEvent extends LocatableEvent {

    /**
     * Returns the method that was entered.
     *
     * @return a {@link Method} which mirrors the method that was entered.
     */
    Method method();
}
