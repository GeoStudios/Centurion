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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.NEW;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 */
final class AbsoluteLocationPath extends Expression {
    private Expression _path;   // may be null

    public AbsoluteLocationPath() {
        _path = null;
    }

    public AbsoluteLocationPath(Expression path) {
        _path = path;
        if (path != null) {
            _path.setParent(this);
        }
    }

    public void setParser(Parser parser) {
        super.setParser(parser);
        if (_path != null) {
            _path.setParser(parser);
        }
    }

    public Expression getPath() {
        return(_path);
    }

    public String toString() {
        return "AbsoluteLocationPath(" +
            (_path != null ? _path.toString() : "null") + ')';
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        if (_path != null) {
            final Type ptype = _path.typeCheck(stable);
            if (ptype instanceof NodeType) {            // promote to node-set
                _path = new CastExpr(_path, Type.NodeSet);
            }
        }
        return _type = Type.NodeSet;
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        if (_path != null) {
            final int initAI = cpg.addMethodref(ABSOLUTE_ITERATOR,
                                                "<init>",
                                                "("
                                                + NODE_ITERATOR_SIG
                                                + ")V");

            // Compile relative path iterator(s)
            //
            // Backwards branches are prohibited if an uninitialized object is
            // on the stack by section 4.9.4 of the JVM Specification, 2nd Ed.
            // We don't know whether this code might contain backwards branches,
            // so we mustn't create the new object until after we've created
            // this argument to its constructor.  Instead we calculate the
            // value of the argument to the constructor first, store it in
            // a temporary variable, create the object and reload the argument
            // from the temporary to avoid the problem.
            _path.translate(classGen, methodGen);
            LocalVariableGen relPathIterator
                    = methodGen.addLocalVariable("abs_location_path_tmp",
                                       Util.getJCRefType(NODE_ITERATOR_SIG),
                                       null, null);
            relPathIterator.setStart(
                    il.append(new ASTORE(relPathIterator.getIndex())));

            // Create new AbsoluteIterator
            il.append(new NEW(cpg.addClass(ABSOLUTE_ITERATOR)));
            il.append(DUP);
            relPathIterator.setEnd(
                    il.append(new ALOAD(relPathIterator.getIndex())));

            // Initialize AbsoluteIterator with iterator from the stack
            il.append(new INVOKESPECIAL(initAI));
        }
        else {
            final int gitr = cpg.addInterfaceMethodref(DOM_INTF,
                                                       "getIterator",
                                                       "()"+NODE_ITERATOR_SIG);
            il.append(methodGen.loadDOM());
            il.append(new INVOKEINTERFACE(gitr, 1));
        }
    }
}
