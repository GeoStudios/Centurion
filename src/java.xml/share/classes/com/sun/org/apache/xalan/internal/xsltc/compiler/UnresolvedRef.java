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

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;

/**
 */
final class UnresolvedRef extends VariableRefBase {

    private QName _variableName = null;
    private VariableRefBase _ref = null;

    public UnresolvedRef(QName name) {
        super();
        _variableName = name;
    }

    public QName getName() {
        return(_variableName);
    }

    private ErrorMsg reportError() {
        ErrorMsg err = new ErrorMsg(ErrorMsg.VARIABLE_UNDEF_ERR,
                                    _variableName, this);
        getParser().reportError(Constants.ERROR, err);
        return(err);
    }

    private VariableRefBase resolve(Parser parser, SymbolTable stable) {
        // At this point the AST is already built and we should be able to
        // find any declared global variable or parameter
        VariableBase ref = parser.lookupVariable(_variableName);
        if (ref == null) {
            ref = (VariableBase)stable.lookupName(_variableName);
        }
        if (ref == null) {
            reportError();
            return null;
        }

        // If in a top-level element, create dependency to the referenced var
        _variable = ref;
        addParentDependency();

        if (ref instanceof Variable) {
            return new VariableRef((Variable) ref);
        }
        else if (ref instanceof Param) {
            return new ParameterRef((Param)ref);
        }
        return null;
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        if (_ref != null) {
            final String name = _variableName.toString();
            ErrorMsg err = new ErrorMsg(ErrorMsg.CIRCULAR_VARIABLE_ERR,
                                        name, this);
        }
        if ((_ref = resolve(getParser(), stable)) != null) {
            return (_type = _ref.typeCheck(stable));
        }
        throw new TypeCheckError(reportError());
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        if (_ref != null)
            _ref.translate(classGen, methodGen);
        else
            reportError();
    }

    public String toString() {
        return "unresolved-ref()";
    }

}
