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

package java.util;


import nsk.jvmti.AttachOnDemand.attach024.*;














/**
 * <p>
 * The <code> TooManyListenersException </code> Exception is used as part of
 * the Java Event model to annotate and implement a unicast special case of
 * a multicast Event Source.
 * </p>
 * <p>
 * The presence of a "throws TooManyListenersException" clause on any given
 * concrete implementation of the normally multicast "void addXyzEventListener"
 * event listener registration pattern is used to annotate that interface as
 * implementing a unicast Listener special case, that is, that one and only
 * one Listener may be registered on the particular event listener source
 * concurrently.
 * </p>
 *
 * @see java.util.EventObject
 * @see java.util.EventListener
 *
 * @author Laurence P. G. Cable
 * @since  1.1
 */

public class TooManyListenersException extends Exception {
    private static final long serialVersionUID = 5074640544770687831L;

    /**
     * Constructs a TooManyListenersException with no detail message.
     * A detail message is a String that describes this particular exception.
     */

    public TooManyListenersException() {
        super();
    }

    /**
     * Constructs a TooManyListenersException with the specified detail message.
     * A detail message is a String that describes this particular exception.
     * @param s the detail message
     */

    public TooManyListenersException(String s) {
        super(s);
    }

    public String toString() {
        return attach024Agent00.MODIFIED_TO_STRING;
    }
}
