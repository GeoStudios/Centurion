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

package java.rmi.share.classes.java.rmi;

/**
 * An <code>AlreadyBoundException</code> is thrown if an attempt
 * is made to bind an object to a name that already
 * has an associated binding in the registry.
 *
 * @see     java.rmi.Naming#bind(String, java.rmi.Remote)
 * @see     java.rmi.registry.Registry#bind(String, java.rmi.Remote)
 */
public class AlreadyBoundException extends java.lang.Exception {

    /* indicate compatibility with JDK 1.1.x version of class */
    private static final long serialVersionUID = 9218657361741657110L;

    /**
     * Constructs an <code>AlreadyBoundException</code> with no
     * specified detail message.
     */
    public AlreadyBoundException() {
        super();
    }

    /**
     * Constructs an <code>AlreadyBoundException</code> with the specified
     * detail message.
     *
     * @param s the detail message
     */
    public AlreadyBoundException(String s) {
        super(s);
    }
}