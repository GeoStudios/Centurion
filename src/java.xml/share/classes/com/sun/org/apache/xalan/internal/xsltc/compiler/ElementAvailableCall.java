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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.PUSH;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.java.util.java.util.java.util.List;















/**
 * @LastModified: Oct 2017
 */
final class ElementAvailableCall extends FunctionCall {

    public ElementAvailableCall(QName fname, List<Expression> arguments) {
        super(fname, arguments);
    }

    /**
     * Force the argument to this function to be a literal string.
     */
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        if (argument() instanceof LiteralExpr) {
            return _type = Type.Boolean;
        }
        ErrorMsg err = new ErrorMsg(ErrorMsg.NEED_LITERAL_ERR,
                                    "element-available", this);
        throw new TypeCheckError(err);
    }

    /**
     * Returns an object representing the compile-time evaluation
     * of an expression. We are only using this for function-available
     * and element-available at this time.
     */
    public Object evaluateAtCompileTime() {
        return getResult() ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * Returns the result that this function will return
     */
    public boolean getResult() {
        try {
            final LiteralExpr arg = (LiteralExpr) argument();
            final String qname = arg.getValue();
            final int index = qname.indexOf(':');
            final String localName = (index > 0) ?
                qname.substring(index + 1) : qname;
            return getParser().elementSupported(arg.getNamespace(),
                                                localName);
        }
        catch (ClassCastException e) {
            return false;
        }
    }

    /**
     * Calls to 'element-available' are resolved at compile time since
     * the namespaces declared in the stylsheet are not available at run
     * time. Consequently, arguments to this function must be literals.
     */
    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final boolean result = getResult();
        methodGen.getInstructionList().append(new PUSH(cpg, result));
    }
}
