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

/* @test
 * @bug 4244051
 * @summary Make sure all PKIX-required X.520 name attribs are supported
 * @modules java.base/sun.security.x509
 */

import sun.security.x509.*;

public class AllAttribs {

    public static void main(String[] args) throws Exception {

        X500Name name = null;

        name = new X500Name("dnq=example_ens, "
                            + "t=mr, "
                            + "cn=john doe, "
                            + "givenname=john, "
                            + "surname=doe, "
                            + "initials=jd, "
                            + "generation=Sr, "
                            + "s=xxx gogo drive, "
                            + "l=peak park, "
                            + "st=california, "
                            + "c=usa, "
                            + "ou=example software, "
                            + "o=example com, "
                            + "ip=1.2.3.4, "
                            + "dc=eng, "
                            + "dc=example, "
                            + "dc=com");
        System.out.println("Name was constructed with keyword dnq");
        System.out.println("toString of name is:\n"+name);

        name = new X500Name("dnqualifier=example_ens, "
                            + "t=mr, "
                            + "cn=john doe, "
                            + "givenname=john, "
                            + "surname=doe, "
                            + "initials=jd, "
                            + "generation=Sr, "
                            + "s=xxx gogo drive, "
                            + "l=peak park, "
                            + "st=california, "
                            + "c=usa, "
                            + "ou=example software, "
                            + "o=example com, "
                            + "ip=1.2.3.4, "
                            + "dc=eng, "
                            + "dc=example, "
                            + "dc=com");
        System.out.println("Name was constructed with keyword dnqualifier");
        System.out.println("toString of name is:\n"+name);
    }
}
