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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.GOTO;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.IFLT;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ILOAD;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ISTORE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 */
final class AncestorPattern extends RelativePathPattern {

    private final Pattern _left;        // may be null
    private final RelativePathPattern _right;
    private InstructionHandle _loop;

    public AncestorPattern(RelativePathPattern right) {
        this(null, right);
    }

    public AncestorPattern(Pattern left, RelativePathPattern right) {
        _left = left;
        (_right = right).setParent(this);
        if (left != null) {
            left.setParent(this);
        }
    }

    public InstructionHandle getLoopHandle() {
        return _loop;
    }

    public void setParser(Parser parser) {
        super.setParser(parser);
        if (_left != null) {
            _left.setParser(parser);
        }
        _right.setParser(parser);
    }

    public boolean isWildcard() {
        //!!! can be wildcard
        return false;
    }

    public StepPattern getKernelPattern() {
        return _right.getKernelPattern();
    }

    public void reduceKernelPattern() {
        _right.reduceKernelPattern();
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        if (_left != null) {
            _left.typeCheck(stable);
        }
        return _right.typeCheck(stable);
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        InstructionHandle parent;
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        /*
         * The scope of this local var must be the entire method since
         * a another pattern may decide to jump back into the loop
         */
        final LocalVariableGen local =
            methodGen.addLocalVariable2("app", Util.getJCRefType(NODE_SIG),
                                        il.getEnd());

        final com.sun.org.apache.bcel.internal.generic.Instruction loadLocal =
            new ILOAD(local.getIndex());
        final com.sun.org.apache.bcel.internal.generic.Instruction storeLocal =
            new ISTORE(local.getIndex());

        if (_right instanceof StepPattern) {
            il.append(DUP);
            il.append(storeLocal);
            _right.translate(classGen, methodGen);
            il.append(methodGen.loadDOM());
            il.append(loadLocal);
        }
        else {
            _right.translate(classGen, methodGen);

            if (_right instanceof AncestorPattern) {
                il.append(methodGen.loadDOM());
                il.append(SWAP);
            }
        }

        if (_left != null) {
            final int getParent = cpg.addInterfaceMethodref(DOM_INTF,
                                                            GET_PARENT,
                                                            GET_PARENT_SIG);
            parent = il.append(new INVOKEINTERFACE(getParent, 2));

            il.append(DUP);
            il.append(storeLocal);
            _falseList.add(il.append(new IFLT(null)));
            il.append(loadLocal);

            _left.translate(classGen, methodGen);

            final SyntaxTreeNode p = getParent();
            if (p == null || p instanceof Instruction ||
                p instanceof TopLevelElement)
            {
                // do nothing
            }
            else {
                il.append(loadLocal);
            }

            final BranchHandle exit = il.append(new GOTO(null));
            _loop = il.append(methodGen.loadDOM());
            il.append(loadLocal);
            local.setEnd(_loop);
            il.append(new GOTO(parent));
            exit.setTarget(il.append(NOP));
            _left.backPatchFalseList(_loop);

            _trueList.append(_left._trueList);
        }
        else {
            il.append(POP2);
        }

        /*
         * If _right is an ancestor pattern, backpatch this pattern's false
         * list to the loop that searches for more ancestors.
         */
        if (_right instanceof AncestorPattern ancestor) {
            _falseList.backPatch(ancestor.getLoopHandle());    // clears list
        }

        _trueList.append(_right._trueList);
        _falseList.append(_right._falseList);
    }

    public String toString() {
        return "AncestorPattern(" + _left + ", " + _right + ')';
    }
}
