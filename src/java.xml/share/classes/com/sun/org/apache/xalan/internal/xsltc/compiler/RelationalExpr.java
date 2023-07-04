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

package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.BooleanType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeSetType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.RealType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ReferenceType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Operators;

/**
 * @LastModified: Oct 2017
 */
final class RelationalExpr extends Expression {

    private int _op;
    private Expression _left, _right;

    public RelationalExpr(int op, Expression left, Expression right) {
        _op = op;
        (_left = left).setParent(this);
        (_right = right).setParent(this);
    }

    public void setParser(Parser parser) {
        super.setParser(parser);
        _left.setParser(parser);
        _right.setParser(parser);
    }

    /**
     * Returns true if this expressions contains a call to position(). This is
     * needed for context changes in node steps containing multiple predicates.
     */
    public boolean hasPositionCall() {
        if (_left.hasPositionCall()) return true;
        return _right.hasPositionCall();
    }

    /**
     * Returns true if this expressions contains a call to last()
     */
    public boolean hasLastCall() {
            return (_left.hasLastCall() || _right.hasLastCall());
    }

    public boolean hasReferenceArgs() {
        return _left.getType() instanceof ReferenceType ||
            _right.getType() instanceof ReferenceType;
    }

    public boolean hasNodeArgs() {
        return _left.getType() instanceof NodeType ||
            _right.getType() instanceof NodeType;
    }

    public boolean hasNodeSetArgs() {
        return _left.getType() instanceof NodeSetType ||
            _right.getType() instanceof NodeSetType;
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        Type tleft = _left.typeCheck(stable);
        Type tright = _right.typeCheck(stable);

        //bug fix # 2838, cast to reals if both are result tree fragments
        if (tleft instanceof ResultTreeType &&
            tright instanceof ResultTreeType )
        {
            _right = new CastExpr(_right, Type.Real);
            _left = new CastExpr(_left, Type.Real);
            return _type = Type.Boolean;
        }

        // If one is of reference type, then convert the other too
        if (hasReferenceArgs()) {
            Type type = null;
            Type typeL = null;
            Type typeR = null;
            if (tleft instanceof ReferenceType) {
                if (_left instanceof VariableRefBase ref) {
                    VariableBase var = ref.getVariable();
                    typeL = var.getType();
                }
            }
            if (tright instanceof ReferenceType) {
                if (_right instanceof VariableRefBase ref) {
                    VariableBase var = ref.getVariable();
                    typeR = var.getType();
                }
            }
            // bug fix # 2838
            if (typeL == null)
                type = typeR;
            else if (typeR == null)
                type = typeL;
            else {
                type = Type.Real;
            }
            if (type == null) type = Type.Real;

            _right = new CastExpr(_right, type);
            _left = new CastExpr(_left, type);
            return _type = Type.Boolean;
        }

        if (hasNodeSetArgs()) {
            // Ensure that the node-set is the left argument
            if (tright instanceof NodeSetType) {
                final Expression temp = _right; _right = _left; _left = temp;
        _op = (_op == Operators.GT) ? Operators.LT :
            (_op == Operators.LT) ? Operators.GT :
            (_op == Operators.GE) ? Operators.LE : Operators.GE;
                tright = _right.getType();
            }

            // Promote nodes to node sets
            if (tright instanceof NodeType) {
                _right = new CastExpr(_right, Type.NodeSet);
            }
            // Promote integer to doubles to have fewer compares
            if (tright instanceof IntType) {
                _right = new CastExpr(_right, Type.Real);
            }
            // Promote result-trees to strings
            if (tright instanceof ResultTreeType) {
                _right = new CastExpr(_right, Type.String);
            }
            return _type = Type.Boolean;
        }

        // In the node-boolean case, convert node to boolean first
        if (hasNodeArgs()) {
            if (tleft instanceof BooleanType) {
                _right = new CastExpr(_right, Type.Boolean);
                tright = Type.Boolean;
            }
            if (tright instanceof BooleanType) {
                _left = new CastExpr(_left, Type.Boolean);
                tleft = Type.Boolean;
            }
        }

        // Lookup the table of primops to find the best match
    MethodType ptype = lookupPrimop(stable, Operators.getOpNames(_op),
                new MethodType(Type.Void, tleft, tright));

        if (ptype != null) {
            Type arg1 = ptype.argsType().get(0);
            if (!arg1.identicalTo(tleft)) {
                _left = new CastExpr(_left, arg1);
            }
            Type arg2 = ptype.argsType().get(1);
            if (!arg2.identicalTo(tright)) {
                _right = new CastExpr(_right, arg1);
            }
            return _type = ptype.resultType();
        }
        throw new TypeCheckError(this);
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        if (hasNodeSetArgs() || hasReferenceArgs()) {
            final ConstantPoolGen cpg = classGen.getConstantPool();
            final InstructionList il = methodGen.getInstructionList();

            // Call compare() from the BasisLibrary
            _left.translate(classGen, methodGen);
            _left.startIterator(classGen, methodGen);
            _right.translate(classGen, methodGen);
            _right.startIterator(classGen, methodGen);

            il.append(new PUSH(cpg, _op));
            il.append(methodGen.loadDOM());

            int index = cpg.addMethodref(BASIS_LIBRARY_CLASS, "compare",
                                         "("
                                         + _left.getType().toSignature()
                                         + _right.getType().toSignature()
                                         + "I"
                                         + DOM_INTF_SIG
                                         + ")Z");
            il.append(new INVOKESTATIC(index));
        }
        else {
            translateDesynthesized(classGen, methodGen);
            synthesize(classGen, methodGen);
        }
    }

    public void translateDesynthesized(ClassGenerator classGen,
                                       MethodGenerator methodGen) {
        if (hasNodeSetArgs() || hasReferenceArgs()) {
            translate(classGen, methodGen);
            desynthesize(classGen, methodGen);
        }
        else {
            BranchInstruction bi = null;
            final InstructionList il = methodGen.getInstructionList();

            _left.translate(classGen, methodGen);
            _right.translate(classGen, methodGen);

            // TODO: optimize if one of the args is 0

            boolean tozero = false;
            Type tleft = _left.getType();

            if (tleft instanceof RealType) {
        il.append(tleft.CMP(_op == Operators.LT || _op == Operators.LE));
                tleft = Type.Int;
                tozero = true;
            }

            switch (_op) {
        case Operators.LT:
                bi = tleft.GE(tozero);
                break;

        case Operators.GT:
                bi = tleft.LE(tozero);
                break;

        case Operators.LE:
                bi = tleft.GT(tozero);
                break;

        case Operators.GE:
                bi = tleft.LT(tozero);
                break;

            default:
                ErrorMsg msg = new ErrorMsg(ErrorMsg.ILLEGAL_RELAT_OP_ERR,this);
                getParser().reportError(Constants.FATAL, msg);
            }

            _falseList.add(il.append(bi));              // must be backpatched
        }
    }

    public String toString() {
        return Operators.getOpNames(_op) + '(' + _left + ", " + _right + ')';
    }
}
