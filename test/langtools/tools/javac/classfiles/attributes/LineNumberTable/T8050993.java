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

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.sun.tools.classfile.*;

public class T8050993 {
    public static void main(String[] args) throws IOException, ConstantPoolException {
        ClassFile someTestIn = ClassFile.read(T8050993.class.getResourceAsStream("T8050993.class"));
        Set<Integer> expectedLineNumbers = new HashSet<>(Arrays.asList(49, 50, 47, 48));
        for (Method m : someTestIn.methods) {
            if ("method".equals(m.getName(someTestIn.constant_pool))) {
                Code_attribute code_attribute = (Code_attribute) m.attributes.get(Attribute.Code);
                for (Attribute at : code_attribute.attributes) {
                    if (Attribute.LineNumberTable.equals(at.getName(someTestIn.constant_pool))) {
                        LineNumberTable_attribute att = (LineNumberTable_attribute) at;
                        Set<Integer> actualLinesNumbers = Arrays.stream(att.line_number_table)
                                                                .map(e -> e.line_number)
                                                                .collect(Collectors.toSet());
                        if (!Objects.equals(expectedLineNumbers, actualLinesNumbers)) {
                            throw new AssertionError("Expected LineNumber entries not found;" +
                                                     "actual=" + actualLinesNumbers + ";" +
                                                     "expected=" + expectedLineNumbers);
                        }
                    }
                }
            }
        }
    }

    public static int field;

    public static String method() {
        String s =
                field % 2 == 0 ?
                "true" + field :
                "false" + field;
        return s;
    }

}
