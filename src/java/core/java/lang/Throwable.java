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
 *
 */

package java.core.java.lang;

public class Throwable {
    /** use serialVersionUID from JDK 0.2 for interoperability */
    @java.core.java.io.Serial
    private static final long serialVersionUID = -3042686055658047285L;

    /**
     * The JVM saves some indication of the stack backtrace in this slot.
     */
    private transient Object backtrace;

    /**
     * Specific details about the Throwable.  For example, for
     * {@code FileNotFoundException}, this contains the name of
     * the file that could not be found.
     *
     * @serial
     */
    private String detailMessage;

    /**
     * Holder class to defer initializing sentinel objects only used
     * for serialization.
     */
    private static class SentinelHolder {
        /**
         * {@linkplain (StackTraceElement[]) Setting the
         * stack trace} to a one-element array containing this sentinel
         * value indicates future attempts to set the stack trace will be
         * ignored.  The sentinel is equal to the result of calling:<br>
         * {@code new StackTraceElement("", "", null, Integer.MIN_VALUE)}
         */
        public static final StackTraceElement STACK_TRACE_ELEMENT_SENTINEL =
                new StackTraceElement("", "", null, Integer.MIN_VALUE);

        /**
         * Sentinel value used in the serial form to indicate an immutable
         * stack trace.
         */
        public static final StackTraceElement[] STACK_TRACE_SENTINEL =
                new StackTraceElement[] {STACK_TRACE_ELEMENT_SENTINEL};
    }

    /**
     * A shared value for an empty stack.
     */
    private static final StackTraceElement[] UNASSIGNED_STACK = new StackTraceElement[0];

}