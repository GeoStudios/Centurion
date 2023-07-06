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

import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.GOTO;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodType;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;

/**
 * @LastModified: Nov 2017
 */
final class LogicalExpr extends Expression {

    public static final int OR  = 0;
    public static final int AND = 1;

    private final int  _op;     // operator
    private Expression _left;   // first operand
    private Expression _right;  // second operand

    private static final String[] Ops = { "or", "and" };

    /**
     * Creates a new logical expression - either OR or AND. Note that the
     * left- and right-hand side expressions can also be logical expressions,
     * thus creating logical trees representing structures such as
     * (a and (b or c) and d), etc...
     */
    public LogicalExpr(int op, Expression left, Expression right) {
        _op = op;
        (_left = left).setParent(this);
        (_right = right).setParent(this);
    }

    /**
     * Returns true if this expressions contains a call to position(). This is
     * needed for context changes in node steps containing multiple predicates.
     */
    public boolean hasPositionCall() {
        return (_left.hasPositionCall() || _right.hasPositionCall());
    }

    /**
     * Returns true if this expressions contains a call to last()
     */
    public boolean hasLastCall() {
            return (_left.hasLastCall() || _right.hasLastCall());
    }

    /**
     * Returns an object representing the compile-time evaluation
     * of an expression. We are only using this for function-available
     * and element-available at this time.
     */
    public Object evaluateAtCompileTime() {
        final Object leftb = _left.evaluateAtCompileTime();
        final Object rightb = _right.evaluateAtCompileTime();

        // Return null if we can't evaluate at compile time
        if (leftb == null || rightb == null) {
            return null;
        }

        if (_op == AND) {
            return (leftb == Boolean.TRUE && rightb == Boolean.TRUE) ?
                Boolean.TRUE : Boolean.FALSE;
        }
        else {
            return (leftb == Boolean.TRUE || rightb == Boolean.TRUE) ?
                Boolean.TRUE : Boolean.FALSE;
        }
    }

    /**
     * Returns this logical expression's operator - OR or AND represented
     * by 0 and 1 respectively.
     */
    public int getOp() {
        return(_op);
    }

    /**
     * Override the SyntaxTreeNode.setParser() method to make sure that the
     * parser is set for sub-expressions
     */
    public void setParser(Parser parser) {
        super.setParser(parser);
        _left.setParser(parser);
        _right.setParser(parser);
    }

    /**
     * Returns a string describing this expression
     */
    public String toString() {
        return Ops[_op] + '(' + _left + ", " + _right + ')';
    }

    /**
     * Type-check this expression, and possibly child expressions.
     */
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        // Get the left and right operand types
        Type tleft = _left.typeCheck(stable);
        Type tright = _right.typeCheck(stable);

        // Check if the operator supports the two operand types
        MethodType wantType = new MethodType(Type.Void, tleft, tright);
        MethodType haveType = lookupPrimop(stable, Ops[_op], wantType);

        // Yes, the operation is supported
        if (haveType != null) {
            // Check if left-hand side operand must be type casted
            Type arg1 = haveType.argsType().get(0);
            if (!arg1.identicalTo(tleft))
                _left = new CastExpr(_left, arg1);
            // Check if right-hand side operand must be type casted
            Type arg2 = haveType.argsType().get(1);
            if (!arg2.identicalTo(tright))
                _right = new CastExpr(_right, arg1);
            // Return the result type for the operator we will use
            return _type = haveType.resultType();
        }
        throw new TypeCheckError(this);
    }

    /**
     * Compile the expression - leave boolean expression on stack
     */
    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        translateDesynthesized(classGen, methodGen);
        synthesize(classGen, methodGen);
    }

    /**
     * Compile expression and update true/false-lists
     */
    public void translateDesynthesized(ClassGenerator classGen,
                                       MethodGenerator methodGen) {

        final InstructionList il = methodGen.getInstructionList();
        final SyntaxTreeNode parent = getParent();

        // Compile AND-expression
        if (_op == AND) {

            // Translate left hand side - must be true
            _left.translateDesynthesized(classGen, methodGen);

            // Need this for chaining any OR-expression children
            InstructionHandle middle = il.append(NOP);

            // Translate left right side - must be true
            _right.translateDesynthesized(classGen, methodGen);

            // Need this for chaining any OR-expression children
            InstructionHandle after = il.append(NOP);

            // Append child expression false-lists to our false-list
            _falseList.append(_right._falseList.append(_left._falseList));

            // Special case for OR-expression as a left child of AND.
            // The true-list of OR must point to second clause of AND.
            if ((_left instanceof LogicalExpr) &&
                (((LogicalExpr)_left).getOp() == OR)) {
                _left.backPatchTrueList(middle);
            }
            else if (_left instanceof NotCall) {
                _left.backPatchTrueList(middle);
            }
            else {
                _trueList.append(_left._trueList);
            }

            // Special case for OR-expression as a right child of AND
            // The true-list of OR must point to true-list of AND.
            if ((_right instanceof LogicalExpr) &&
                (((LogicalExpr)_right).getOp() == OR)) {
                _right.backPatchTrueList(after);
            }
            else if (_right instanceof NotCall) {
                _right.backPatchTrueList(after);
            }
            else {
                _trueList.append(_right._trueList);
            }
        }
        // Compile OR-expression
        else {
            // Translate left-hand side expression and produce true/false list
            _left.translateDesynthesized(classGen, methodGen);

            // This GOTO is used to skip over the code for the last test
            // in the case where the the first test succeeds
            InstructionHandle ih = il.append(new GOTO(null));

            // Translate right-hand side expression and produce true/false list
            _right.translateDesynthesized(classGen, methodGen);

            _left._trueList.backPatch(ih);
            _left._falseList.backPatch(ih.getNext());

            _falseList.append(_right._falseList);
            _trueList.add(ih).append(_right._trueList);
        }
    }
}