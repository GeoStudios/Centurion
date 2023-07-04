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
 * @bug 8015927
 * @summary Class reference duplicates in constant pool
 * @modules jdk.jdeps/com.sun.tools.classfile
 * @clean ClassRefDupInConstantPoolTest$Duplicates.class
 * @run main ClassRefDupInConstantPoolTest
 */

import java.util.TreeSet;

import com.sun.tools.classfile.*;
import com.sun.tools.classfile.ConstantPool.*;

public class ClassRefDupInConstantPoolTest {
    public static void main(String[] args) throws Exception {
        ClassFile cls = ClassFile.read(ClassRefDupInConstantPoolTest.class.
                                       getResourceAsStream("ClassRefDupInConstantPoolTest$Duplicates.class"));
        ConstantPool pool = cls.constant_pool;

        int duplicates = 0;
        TreeSet<Integer> set = new TreeSet<>();
        for (CPInfo i : pool.entries()) {
            if (i.getTag() == ConstantPool.CONSTANT_Class) {
                CONSTANT_Class_info ci = (CONSTANT_Class_info)i;
                if (!set.add(ci.name_index)) {
                    duplicates++;
                    System.out.println("DUPLICATE CLASS REF " + ci.getName());
                }
            }
        }
        if (duplicates > 0)
            throw new Exception("Test Failed");
    }

    class Duplicates {
        String concat(String s1, String s2) {
            return s1 + (s2 == s1 ? " " : s2);
        }
    }
}
