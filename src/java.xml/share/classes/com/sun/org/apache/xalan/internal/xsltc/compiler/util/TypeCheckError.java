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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util;


import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 */
public class TypeCheckError extends Exception {
    static final long serialVersionUID = 3246224233917854640L;
    ErrorMsg _error = null;
    SyntaxTreeNode _node = null;

    public TypeCheckError(SyntaxTreeNode node) {
        super();
        _node = node;
    }

    public TypeCheckError(ErrorMsg error) {
        super();
        _error = error;
    }

    public TypeCheckError(String code, Object param) {
        super();
        _error = new ErrorMsg(code, param);
    }

    public TypeCheckError(String code, Object param1, Object param2) {
        super();
        _error = new ErrorMsg(code, param1, param2);
    }

    public ErrorMsg getErrorMsg() {
        return _error;
    }

    public String getMessage() {
        return toString();
    }

    public String toString() {
        String result;

        if (_error == null) {
            if (_node != null) {
                _error = new ErrorMsg(ErrorMsg.TYPE_CHECK_ERR,
                                      _node.toString());
            } else {
                _error = new ErrorMsg(ErrorMsg.TYPE_CHECK_UNK_LOC_ERR);
            }
        }

        return _error.toString();
    }
}
