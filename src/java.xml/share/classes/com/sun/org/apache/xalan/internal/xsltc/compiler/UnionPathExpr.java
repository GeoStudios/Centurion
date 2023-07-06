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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.NEW;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.Axis;
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.DTM;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;

/**
 * @LastModified: Nov 2017
 */
final class UnionPathExpr extends Expression {

    private final Expression _pathExpr;
    private final Expression _rest;
    private boolean _reverse = false;

    // linearization for top level UnionPathExprs
    private Expression[] _components;

    public UnionPathExpr(Expression pathExpr, Expression rest) {
        _pathExpr = pathExpr;
        _rest     = rest;
    }

    public void setParser(Parser parser) {
        super.setParser(parser);
        // find all expressions in this Union
        final List<Expression> components = new ArrayList<>();
        flatten(components);
        final int size = components.size();
        _components = components.toArray(new Expression[size]);
        for (int i = 0; i < size; i++) {
            _components[i].setParser(parser);
            _components[i].setParent(this);
            if (_components[i] instanceof Step step) {
                final int axis = step.getAxis();
                final int type = step.getNodeType();
                // Put attribute iterators first
                if ((axis == Axis.ATTRIBUTE) || (type == DTM.ATTRIBUTE_NODE)) {
                    _components[i] = _components[0];
                    _components[0] = step;
                }
                // Check if the union contains a reverse iterator
        if (Axis.isReverse(axis)) _reverse = true;
            }
        }
        // No need to reverse anything if another expression lies on top of this
        if (getParent() instanceof Expression) _reverse = false;
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        final int length = _components.length;
        for (int i = 0; i < length; i++) {
            if (_components[i].typeCheck(stable) != Type.NodeSet) {
                _components[i] = new CastExpr(_components[i], Type.NodeSet);
            }
        }
        return _type = Type.NodeSet;
    }

    public String toString() {
        return "union(" + _pathExpr + ", " + _rest + ')';
    }

    private void flatten(List<Expression> components) {
        components.add(_pathExpr);
        if (_rest != null) {
            if (_rest instanceof UnionPathExpr) {
                ((UnionPathExpr)_rest).flatten(components);
            }
            else {
                components.add(_rest);
            }
        }
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        final int init = cpg.addMethodref(UNION_ITERATOR_CLASS,
                                          "<init>",
                                          "("+DOM_INTF_SIG+")V");
        final int iter = cpg.addMethodref(UNION_ITERATOR_CLASS,
                                          ADD_ITERATOR,
                                          ADD_ITERATOR_SIG);

        // Create the UnionIterator and leave it on the stack
        il.append(new NEW(cpg.addClass(UNION_ITERATOR_CLASS)));
        il.append(DUP);
        il.append(methodGen.loadDOM());
        il.append(new INVOKESPECIAL(init));

        // Add the various iterators to the UnionIterator
        final int length = _components.length;
        for (int i = 0; i < length; i++) {
            _components[i].translate(classGen, methodGen);
            il.append(new INVOKEVIRTUAL(iter));
        }

        // Order the iterator only if strictly needed
        if (_reverse) {
            final int order = cpg.addInterfaceMethodref(DOM_INTF,
                                                        ORDER_ITERATOR,
                                                        ORDER_ITERATOR_SIG);
            il.append(methodGen.loadDOM());
            il.append(SWAP);
            il.append(methodGen.loadContextNode());
            il.append(new INVOKEINTERFACE(order, 3));

        }
    }
}