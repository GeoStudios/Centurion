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

import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.java.util.java.util.java.util.List;

/**
 * @LastModified: Oct 2017
 */
final class BooleanCall extends FunctionCall {

    private Expression _arg = null;

    public BooleanCall(QName fname, List<Expression> arguments) {
        super(fname, arguments);
        _arg = argument(0);
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        _arg.typeCheck(stable);
        return _type = Type.Boolean;
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        _arg.translate(classGen, methodGen);
        final Type targ = _arg.getType();
        if (!targ.identicalTo(Type.Boolean)) {
            _arg.startIterator(classGen, methodGen);
            targ.translateTo(classGen, methodGen, Type.Boolean);
        }
    }
}
