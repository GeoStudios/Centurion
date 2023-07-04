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

/**
 * @test
 * @bug 8074761
 * @summary RFC-2255 allows attribute, scope and filter to be empty.
 * @modules java.naming/com.sun.jndi.ldap
 */

import com.sun.jndi.ldap.LdapURL;

public class LdapURLOptionalFields {

    private static final String[] TEST_URLS = {
        "ldap://localhost:10389/ou=RefPeople,dc=example,dc=com",
        "ldap://localhost:10389/ou=RefPeople,dc=example,dc=com?",
        "ldap://localhost:10389/ou=RefPeople,dc=example,dc=com??",
        "ldap://localhost:10389/ou=RefPeople,dc=example,dc=com???",
        "ldap://localhost:10389/ou=RefPeople,dc=example,dc=com????"
    };

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < TEST_URLS.length; i++) {
            String url = TEST_URLS[i];
            checkEmptyAttributes(url);
        }
    }

    private static void checkEmptyAttributes(String urlString) throws Exception {
        LdapURL url = new LdapURL(urlString);
        if (url.getAttributes() != null) {
            throw new Exception("Expected null attributes for url: '" + urlString + "'");
        }
        if (url.getScope() != null) {
            throw new Exception("Expected null scope for url: '" + urlString + "'");
        }
        if (url.getFilter() != null) {
            throw new Exception("Expected null filter for url: '" + urlString + "'");
        }
    }

}
