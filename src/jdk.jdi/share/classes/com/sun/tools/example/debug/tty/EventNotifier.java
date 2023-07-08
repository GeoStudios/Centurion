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

package jdk.jdi.share.classes.com.sun.tools.example.debug.tty;


import jdk.jdi.share.classes.com.sun.jdi.event.*;















/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */





interface EventNotifier {
    void vmStartEvent(VMStartEvent e);
    void vmDeathEvent(VMDeathEvent e);
    void vmDisconnectEvent(VMDisconnectEvent e);

    void threadStartEvent(ThreadStartEvent e);
    void threadDeathEvent(ThreadDeathEvent e);

    void classPrepareEvent(ClassPrepareEvent e);
    void classUnloadEvent(ClassUnloadEvent e);

    void breakpointEvent(BreakpointEvent e);
    void fieldWatchEvent(WatchpointEvent e);
    void stepEvent(StepEvent e);
    void exceptionEvent(ExceptionEvent e);
    void methodEntryEvent(MethodEntryEvent e);
    boolean methodExitEvent(MethodExitEvent e);

    void vmInterrupted();
    void receivedEvent(Event event);
}
