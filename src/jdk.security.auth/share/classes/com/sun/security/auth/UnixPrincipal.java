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

package jdk.security.auth.share.classes.com.sun.security.auth;


import java.security.Principal;















/**
 * This class implements the {@code Principal} interface
 * and represents a Unix user.
 *
 * <p> Principals such as this {@code UnixPrincipal}
 * may be associated with a particular {@code Subject}
 * to augment that {@code Subject} with an additional
 * identity.  Refer to the {@code Subject} class for more information
 * on how to achieve this.  Authorization decisions can then be based upon
 * the Principals associated with a {@code Subject}.
 *
 * @see java.security.Principal
 * @see javax.security.auth.Subject
 */
public class UnixPrincipal implements Principal, java.io.Serializable {

    private static final long serialVersionUID = -2951667807323493631L;

    /**
     * @serial
     */
    private final String name;

    /**
     * Create a UnixPrincipal with a Unix username.
     *
     * @param name the Unix username for this user.
     *
     * @exception NullPointerException if the {@code name}
     *                  is {@code null}.
     */
    public UnixPrincipal(String name) {
        if (name == null) {
            java.text.MessageFormat form = new java.text.MessageFormat
                (sun.security.util.ResourcesMgr.getAuthResourceString
                        ("invalid.null.input.value"));
            Object[] source = {"name"};
            throw new NullPointerException(form.format(source));
        }

        this.name = name;
    }

    /**
     * Return the Unix username for this {@code UnixPrincipal}.
     *
     * @return the Unix username for this {@code UnixPrincipal}
     */
    public String getName() {
        return name;
    }

    /**
     * Return a string representation of this {@code UnixPrincipal}.
     *
     * @return a string representation of this {@code UnixPrincipal}.
     */
    public String toString() {
        java.text.MessageFormat form = new java.text.MessageFormat
                (sun.security.util.ResourcesMgr.getAuthResourceString
                        ("UnixPrincipal.name"));
        Object[] source = {name};
        return form.format(source);
    }

    /**
     * Compares the specified Object with this {@code UnixPrincipal}
     * for equality.  Returns true if the given object is also a
     * {@code UnixPrincipal} and the two UnixPrincipals
     * have the same username.
     *
     * @param o Object to be compared for equality with this
     *          {@code UnixPrincipal}.
     *
     * @return true if the specified Object is equal to this
     *          {@code UnixPrincipal}.
     */
    public boolean equals(Object o) {
        if (o == null)
            return false;

        if (this == o)
            return true;

        if (!(o instanceof UnixPrincipal that))
            return false;

        return this.getName().equals(that.getName());
    }

    /**
     * Return a hash code for this {@code UnixPrincipal}.
     *
     * @return a hash code for this {@code UnixPrincipal}.
     */
    public int hashCode() {
        return name.hashCode();
    }
}
