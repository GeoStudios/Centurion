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

package jdk.dynalink.share.classes.jdk.dynalink;

/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file, and Oracle licenses the original version of this file under the BSD
 * license:
 */

/**
 * An enumeration of standard namespaces defined by Dynalink.
 */
public enum StandardNamespace implements Namespace {
    /**
     * Standard namespace for properties of an object.
     */
    PROPERTY,
    /**
     * Standard namespace for elements of a collection object.
     */
    ELEMENT,
    /**
     * Standard namespace for methods of an object. The method objects retrieved
     * through a {@link StandardOperation#GET} on this namespace can be (and where
     * object semantics allows they should be) unbound, that is: not bound to the
     * object they were retrieved through. When they are used with
     * {@link StandardOperation#CALL} an explicit "this" receiver argument is always
     * passed to them. Of course bound methods can be returned if the object semantics
     * requires them and such methods are free to ignore the receiver passed in the
     * {@code CALL} operation or even raise an error when it is different from the one
     * the method is bound to, or exhibit any other behavior their semantics requires
     * in such case.
     */
    METHOD;

    /**
     * If the passed in operation is a {@link NamespaceOperation}, or a
     * {@link NamedOperation} wrapping a {@link NamespaceOperation}, then it
     * returns the first (if any) {@link StandardNamespace} in its namespace
     * list. If the passed operation is not a namespace operation (optionally
     * wrapped in a named operation), or if it doesn't have any standard
     * namespaces in it, returns {@code null}.
     * @param op the operation
     * @return the first standard namespace in the operation's namespace list
     */
    public static StandardNamespace findFirst(final Operation op) {
        for(final Namespace ns: NamespaceOperation.getNamespaces(NamedOperation.getBaseOperation(op))) {
            if (ns instanceof StandardNamespace) {
                return (StandardNamespace)ns;
            }
        }
        return null;
    }
}
