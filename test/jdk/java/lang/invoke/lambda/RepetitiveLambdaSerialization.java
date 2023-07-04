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
 * @test
 * @bug 8027681
 * @summary Lambda serialization fails once reflection proxy generation kicks in
 * @author  Robert Field
 * @run main/othervm RepetitiveLambdaSerialization
 * @run main/othervm -Dsun.reflect.noInflation=true RepetitiveLambdaSerialization
 */

import java.io.*;

public class RepetitiveLambdaSerialization {

    static final int REPS = 20;

    public static void main(String[] args) throws Exception {
        LSI ls = z -> "[" + z + "]";
        for (int i = 0; i < REPS; ++i) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(baos);
            out.writeObject(ls);
            out.flush();
            out.close();
        }
        System.out.println("Passed.");
    }
}

interface LSI extends Serializable {
    String convert(String x);
}
