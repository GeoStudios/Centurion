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

/*
 *
 *
 * Creates a "no-op" agent with a non US-Ascii class name, and a corresponding
 * jar manifest file with the Premain-Class attribute value set to the
 * name of the agent class.
 */
import java.io.File;
import java.io.FileOutputStream;

public class CreateFiles {

    static void output(FileOutputStream fos, String s) throws Exception {
        fos.write( s.getBytes("UTF8") );
        fos.write( "\n".getBytes("UTF8") );
    }

    public static void main(String [] args) throws Exception {
        File f;
        FileOutputStream fos;

        String name = "\u20ac";

        f = new File(name + ".java");
        fos = new FileOutputStream(f);
        output(fos, "import java.lang.instrument.Instrumentation;" );
        output(fos, "public class " +name + " {" );
        output(fos, "    public static void premain(String ops, Instrumentation ins) {" );
        output(fos, "        System.out.println(\"premain running\"); ");
        output(fos, "    }");
        output(fos, "}");
        fos.close();

        f = new File("agent.mf");
        fos = new FileOutputStream(f);
        output(fos, "Manifest-Version: 1.0");
        output(fos, "Premain-Class: " + name);
        output(fos, "");
        fos.close();
    }
}
