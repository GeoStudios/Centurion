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

package java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile;


import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.java.io.java.io.java.io.IOException;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.Const;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */




/**
 * This attribute exists for local or
 * anonymous classes and ... there can be only one.
 *
 */
public class EnclosingMethod extends Attribute {

    // Pointer to the CONSTANT_Class_info structure representing the
    // innermost class that encloses the declaration of the current class.
    private int classIndex;

    // If the current class is not immediately enclosed by a method or
    // constructor, then the value of the method_index item must be zero.
    // Otherwise, the value of the  method_index item must point to a
    // CONSTANT_NameAndType_info structure representing the name and the
    // type of a method in the class referenced by the class we point
    // to in the class_index.  *It is the compiler responsibility* to
    // ensure that the method identified by this index is the closest
    // lexically enclosing method that includes the local/anonymous class.
    private int methodIndex;

    // Ctors - and code to read an attribute in.
    EnclosingMethod(final int nameIndex, final int len, final DataInput input, final ConstantPool cpool) throws IOException {
        this(nameIndex, len, input.readUnsignedShort(), input.readUnsignedShort(), cpool);
    }

    private EnclosingMethod(final int nameIndex, final int len, final int classIdx,final int methodIdx, final ConstantPool cpool) {
        super(Const.ATTR_ENCLOSING_METHOD, nameIndex, len, cpool);
        classIndex  = classIdx;
        methodIndex = methodIdx;
    }

    @Override
    public void accept(final Visitor v) {
      v.visitEnclosingMethod(this);
    }

    @Override
    public Attribute copy(final ConstantPool constant_pool) {
        return (Attribute) clone();
    }

    // Accessors
    public final int getEnclosingClassIndex() {
        return classIndex;
    }

    public final int getEnclosingMethodIndex() {
        return methodIndex;
    }

    public final void setEnclosingClassIndex(final int idx) {
        classIndex = idx;
    }

    public final void setEnclosingMethodIndex(final int idx) {
        methodIndex = idx;
    }

    public final ConstantClass getEnclosingClass() {
        final ConstantClass c =
            (ConstantClass)super.getConstantPool().getConstant(classIndex,Const.CONSTANT_Class);
        return c;
    }

    public final ConstantNameAndType getEnclosingMethod() {
        if (methodIndex == 0) {
            return null;
        }
        final ConstantNameAndType nat =
            (ConstantNameAndType)super.getConstantPool().getConstant(methodIndex,Const.CONSTANT_NameAndType);
        return nat;
    }

    @Override
    public final void dump(final DataOutputStream file) throws IOException {
        super.dump(file);
        file.writeShort(classIndex);
        file.writeShort(methodIndex);
    }
}
