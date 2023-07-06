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

package org.example.authz;


import java.io.*;
import javax.naming.ldap.*;














/**
 * This class implements the LDAPv3 Authorization Identity response control
 * as defined in
 * <a href="http://tools.ietf.org/html/rfc3829">RFC 3829</a>.
 */



public class AuthzIdResponseControl extends BasicControl {

    public static final String OID = "2.16.840.1.113730.3.4.15";

    private String identity = null;

    public AuthzIdResponseControl(String id, boolean criticality, byte[] value)
        throws IOException {

        super(id, criticality, value);

        // decode value
        if (value != null && value.length > 0) {
            identity = new String(value, "UTF8");
        }
    }

    public String getIdentity() {
        return identity;
    }
}
