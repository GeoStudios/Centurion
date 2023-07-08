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
 * From a server executing on JDK&nbsp;1.1, a
 * <code>ServerRuntimeException</code> is thrown as a result of a
 * remote method invocation when a <code>RuntimeException</code> is
 * thrown while processing the invocation on the server, either while
 * unmarshalling the arguments, executing the remote method itself, or
 * marshalling the return value.
 *
 * A <code>ServerRuntimeException</code> instance contains the original
 * <code>RuntimeException</code> that occurred as its cause.
 *
 * <p>A <code>ServerRuntimeException</code> is not thrown from servers
 * executing on the Java 2 platform v1.2 or later versions.
 *
 * @deprecated no replacement
 */
@Deprecated
public class ServerRuntimeException extends RemoteException {

    /* indicate compatibility with JDK 1.1.x version of class */
    private static final long serialVersionUID = 7054464920481467219L;

    /**
     * Constructs a <code>ServerRuntimeException</code> with the specified
     * detail message and nested exception.
     *
     * @param s the detail message
     * @param ex the nested exception
     * @deprecated no replacement
     */
    @Deprecated
    public ServerRuntimeException(String s, Exception ex) {
        super(s, ex);
    }
}
