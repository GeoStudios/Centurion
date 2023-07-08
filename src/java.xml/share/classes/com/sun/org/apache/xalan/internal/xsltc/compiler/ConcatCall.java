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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler;


import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instruction;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.NEW;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.PUSH;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.java.util.java.util.java.util.List;















/**
 * @LastModified: Oct 2017
 */
final class ConcatCall extends FunctionCall {
    public ConcatCall(QName fname, List<Expression> arguments) {
        super(fname, arguments);
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        for (int i = 0; i < argumentCount(); i++) {
            final Expression exp = argument(i);
            if (!exp.typeCheck(stable).identicalTo(Type.String)) {
                setArgument(i, new CastExpr(exp, Type.String));
            }
        }
        return _type = Type.String;
    }

    /** translate leaves a String on the stack */
    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int nArgs = argumentCount();

        switch (nArgs) {
        case 0:
            il.append(new PUSH(cpg, EMPTYSTRING));
            break;

        case 1:
            argument().translate(classGen, methodGen);
            break;

        default:
            final int initBuffer = cpg.addMethodref(STRING_BUFFER_CLASS,
                                                    "<init>", "()V");
            final Instruction append =
                new INVOKEVIRTUAL(cpg.addMethodref(STRING_BUFFER_CLASS,
                                                   "append",
                                                   "("+STRING_SIG+")"
                                                   +STRING_BUFFER_SIG));

            final int toString = cpg.addMethodref(STRING_BUFFER_CLASS,
                                                  "toString",
                                                  "()"+STRING_SIG);

            il.append(new NEW(cpg.addClass(STRING_BUFFER_CLASS)));
            il.append(DUP);
            il.append(new INVOKESPECIAL(initBuffer));
            for (int i = 0; i < nArgs; i++) {
                argument(i).translate(classGen, methodGen);
                il.append(append);
            }
            il.append(new INVOKEVIRTUAL(toString));
        }
    }
}
