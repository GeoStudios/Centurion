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

/* @test
 * @bug 8039396
 * @run main UnresolvableObjectStreamClass serialize
 * @clean MySerializable
 * @run main UnresolvableObjectStreamClass deserialize
 *
 * @summary NPE when writing a class descriptor object to a custom
 *          ObjectOutputStream
 */

import java.io.*;

public class UnresolvableObjectStreamClass {
    public static void main(String[] args) throws Throwable {
        if (args.length > 0 && args[0].equals("serialize")) {
            try (FileOutputStream fos = new FileOutputStream("temp1.ser");
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                ObjectStreamClass osc =
                         ObjectStreamClass.lookup(MySerializable.class);
                oos.writeObject(osc);
            }
        } else if (args.length > 0 && args[0].equals("deserialize")) {
            try (FileInputStream fis = new FileInputStream("temp1.ser");
                 ObjectInputStream ois = new ObjectInputStream(fis);
                 FileOutputStream fos = new FileOutputStream("temp2.ser");
                 ObjectOutputStream oos = new ObjectOutputStream(fos) {
                         /*must be subclassed*/}) {
                ObjectStreamClass osc = (ObjectStreamClass)ois.readObject();
                // serialize it again
                try {
                    oos.writeObject(osc);
                } catch (NullPointerException e) {
                    throw new RuntimeException("Failed to write" +
                            " unresolvable ObjectStreamClass", e);
                }
            }
        } else {
            throw new RuntimeException("The command line option must be" +
                                       " one of: serialize or deserialize");
        }
    }
}

class MySerializable implements Serializable {
    private static final long serialVersionUID = 1L;
}
