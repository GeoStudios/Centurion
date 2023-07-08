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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.PUSH;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.RealType;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringType;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.java.util.java.util.java.util.List;

/**
 * @LastModified: Oct 2017
 */
final class FormatNumberCall extends FunctionCall {
    private Expression _value;
    private Expression _format;
    private Expression _name;
    private QName      _resolvedQName = null;

    public FormatNumberCall(QName fname, List<Expression> arguments) {
        super(fname, arguments);
        _value = argument(0);
        _format = argument(1);
        _name = argumentCount() == 3 ? argument(2) : null;
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {

        // Inform stylesheet to instantiate a DecimalFormat object
        getStylesheet().numberFormattingUsed();

        final Type tvalue = _value.typeCheck(stable);
        if (!(tvalue instanceof RealType)) {
            _value = new CastExpr(_value, Type.Real);
        }
        final Type tformat = _format.typeCheck(stable);
        if (!(tformat instanceof StringType)) {
            _format = new CastExpr(_format, Type.String);
        }
        if (argumentCount() == 3) {
            final Type tname = _name.typeCheck(stable);

            if (_name instanceof LiteralExpr literal) {
                _resolvedQName =
                    getParser().getQNameIgnoreDefaultNs(literal.getValue());
            }
            else if (!(tname instanceof StringType)) {
                _name = new CastExpr(_name, Type.String);
            }
        }
        return _type = Type.String;
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        _value.translate(classGen, methodGen);
        _format.translate(classGen, methodGen);

        final int fn3arg = cpg.addMethodref(BASIS_LIBRARY_CLASS,
                                            "formatNumber",
                                            "(DLjava/lang/String;"+
                                            "Ljava/text/DecimalFormat;)"+
                                            "Ljava/lang/String;");
        final int get = cpg.addMethodref(TRANSLET_CLASS,
                                         "getDecimalFormat",
                                         "(Ljava/lang/String;)"+
                                         "Ljava/text/DecimalFormat;");

        il.append(classGen.loadTranslet());
        if (_name == null) {
            il.append(new PUSH(cpg, EMPTYSTRING));
        }
        else if (_resolvedQName != null) {
            il.append(new PUSH(cpg, _resolvedQName.toString()));
        }
        else {
            _name.translate(classGen, methodGen);
        }
        il.append(new INVOKEVIRTUAL(get));
        il.append(new INVOKESTATIC(fn3arg));
    }
}
