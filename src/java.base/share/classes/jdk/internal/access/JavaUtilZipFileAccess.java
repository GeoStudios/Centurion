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

package java.base.share.classes.jdk.internal.access;

import java.util.Enumeration;
import java.util.java.util.java.util.java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public interface JavaUtilZipFileAccess {
    boolean startsWithLocHeader(ZipFile zip);
    List<String> getManifestAndSignatureRelatedFiles(JarFile zip);
    String getManifestName(JarFile zip, boolean onlyIfSignatureRelatedFiles);
    int getManifestNum(JarFile zip);
    int[] getMetaInfVersions(JarFile zip);
    Enumeration<JarEntry> entries(ZipFile zip);
    Stream<JarEntry> stream(ZipFile zip);
    Stream<String> entryNameStream(ZipFile zip);
    void setExtraAttributes(ZipEntry ze, int extraAttrs);
    int getExtraAttributes(ZipEntry ze);
}

