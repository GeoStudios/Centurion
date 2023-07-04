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
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;

/**
 */
final class ParentPattern extends RelativePathPattern {
    private final Pattern _left;
    private final RelativePathPattern _right;

    public ParentPattern(Pattern left, RelativePathPattern right) {
        (_left = left).setParent(this);
        (_right = right).setParent(this);
    }

    public void setParser(Parser parser) {
        super.setParser(parser);
        _left.setParser(parser);
        _right.setParser(parser);
    }

    public boolean isWildcard() {
        return false;
    }

    public StepPattern getKernelPattern() {
        return _right.getKernelPattern();
    }

    public void reduceKernelPattern() {
        _right.reduceKernelPattern();
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        _left.typeCheck(stable);
        return _right.typeCheck(stable);
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final LocalVariableGen local =
            methodGen.addLocalVariable2("ppt",
                                        Util.getJCRefType(NODE_SIG),
                                        null);

        final com.sun.org.apache.bcel.internal.generic.Instruction loadLocal =
            new ILOAD(local.getIndex());
        final com.sun.org.apache.bcel.internal.generic.Instruction storeLocal =
            new ISTORE(local.getIndex());

        if (_right.isWildcard()) {
            il.append(methodGen.loadDOM());
            il.append(SWAP);
        }
        else if (_right instanceof StepPattern) {
            il.append(DUP);
            local.setStart(il.append(storeLocal));

            _right.translate(classGen, methodGen);

            il.append(methodGen.loadDOM());
            local.setEnd(il.append(loadLocal));
        }
        else {
            _right.translate(classGen, methodGen);

            if (_right instanceof AncestorPattern) {
                il.append(methodGen.loadDOM());
                il.append(SWAP);
            }
        }

        final int getParent = cpg.addInterfaceMethodref(DOM_INTF,
                                                        GET_PARENT,
                                                        GET_PARENT_SIG);
        il.append(new INVOKEINTERFACE(getParent, 2));

        final SyntaxTreeNode p = getParent();
        if (p == null || p instanceof Instruction ||
            p instanceof TopLevelElement)
        {
            _left.translate(classGen, methodGen);
        }
        else {
            il.append(DUP);
            InstructionHandle storeInst = il.append(storeLocal);

            if (local.getStart() == null) {
                local.setStart(storeInst);
            }

            _left.translate(classGen, methodGen);

            il.append(methodGen.loadDOM());
            local.setEnd(il.append(loadLocal));
        }

        methodGen.removeLocalVariable(local);

        /*
         * If _right is an ancestor pattern, backpatch _left false
         * list to the loop that searches for more ancestors.
         */
        if (_right instanceof AncestorPattern ancestor) {
            _left.backPatchFalseList(ancestor.getLoopHandle());    // clears list
        }

        _trueList.append(_right._trueList.append(_left._trueList));
        _falseList.append(_right._falseList.append(_left._falseList));
    }

    public String toString() {
        return "Parent(" + _left + ", " + _right + ')';
    }
}
