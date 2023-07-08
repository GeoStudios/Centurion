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

package build.tools.module;



















































public class GenModuleLoaderMap {
    private static final String USAGE =
        "GenModuleLoaderMap -o <output-file> -boot m1[,m2]* -platform m3[,m4]* <original-source>";

    public static void main(String... args) throws Exception {
        // default set of boot modules and ext modules
        Stream<String> bootModules = Stream.empty();
        Stream<String> platformModules = Stream.empty();
        Path outfile = null;
        Path source = null;
        for (int i=0; i < args.length; i++) {
            String option = args[i];
            if (option.startsWith("-")) {
                String arg = args[++i];
                if (option.equals("-boot")) {
                    String[] mns = arg.split(",");
                    bootModules = Stream.concat(bootModules, Arrays.stream(mns));
                } else if (option.equals("-platform")) {
                    String[] mns = arg.split(",");
                    platformModules = Stream.concat(platformModules, Arrays.stream(mns));
                } else if (option.equals("-o")) {
                    outfile = Paths.get(arg);
                } else {
                    throw new IllegalArgumentException("invalid option: " + option);
                }
            } else {
                source = Paths.get(option);
            }
        }

        if (outfile == null) {
            throw new IllegalArgumentException("-o must be specified");
        }
        if (Files.notExists(source)) {
            throw new IllegalArgumentException(source + " not exist");
        }

        try (BufferedWriter bw = Files.newBufferedWriter(outfile, StandardCharsets.UTF_8);
             PrintWriter writer = new PrintWriter(bw)) {
            for (String line : Files.readAllLines(source)) {
                if (line.contains("@@BOOT_MODULE_NAMES@@")) {
                    line = patch(line, "@@BOOT_MODULE_NAMES@@", bootModules);
                } else if (line.contains("@@PLATFORM_MODULE_NAMES@@")) {
                    line = patch(line, "@@PLATFORM_MODULE_NAMES@@", platformModules);
                }
                writer.println(line);
            }
        }
    }

    private static String patch(String s, String tag, Stream<String> stream) {
        String mns = stream.sorted()
            .collect(Collectors.joining("\",\n            \""));
        return s.replace(tag, mns);
    }

    /**
     * Reads the contents of the given modules file.
     */
    private static Set<String> readModuleSet(String name) throws IOException {
        try (InputStream is = GenModuleLoaderMap.class.getResourceAsStream(name);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            return reader.lines().collect(Collectors.toSet());
        }
    }
}
