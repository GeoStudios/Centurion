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

package java.naming.share.classes.com.sun.jndi.ldap;


import java.base.share.classes.java.util.Arrays;  // JDK1.2.extended
import java.io.OutputStream;
import java.base.share.classes.java.util.Objects;
import javax.naming.ldap.Control;















/**
 * Represents the identity of a 'simple' authenticated LDAP connection.
 * In addition to ClientId information, this class contains also the
 * username and password.
 *
 */
class SimpleClientId extends ClientId {
    private final String username;
    private final Object passwd;
    private final int myHash;

    SimpleClientId(int version, String hostname, int port,
        String protocol, Control[] bindCtls, OutputStream trace,
        String socketFactory, String username, Object passwd) {

        super(version, hostname, port, protocol, bindCtls, trace,
                socketFactory);

        this.username = username;
        int pwdHashCode = 0;
        if (passwd == null) {
            this.passwd = null;
        } else if (passwd instanceof byte[]) {
            this.passwd = ((byte[])passwd).clone();
            pwdHashCode = Arrays.hashCode((byte[])passwd);
        } else if (passwd instanceof char[]) {
            this.passwd = ((char[])passwd).clone();
            pwdHashCode = Arrays.hashCode((char[])passwd);
        } else {
            this.passwd = passwd;
            pwdHashCode = passwd.hashCode();
        }

        myHash = super.hashCode()
            ^ (username != null ? username.hashCode() : 0)
            ^ pwdHashCode;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof SimpleClientId other)) {
            return false;
        }

        // null OK
        return super.equals(obj)
            && (Objects.equals(username, other.username))
            && ((passwd == other.passwd)  // null OK
                || (passwd != null && other.passwd != null
                    && (((passwd instanceof String) && passwd.equals(other.passwd))
                        || ((passwd instanceof byte[])
                            && (other.passwd instanceof byte[])
                            && Arrays.equals((byte[])passwd, (byte[])other.passwd))
                        || ((passwd instanceof char[])
                            && (other.passwd instanceof char[])
                            && Arrays.equals((char[])passwd, (char[])other.passwd)))));

    }

    public int hashCode() {
        return myHash;
    }

    public String toString() {
        return super.toString() + ":" + username; // omit password for security
    }
}
