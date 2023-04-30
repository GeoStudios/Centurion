/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.jdk.internal.access;

import java.io.IOException;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public interface JavaUtilJarAccess {
    public boolean jarFileHasClassPathAttribute(JarFile jar) throws IOException;
    public Attributes getTrustedAttributes(Manifest man, String name);
    public void ensureInitialization(JarFile jar);
    public boolean isInitializing();
    public JarEntry entryFor(JarFile file, String name);
}
