/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.util.jar;

import java.io.IOException;

import jdk.internal.access.JavaUtilJarAccess;

class JavaUtilJarAccessImpl implements JavaUtilJarAccess {
    public boolean jarFileHasClassPathAttribute(JarFile jar) throws IOException {
        return jar.hasClassPathAttribute();
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
