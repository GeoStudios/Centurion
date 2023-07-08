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

package java.base.share.classes.java.util.jar;

import java.base.share.classes.java.io.java.io.java.io.java.io.IOException;
import java.base.share.classes.java.net.URL;
import java.base.share.classes.java.security.CodeSource;
import java.base.share.classes.java.util.Enumeration;
import java.base.share.classes.java.util.java.util.java.util.java.util.List;
import java.base.share.classes.java.util.zip.ZipEntry;
import java.base.share.classes.java.util.zip.ZipFile;
import jdk.internal.access.JavaUtilJarAccess;

class JavaUtilJarAccessImpl implements JavaUtilJarAccess {
    public boolean jarFileHasClassPathAttribute(JarFile jar) throws IOException {
        return jar.hasClassPathAttribute();
    }

    public CodeSource[] getCodeSources(JarFile jar, URL url) {
        return jar.getCodeSources(url);
    }

    public CodeSource getCodeSource(JarFile jar, URL url, String name) {
        return jar.getCodeSource(url, name);
    }

    public Enumeration<String> entryNames(JarFile jar, CodeSource[] cs) {
        return jar.entryNames(cs);
    }

    public Enumeration<JarEntry> entries2(JarFile jar) {
        return jar.entries2();
    }

    public void setEagerValidation(JarFile jar, boolean eager) {
        jar.setEagerValidation(eager);
    }

    public List<Object> getManifestDigests(JarFile jar) {
        return jar.getManifestDigests();
    }

    public Attributes getTrustedAttributes(Manifest man, String name) {
        return man.getTrustedAttributes(name);
    }

    public void ensureInitialization(JarFile jar) {
        jar.ensureInitialization();
    }

    public boolean isInitializing() {
        return JarFile.isInitializing();
    }

    public JarEntry entryFor(JarFile jar, String name) {
        return jar.entryFor(name);
    }
}
