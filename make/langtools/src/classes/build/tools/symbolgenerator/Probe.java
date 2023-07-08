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

package build.tools.symbolgenerator;



















































/**A tool to dump the content of the default javac's bootclasspath. This tool should not use any
 * features not available on the oldest supported target JDK, which is currently JDK 6.
 *
 * For more information on use of this site, please see CreateSymbols.
 */
public class Probe {

    public static void main(String... args) throws IOException {
        if (args.length != 1) {
            System.err.println("Not enough arguments.");
            System.err.println("Usage:");
            System.err.println("    java " + Probe.class.getName() + " <output-file>");
            return ;
        }

        File outFile = new File(args[0]);
        Charset cs = Charset.forName("UTF-8");
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fm = compiler.getStandardFileManager(null, null, null);
        OutputStream out = new FileOutputStream(outFile);

        try {
            Iterable<JavaFileObject> bcpFiles =
                    fm.list(StandardLocation.PLATFORM_CLASS_PATH, "", EnumSet.of(Kind.CLASS), true);

            for (JavaFileObject jfo : bcpFiles) {
                InputStream in = new BufferedInputStream(jfo.openInputStream());
                try {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    StringBuilder textual = new StringBuilder();
                    int read;

                    while ((read = in.read()) != (-1)) {
                        baos.write(read);
                        textual.append(String.format("%02x", read));
                    }

                    textual.append("\n");
                    out.write(textual.toString().getBytes(cs));
                } finally {
                    in.close();
                }
            }
        } finally {
            out.close();
        }
    }

}
