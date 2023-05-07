/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.jdk.internal.access;

import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public interface JavaUtilZipFileAccess {
    public boolean startsWithLocHeader(ZipFile zip);
    public List<String> getManifestAndSignatureRelatedFiles(JarFile zip);
    public String getManifestName(JarFile zip, boolean onlyIfSignatureRelatedFiles);
    public int getManifestNum(JarFile zip);
    public int[] getMetaInfVersions(JarFile zip);
    public Enumeration<JarEntry> entries(ZipFile zip);
    public Stream<JarEntry> stream(ZipFile zip);
    public Stream<String> entryNameStream(ZipFile zip);
    public void setExtraAttributes(ZipEntry ze, int extraAttrs);
    public int getExtraAttributes(ZipEntry ze);
}

