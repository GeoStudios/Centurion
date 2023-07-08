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

package java.xml.share.classes.com.sun.org.apache.bcel.internal.generic;

import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.ConstantCP;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.ConstantNameAndType;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.ConstantUtf8;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Super class for FieldOrMethod and INVOKEDYNAMIC, since they both have
 * names and signatures
 *
 */
public abstract class NameSignatureInstruction extends CPInstruction {

    public NameSignatureInstruction() {
        super();
    }

    public NameSignatureInstruction(final short opcode, final int index) {
        super(opcode, index);
    }

    public ConstantNameAndType getNameAndType(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();
        final ConstantCP cmr = (ConstantCP) cp.getConstant(super.getIndex());
        return  (ConstantNameAndType) cp.getConstant(cmr.getNameAndTypeIndex());
    }
    /** @return signature of referenced method/field.
     */
    public String getSignature(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();
        final ConstantNameAndType cnat = getNameAndType(cpg);
        return ((ConstantUtf8) cp.getConstant(cnat.getSignatureIndex())).getBytes();
    }

    /** @return name of referenced method/field.
     */
    public String getName(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();
        final ConstantNameAndType cnat = getNameAndType(cpg);
        return ((ConstantUtf8) cp.getConstant(cnat.getNameIndex())).getBytes();
    }

}
