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
 * @bug 4088176
 * @summary Evolution: read evolved class with new field of a non-existing Externalizable class.
 */

import java.io.*;

class A implements Serializable {
    // Version 1.0 of class A.
    private static final long serialVersionUID = 1L;
    public int bar;
    public D zoo;
}

class D implements Serializable {
    private static final long serialVersionUID = 1L;

    public int x;
    D(int y) {
        x = y;
    }
}

public class ReadAddedField {
    public static void main(String args[])
        throws IOException, ClassNotFoundException
    {
        File f = new File("tmp.ser");
        ObjectInput in =
            new ObjectInputStream(new FileInputStream(f));
        A a = (A)in.readObject();
        A b = (A)in.readObject();
        if (a.bar != 4)
            throw new RuntimeException("a.bar does not equal 4, it equals " +
                                       a.bar);
        if (a.zoo.x != 22)
            throw new RuntimeException("a.zoo.x does not equal 22 equals " +
                                       a.zoo.x);
        if (b.bar != 4)
            throw new RuntimeException("b.bar does not equal 4, it equals " +
                                       b.bar);
        if (b.zoo.x != 22)
            throw new RuntimeException("b.zoo.x does not equal 22 equals " +
                                       b.zoo.x);
        in.close();
    }
}
