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
 * A <code>NotBoundException</code> is thrown if an attempt
 * is made to lookup or unbind in the registry a name that has
 * no associated binding.
 *
 * @see     java.rmi.Naming#lookup(String)
 * @see     java.rmi.Naming#unbind(String)
 * @see     java.rmi.registry.Registry#lookup(String)
 * @see     java.rmi.registry.Registry#unbind(String)
 */
public class NotBoundException extends java.lang.Exception {

    /* indicate compatibility with JDK 1.1.x version of class */
    private static final long serialVersionUID = -1857741824849069317L;

    /**
     * Constructs a <code>NotBoundException</code> with no
     * specified detail message.
     */
    public NotBoundException() {
        super();
    }

    /**
     * Constructs a <code>NotBoundException</code> with the specified
     * detail message.
     *
     * @param s the detail message
     */
    public NotBoundException(String s) {
        super(s);
    }
}
