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

import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ALOAD;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ASTORE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ILOAD;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ISTORE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.NEW;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeSetType;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ReferenceType;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.util.java.util.java.util.java.util.List;

/**
 * @LastModified: Nov 2017
 */
class FilterExpr extends Expression {

    /**
     * Primary expression of this filter. I.e., 'e' in '(e)[p1]...[pn]'.
     */
    private Expression   _primary;

    /**
     * Array of predicates in '(e)[p1]...[pn]'.
     */
    private final List<Expression> _predicates;

    public FilterExpr(Expression primary, List<Expression> predicates) {
        _primary = primary;
        _predicates = predicates;
        primary.setParent(this);
    }

    protected Expression getExpr() {
        if (_primary instanceof CastExpr)
            return ((CastExpr)_primary).getExpr();
        else
            return _primary;
    }

    public void setParser(Parser parser) {
        super.setParser(parser);
        _primary.setParser(parser);
        if (_predicates != null) {
            final int n = _predicates.size();
            for (int i = 0; i < n; i++) {
                final Expression exp = _predicates.get(i);
                exp.setParser(parser);
                exp.setParent(this);
            }
        }
    }

    public String toString() {
        return "filter-expr(" + _primary + ", " + _predicates + ")";
    }

    /**
     * Type check a FilterParentPath. If the filter is not a node-set add a
     * cast to node-set only if it is of reference type. This type coercion
     * is needed for expressions like $x where $x is a parameter reference.
     * All optimizations are turned off before type checking underlying
     * predicates.
     */
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        Type ptype = _primary.typeCheck(stable);
        boolean canOptimize = _primary instanceof KeyCall;

        if (!(ptype instanceof NodeSetType)) {
            if (ptype instanceof ReferenceType)  {
                _primary = new CastExpr(_primary, Type.NodeSet);
            }
            else {
                throw new TypeCheckError(this);
            }
        }

        // Type check predicates and turn all optimizations off if appropriate
        int n = _predicates.size();
        for (int i = 0; i < n; i++) {
            Predicate pred = (Predicate) _predicates.get(i);

            if (!canOptimize) {
                pred.dontOptimize();
            }
            pred.typeCheck(stable);
        }
        return _type = Type.NodeSet;
    }

    /**
     * Translate a filter expression by pushing the appropriate iterator
     * onto the stack.
     */
    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        translateFilterExpr(classGen, methodGen, _predicates == null ? -1 : _predicates.size() - 1);
    }

    private void translateFilterExpr(ClassGenerator classGen,
                                     MethodGenerator methodGen,
                                     int predicateIndex) {
        if (predicateIndex >= 0) {
            translatePredicates(classGen, methodGen, predicateIndex);
        }
        else {
            _primary.translate(classGen, methodGen);
        }
    }

    /**
     * Translate a sequence of predicates. Each predicate is translated
     * by constructing an instance of <code>CurrentNodeListIterator</code>
     * which is initialized from another iterator (recursive call), a
     * filter and a closure (call to translate on the predicate) and "this".
     */
    public void translatePredicates(ClassGenerator classGen,
                                    MethodGenerator methodGen,
                                    int predicateIndex) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        // If not predicates left, translate primary expression
        if (predicateIndex < 0) {
            translateFilterExpr(classGen, methodGen, predicateIndex);
        }
        else {
            // Get the next predicate to be translated
            Predicate predicate = (Predicate) _predicates.get(predicateIndex--);

            // Translate the rest of the predicates from right to left
            translatePredicates(classGen, methodGen, predicateIndex);

            if (predicate.isNthPositionFilter()) {
                int nthIteratorIdx = cpg.addMethodref(NTH_ITERATOR_CLASS,
                                       "<init>",
                                       "("+NODE_ITERATOR_SIG+"I)V");

                // Backwards branches are prohibited if an uninitialized object
                // is on the stack by section 4.9.4 of the JVM Specification,
                // 2nd Ed.  We don't know whether this code might contain
                // backwards branches, so we mustn't create the new object unti

                // after we've created the suspect arguments to its constructor

                // Instead we calculate the values of the arguments to the
                // constructor first, store them in temporary variables, create
                // the object and reload the arguments from the temporaries to
                // avoid the problem.
                LocalVariableGen iteratorTemp
                        = methodGen.addLocalVariable("filter_expr_tmp1",
                                         Util.getJCRefType(NODE_ITERATOR_SIG),
                                         null, null);
                iteratorTemp.setStart(
                        il.append(new ASTORE(iteratorTemp.getIndex())));

                predicate.translate(classGen, methodGen);
                LocalVariableGen predicateValueTemp
                        = methodGen.addLocalVariable("filter_expr_tmp2",
                                         Util.getJCRefType("I"),
                                         null, null);
                predicateValueTemp.setStart(
                        il.append(new ISTORE(predicateValueTemp.getIndex())));

                il.append(new NEW(cpg.addClass(NTH_ITERATOR_CLASS)));
                il.append(DUP);
                iteratorTemp.setEnd(
                        il.append(new ALOAD(iteratorTemp.getIndex())));
                predicateValueTemp.setEnd(
                        il.append(new ILOAD(predicateValueTemp.getIndex())));
                il.append(new INVOKESPECIAL(nthIteratorIdx));
            } else {
                    // Translate predicates from right to left
                final int initCNLI = cpg.addMethodref(CURRENT_NODE_LIST_ITERATOR,
                                                      "<init>",
                                                      "("+NODE_ITERATOR_SIG+"Z"+
                                                      CURRENT_NODE_LIST_FILTER_SIG +
                                                      NODE_SIG+TRANSLET_SIG+")V");

                // Backwards branches are prohibited if an uninitialized object is
                // on the stack by section 4.9.4 of the JVM Specification, 2nd Ed.
                // We don't know whether this code might contain backwards branches,
                // so we mustn't create the new object until after we've created
                // the suspect arguments to its constructor.  Instead we calculate
                // the values of the arguments to the constructor first, store them
                // in temporary variables, create the object and reload the
                // arguments from the temporaries to avoid the problem.

                LocalVariableGen nodeIteratorTemp =
                    methodGen.addLocalVariable("filter_expr_tmp1",
                                               Util.getJCRefType(NODE_ITERATOR_SIG),
                                               null, null);
                nodeIteratorTemp.setStart(
                        il.append(new ASTORE(nodeIteratorTemp.getIndex())));

                predicate.translate(classGen, methodGen);
                LocalVariableGen filterTemp =
                    methodGen.addLocalVariable("filter_expr_tmp2",
                                  Util.getJCRefType(CURRENT_NODE_LIST_FILTER_SIG),
                                  null, null);
                filterTemp.setStart(il.append(new ASTORE(filterTemp.getIndex())));

                // Create a CurrentNodeListIterator
                il.append(new NEW(cpg.addClass(CURRENT_NODE_LIST_ITERATOR)));
                il.append(DUP);

                // Initialize CurrentNodeListIterator
                nodeIteratorTemp.setEnd(
                        il.append(new ALOAD(nodeIteratorTemp.getIndex())));
                il.append(ICONST_1);
                filterTemp.setEnd(il.append(new ALOAD(filterTemp.getIndex())));
                il.append(methodGen.loadCurrentNode());
                il.append(classGen.loadTranslet());
                il.append(new INVOKESPECIAL(initCNLI));
            }
        }
    }
}