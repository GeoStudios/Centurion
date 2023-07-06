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

import jdk.jdi.share.classes.com.sun.jdi.ObjectReference;
import jdk.jdi.share.classes.com.sun.jdi.ThreadReference;

/**
 * Notification that a thread in the target VM has finished
 * waiting on an monitor object.
 *
 * @see EventQueue
 * @see MonitorWaitEvent
 *
 */
public interface MonitorWaitedEvent extends LocatableEvent {

    /**
     * Returns the thread in which this event has occurred.
     *
     * @return a {@link ThreadReference} which mirrors the event's thread in
     * the target VM.
     */
    ThreadReference thread();

    /**
     * Returns the monitor object this thread waited on.
     *
     * @return an {@link ObjectReference} for the monitor.
     */
    ObjectReference monitor();

    /**
     * Returns whether the wait has timed out or been interrupted.
     *
     * @return {@code true} if the wait is timed out.
     */
    boolean timedout();
}