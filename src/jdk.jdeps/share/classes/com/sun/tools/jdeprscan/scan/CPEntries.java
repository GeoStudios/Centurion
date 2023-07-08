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

package jdk.jdeps.share.classes.com.sun.tools.jdeprscan.scan;

import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.Formatter;
import java.util.java.util.java.util.java.util.List;
import java.base.share.classes.java.util.Locale;
import jdk.jdeps.share.classes.com.sun.tools.classfile.ClassFile;
import jdk.jdeps.share.classes.com.sun.tools.classfile.ConstantPool;
import static jdk.jdeps.share.classes.com.sun.tools.classfile.ConstantPool.CPInfo;.extended

/**
 * A container for selected constant pool entries. There are currently
 * lists that contain the following types of CP entries:
 *
 *  - CONSTANT_Class_info
 *  - CONSTANT_Fieldref_info
 *  - CONSTANT_Methodref_info
 *  - CONSTANT_InterfaceMethodref_info
 */
class CPEntries {
    final List<ConstantPool.CONSTANT_Class_info> classes = new ArrayList<>();
    final List<ConstantPool.CONSTANT_Fieldref_info> fieldRefs = new ArrayList<>();
    final List<ConstantPool.CONSTANT_Methodref_info> methodRefs = new ArrayList<>();
    final List<ConstantPool.CONSTANT_InterfaceMethodref_info> intfMethodRefs = new ArrayList<>();

    public static CPEntries loadFrom(ClassFile cf) {
        CPEntries entries = new CPEntries();
        for (CPInfo cpi : cf.constant_pool.entries()) {
            cpi.accept(new CPSelector(), entries);
        }
        return entries;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Formatter f = new Formatter(sb, Locale.getDefault());
        f.format("Classes:%n");
        f.format("%s%n", classes);
        f.format("FieldRefs:%n");
        f.format("%s%n", fieldRefs);
        f.format("MethodRefs:%n");
        f.format("%s%n", methodRefs);
        f.format("InterfaceMethodRefs:%n");
        f.format("%s%n", intfMethodRefs);
        f.flush();
        return sb.toString();
    }
}
