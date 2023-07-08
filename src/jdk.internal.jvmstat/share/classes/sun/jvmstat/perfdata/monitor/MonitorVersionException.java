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

package jdk.internal.jvmstat.share.classes.sun.jvmstat.perfdata.monitor;


import jdk.internal.jvmstat.share.classes.sun.jvmstat.monitor.MonitorException;















/**
 * Exception thrown when version of the implementation does not
 * match the version of the instrumentation exported by a target
 * Java Virtual Machine.
 *
 */
@SuppressWarnings("serial") // JDK implementation class
public class MonitorVersionException extends MonitorException {

    /**
     * Create a MonitorVersionException
     */
    public MonitorVersionException() {
        super();
    }

    /**
     * Create a MonitorVersionException with the given message.
     *
     * @param message the message to associate with the exception.
     */
    public MonitorVersionException(String message) {
        super(message);
    }
}
