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

import java.security.ProtectionDomain;
import java.net.URLClassLoader;
import java.net.URL;
import java.io.File;

// Intended to be called from test ProtectionDomain.java
//
// ProtDomainClassForArchive is     stored in CDS archive.
// ProtDomainNotForArchive   is NOT stored in CDS archive.
//
// However, they should have the same ProtectionDomain instance.
public class ProtDomain {
    public static void main(String args[]) throws Exception {
        String customLdrPath = args[0];

        URL[] urls = new URL[] {new File(customLdrPath).toURI().toURL()};
        URLClassLoader ldr = new URLClassLoader(urls);
        ProtectionDomain domain1 = ldr.loadClass("ProtDomainClassForArchive").getProtectionDomain();
        ProtectionDomain domain2 = ldr.loadClass("ProtDomainNotForArchive").getProtectionDomain();

        System.out.println("domain1 = " + domain1);
        System.out.println("domain2  = " + domain2);

        if (domain1 != domain2)
            throw new RuntimeException("Protection Domains do not match!");
    }
}

class ProtDomainClassForArchive {}

class ProtDomainNotForArchive {}
