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

package jdk.jdi.share.classes.com.sun.jdi.request;

import jdk.jdi.share.classes.com.sun.jdi.ObjectReference;
import jdk.jdi.share.classes.com.sun.jdi.ReferenceType;
import jdk.jdi.share.classes.com.sun.jdi.ThreadReference;
import jdk.jdi.share.classes.com.sun.jdi.VirtualMachine;
import jdk.jdi.share.classes.com.sun.jdi.event.EventQueue;
import jdk.jdi.share.classes.com.sun.jdi.event.EventSet;
import jdk.jdi.share.classes.com.sun.jdi.event.MonitorWaitedEvent;

/**
 * Request for notification when a thread in the target VM has finished waiting on
 * a monitor object. That is, a thread is leaving Object.wait(). "
 * When an enabled MonitorWaitedRequest is satisfied, an
 * {@link EventSet event set} containing a
 * {@link MonitorWaitedEvent MonitorWaitedEvent}
 * will be placed on the {@link EventQueue EventQueue}.
 * The collection of existing MonitorWaitedEvents is
 * managed by the {@link EventRequestManager}
 *
 * @see MonitorWaitedEvent
 * @see EventQueue
 * @see EventRequestManager
 *
 */
public interface MonitorWaitedRequest extends EventRequest {

    /**
     * Restricts the events generated by this request to those in
     * the given thread.
     * @param thread the thread to filter on.
     * @throws InvalidRequestStateException if this request is currently
     * enabled or has been deleted.
     * Filters may be added only to disabled requests.
     */
    void addThreadFilter(ThreadReference thread);

    /**
     * Restricts the events generated by this request to those whose
     * monitor object is of the given reference type or any of
     * its subtypes.
     *
     * @param refType the reference type to filter on.
     * @throws InvalidRequestStateException if this request is currently
     * enabled or has been deleted.
     * Filters may be added only to disabled requests.
     */
    void addClassFilter(ReferenceType refType);

    /**
     * Restricts the events generated by this request to those
     * in which the name of the class of the monitor object matches this restricted
     * regular expression. Regular expressions are limited
     * to exact matches and patterns that begin with '*' or end with '*';
     * for example, "*.Foo" or "java.*".
     *
     * @param classPattern the pattern String to filter for.
     * @throws InvalidRequestStateException if this request is currently
     * enabled or has been deleted.
     * Filters may be added only to disabled requests.
     */
    void addClassFilter(String classPattern);

    /**
     * Restricts the events generated by this request to those
     * in which the name of the class of the monitor object does <b>not</b>match this restricted
     * regular expression, e.g.  "java.*" or "*.Foo".
     * @param classPattern the pattern String to filter against.
     * @throws InvalidRequestStateException if this request is currently
     * enabled or has been deleted.
     * Filters may be added only to disabled requests.
     */
    void addClassExclusionFilter(String classPattern);

    /**
     * Restricts the events generated by this request to those in
     * which the currently executing instance ("this") is the object
     * specified.
     * <P>
     * Not all targets support this operation.
     * Use {@link VirtualMachine#canUseInstanceFilters()}
     * to determine if the operation is supported.
     * @param instance the object which must be the current instance
     * in order to pass this filter.
     * @throws java.lang.UnsupportedOperationException if
     * the target virtual machine does not support this
     * operation.
     * @throws InvalidRequestStateException if this request is currently
     * enabled or has been deleted.
     * Filters may be added only to disabled requests.
     */
    void addInstanceFilter(ObjectReference instance);
}
