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


import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.BranchHandle;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.GOTO_W;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.IF_ICMPEQ;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ILOAD;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ISTORE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.PUSH;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTM;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 */
final class AbsolutePathPattern extends LocationPathPattern {
    private final RelativePathPattern _left; // may be null

    public AbsolutePathPattern(RelativePathPattern left) {
        _left = left;
        if (left != null) {
            left.setParent(this);
        }
    }

    public void setParser(Parser parser) {
        super.setParser(parser);
        if (_left != null)
            _left.setParser(parser);
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        return _left == null ? Type.Root : _left.typeCheck(stable);
    }

    public boolean isWildcard() {
        return false;
    }

    public StepPattern getKernelPattern() {
        return _left != null ? _left.getKernelPattern() : null;
    }

    public void reduceKernelPattern() {
        _left.reduceKernelPattern();
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        if (_left != null) {
            if (_left instanceof StepPattern) {
                final LocalVariableGen local =
                    // absolute path pattern temporary
                    methodGen.addLocalVariable2("apptmp",
                                                Util.getJCRefType(NODE_SIG),
                                                null);
                il.append(DUP);
                local.setStart(il.append(new ISTORE(local.getIndex())));
                _left.translate(classGen, methodGen);
                il.append(methodGen.loadDOM());
                local.setEnd(il.append(new ILOAD(local.getIndex())));
                methodGen.removeLocalVariable(local);
            }
            else {
                _left.translate(classGen, methodGen);
            }
        }

        final int getParent = cpg.addInterfaceMethodref(DOM_INTF,
                                                        GET_PARENT,
                                                        GET_PARENT_SIG);
        final int getType = cpg.addInterfaceMethodref(DOM_INTF,
                                                      "getExpandedTypeID",
                                                      "(I)I");

        InstructionHandle begin = il.append(methodGen.loadDOM());
        il.append(SWAP);
        il.append(new INVOKEINTERFACE(getParent, 2));
        if (_left instanceof AncestorPattern) {
            il.append(methodGen.loadDOM());
            il.append(SWAP);
        }
        il.append(new INVOKEINTERFACE(getType, 2));
        il.append(new PUSH(cpg, DTM.DOCUMENT_NODE));

        final BranchHandle skip = il.append(new IF_ICMPEQ(null));
        _falseList.add(il.append(new GOTO_W(null)));
        skip.setTarget(il.append(NOP));

        if (_left != null) {
            _left.backPatchTrueList(begin);

            /*
             * If _left is an ancestor pattern, backpatch this pattern's false
             * list to the loop that searches for more ancestors.
             */
            if (_left instanceof AncestorPattern ancestor) {
                _falseList.backPatch(ancestor.getLoopHandle());         // clears list
            }
            _falseList.append(_left._falseList);
        }
    }

    public String toString() {
        return "absolutePathPattern(" + (_left != null ? _left.toString() : ")");
    }
}
