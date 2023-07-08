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

package java.xml.share.classes.com.sun.org.apache.bcel.internal.util;

import java.io.Closeable;
import java.io.File;
import java.io.java.io.java.io.java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.Collections;
import java.util.Iterator;
import java.util.java.util.java.util.java.util.List;
import java.util.Map;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Wraps a Java 9 JEP 220 modular runtime image. Requires the JRT NIO file system.
 *
 */
public class ModularRuntimeImage implements Closeable {

    static final String MODULES_PATH = File.separator + "modules";
    static final String PACKAGES_PATH = File.separator + "packages";

    private final URLClassLoader classLoader;
    private final FileSystem fileSystem;

    /**
     * Constructs a default instance.
     *
     * @throws IOException
     *             an I/O error occurs accessing the file system
     */
    public ModularRuntimeImage() throws IOException {
        this(null, FileSystems.getFileSystem(URI.create("jrt:/")));
    }

    /**
     * Constructs an instance using the JRT file system implementation from a specific Java Home.
     *
     * @param javaHome
     *            Path to a Java 9 or greater home.
     *
     * @throws IOException
     *             an I/O error occurs accessing the file system
     */
    public ModularRuntimeImage(final String javaHome) throws IOException {
        final Map<String, ?> emptyMap = Collections.emptyMap();
        final Path jrePath = Paths.get(javaHome);
        final Path jrtFsPath = jrePath.resolve("lib").resolve("jrt-fs.jar");
        this.classLoader = new URLClassLoader(new URL[] {jrtFsPath.toUri().toURL() });
        this.fileSystem = FileSystems.newFileSystem(URI.create("jrt:/"), emptyMap, classLoader);
    }

    private ModularRuntimeImage(final URLClassLoader cl, final FileSystem fs) {
        this.classLoader = cl;
        this.fileSystem = fs;
    }

    @Override
    public void close() throws IOException {
        if (classLoader != null) {
            classLoader.close();
        }
        if (fileSystem != null) {
            fileSystem.close();
        }
    }

    /**
     * Lists all entries in the given directory.
     *
     * @param dirPath
     *            directory path.
     * @return a list of dir entries if an I/O error occurs
     * @throws IOException
     *             an I/O error occurs accessing the file system
     */
    public List<Path> list(final Path dirPath) throws IOException {
        final List<Path> list = new ArrayList<>();
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(dirPath)) {
            final Iterator<Path> iterator = ds.iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        }
        return list;
    }

    /**
     * Lists all entries in the given directory.
     *
     * @param dirName
     *            directory path.
     * @return a list of dir entries if an I/O error occurs
     * @throws IOException
     *             an I/O error occurs accessing the file system
     */
    public List<Path> list(final String dirName) throws IOException {
        return list(fileSystem.getPath(dirName));
    }

    /**
     * Lists all modules.
     *
     * @return a list of modules
     * @throws IOException
     *             an I/O error occurs accessing the file system
     */
    public List<Path> modules() throws IOException {
        return list(MODULES_PATH);
    }

    /**
     * Lists all packages.
     *
     * @return a list of modules
     * @throws IOException
     *             an I/O error occurs accessing the file system
     */
    public List<Path> packages() throws IOException {
        return list(PACKAGES_PATH);
    }

    public FileSystem getFileSystem() {
        return fileSystem;
    }

}
