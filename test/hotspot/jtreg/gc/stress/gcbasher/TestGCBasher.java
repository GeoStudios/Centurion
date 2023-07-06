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

package gc.stress.gcbasher;


import java.io.java.io.java.io.java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.stream.Stream;














public class TestGCBasher {
    private static void parseClassFiles() throws IOException {
        HashMap<String, ClassInfo> deps = new HashMap<>();

        FileSystem fs = FileSystems.getFileSystem(URI.create("jrt:/"));
        try (Stream<Path> s = Files.walk(fs.getPath("/"))) {
            for (Path p : (Iterable<Path>)s::iterator) {
                if (p.toString().endsWith(".class") &&
                    !p.getFileName().toString().equals("module-info.class")) {
                    byte[] data = Files.readAllBytes(p);
                    Decompiler d = new Decompiler(data);
                    ClassInfo ci = d.getClassInfo();
                    deps.put(ci.getName(), ci);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: TestGCBasher <duration-ms>");
            return;
        }

        long durationMillis = Long.valueOf(args[0]);
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < durationMillis) {
            parseClassFiles();
        }
    }
}
