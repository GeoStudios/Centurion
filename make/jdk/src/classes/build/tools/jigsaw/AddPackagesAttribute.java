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

package build.tools.jigsaw;



















































/**
 * Adds the Packages class file attribute to each module-info.class in an
 * exploded build.
 */

public class AddPackagesAttribute {

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.err.println("Usage AddPackagesAttribute exploded-java-home");
            System.exit(-1);
        }

        String home = args[0];
        Path dir = Paths.get(home, "modules");

        ModuleFinder finder = ModuleFinder.of(dir);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry : stream) {
                Path mi = entry.resolve("module-info.class");
                if (Files.isRegularFile(mi)) {
                    String mn = entry.getFileName().toString();
                    Optional<ModuleReference> omref = finder.find(mn);
                    if (omref.isPresent()) {
                        Set<String> packages = omref.get().descriptor().packages();
                        addPackagesAttribute(mi, packages);
                    }
                }
            }
        }
    }

    static void addPackagesAttribute(Path mi, Set<String> packages) throws IOException {
        byte[] bytes;
        try (InputStream in = Files.newInputStream(mi)) {
            ModuleInfoExtender extender = ModuleInfoExtender.newExtender(in);
            extender.packages(packages);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            extender.write(baos);
            bytes = baos.toByteArray();
        }

        byte[] currentBytes = Files.readAllBytes(mi);
        if (!Arrays.equals(bytes, currentBytes)) {
            Files.write(mi, bytes);
        }
    }

}
