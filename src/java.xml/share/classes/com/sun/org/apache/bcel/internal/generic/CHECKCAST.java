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

import java.xml.share.classes.com.sun.org.apache.bcel.internal.ExceptionConst;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * CHECKCAST - Check whether object is of given type
 * <PRE>Stack: ..., objectref -&gt; ..., objectref</PRE>
 *
 */
public class CHECKCAST extends CPInstruction implements LoadClass, ExceptionThrower, StackProducer,
        StackConsumer {

    /**
     * Empty constructor needed for Instruction.readInstruction.
     * Not to be used otherwise.
     */
    CHECKCAST() {
    }

    /** Check whether object is of given type
     * @param index index to class in constant pool
     */
    public CHECKCAST(final int index) {
        super(com.sun.org.apache.bcel.internal.Const.CHECKCAST, index);
    }

    /** @return exceptions this instruction may cause
     */
    @Override
    public Class<?>[] getExceptions() {
        return ExceptionConst.createExceptions(ExceptionConst.EXCS.EXCS_CLASS_AND_INTERFACE_RESOLUTION,
            ExceptionConst.CLASS_CAST_EXCEPTION);
    }

    @Override
    public ObjectType getLoadClassType( final ConstantPoolGen cpg ) {
        Type t = getType(cpg);
        if (t instanceof ArrayType) {
            t = ((ArrayType) t).getBasicType();
        }
        return (t instanceof ObjectType) ? (ObjectType) t : null;
    }

    /**
     * Call corresponding visitor method(s). The order is:
     * Call visitor methods of implemented interfaces first, then
     * call methods according to the class hierarchy in descending order,
     * i.e., the most specific visitXXX() call comes last.
     *
     * @param v Visitor object
     */
    @Override
    public void accept( final Visitor v ) {
        v.visitLoadClass(this);
        v.visitExceptionThrower(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitTypedInstruction(this);
        v.visitCPInstruction(this);
        v.visitCHECKCAST(this);
    }
}