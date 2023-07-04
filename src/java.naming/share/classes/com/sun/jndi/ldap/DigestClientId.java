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

package com.sun.jndi.ldap;

import java.util.Arrays; // JDK 1.2
import java.util.Hashtable;

import java.io.OutputStream;
import javax.naming.ldap.Control;

/**
 * Extends SimpleClientId to add property values specific for Digest-MD5.
 * This includes:
 * realm, authzid, qop, strength, maxbuffer, mutual-auth, reuse,
 * all policy-related selection properties.
 * Two DigestClientIds are identical iff they pass the SimpleClientId
 * equals() test and that all of these property values are the same.
 *
 */
class DigestClientId extends SimpleClientId {
    private static final String[] SASL_PROPS = {
        "java.naming.security.sasl.authorizationId",
        "java.naming.security.sasl.realm",
        "javax.security.sasl.qop",
        "javax.security.sasl.strength",
        "javax.security.sasl.reuse",
        "javax.security.sasl.server.authentication",
        "javax.security.sasl.maxbuffer",
        "javax.security.sasl.policy.noplaintext",
        "javax.security.sasl.policy.noactive",
        "javax.security.sasl.policy.nodictionary",
        "javax.security.sasl.policy.noanonymous",
        "javax.security.sasl.policy.forward",
        "javax.security.sasl.policy.credentials",
    };

    private final String[] propvals;
    private final int myHash;

    DigestClientId(int version, String hostname, int port,
        String protocol, Control[] bindCtls, OutputStream trace,
        String socketFactory, String username,
        Object passwd, Hashtable<?,?> env) {

        super(version, hostname, port, protocol, bindCtls, trace,
            socketFactory, username, passwd);

        if (env == null) {
            propvals = null;
        } else {
            // Could be smarter and apply default values for props
            // but for now, we just record and check exact matches
            propvals = new String[SASL_PROPS.length];
            for (int i = 0; i < SASL_PROPS.length; i++) {
                propvals[i] = (String) env.get(SASL_PROPS[i]);
            }
        }
        myHash = super.hashCode() ^ Arrays.hashCode(propvals);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DigestClientId other)) {
            return false;
        }
        return myHash == other.myHash
            && super.equals(obj)
            && Arrays.equals(propvals, other.propvals);
    }

    public int hashCode() {
        return myHash;
    }

    public String toString() {
        if (propvals != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < propvals.length; i++) {
                sb.append(':');
                if (propvals[i] != null) {
                    sb.append(propvals[i]);
                }
            }
            return super.toString() + sb;
        } else {
            return super.toString();
        }
    }
}
