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

import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodType;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;

/**
 * @LastModified: Oct 2017
 */
final class UnaryOpExpr extends Expression {
    private Expression _left;

    public UnaryOpExpr(Expression left) {
        (_left = left).setParent(this);
    }

    /**
     * Returns true if this expressions contains a call to position(). This is
     * needed for context changes in node steps containing multiple predicates.
     */
    public boolean hasPositionCall() {
        return(_left.hasPositionCall());
    }

    /**
     * Returns true if this expressions contains a call to last()
     */
    public boolean hasLastCall() {
            return(_left.hasLastCall());
    }

    public void setParser(Parser parser) {
        super.setParser(parser);
        _left.setParser(parser);
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        final Type tleft = _left.typeCheck(stable);
        final MethodType ptype = lookupPrimop(stable, "u-",
                                              new MethodType(Type.Void,
                                                             tleft));

        if (ptype != null) {
            final Type arg1 = ptype.argsType().get(0);
            if (!arg1.identicalTo(tleft)) {
                _left = new CastExpr(_left, arg1);
            }
            return _type = ptype.resultType();
        }

        throw new TypeCheckError(this);
    }

    public String toString() {
        return "u-" + '(' + _left + ')';
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        InstructionList il = methodGen.getInstructionList();
        _left.translate(classGen, methodGen);
        il.append(_type.NEG());
    }
}