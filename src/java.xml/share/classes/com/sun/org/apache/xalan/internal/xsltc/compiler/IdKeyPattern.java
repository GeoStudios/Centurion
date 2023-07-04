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

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.IFNE;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;

/**
 */
abstract class IdKeyPattern extends LocationPathPattern {

    protected RelativePathPattern _left = null;
    private String _index = null;
    private String _value = null;

    public IdKeyPattern(String index, String value) {
        _index = index;
        _value = value;
    }

    public String getIndexName() {
        return(_index);
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        return Type.NodeSet;
    }

    public boolean isWildcard() {
        return false;
    }

    public void setLeft(RelativePathPattern left) {
        _left = left;
    }

    public StepPattern getKernelPattern() {
        return(null);
    }

    public void reduceKernelPattern() { }

    public String toString() {
        return "id/keyPattern(" + _index + ", " + _value + ')';
    }

    /**
     * This method is called when the constructor is compiled in
     * Stylesheet.compileConstructor() and not as the syntax tree is traversed.
     */
    public void translate(ClassGenerator classGen,
                          MethodGenerator methodGen) {

        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        // Returns the KeyIndex object of a given name
        final int getKeyIndex = cpg.addMethodref(TRANSLET_CLASS,
                                                 "getKeyIndex",
                                                 "(Ljava/lang/String;)"+
                                                 KEY_INDEX_SIG);

        // Initialises a KeyIndex to return nodes with specific values
        final int lookupId = cpg.addMethodref(KEY_INDEX_CLASS,
                                              "containsID",
                                              "(ILjava/lang/Object;)I");
        final int lookupKey = cpg.addMethodref(KEY_INDEX_CLASS,
                                               "containsKey",
                                               "(ILjava/lang/Object;)I");
        final int getNodeIdent = cpg.addInterfaceMethodref(DOM_INTF,
                                                           "getNodeIdent",
                                                           "(I)"+NODE_SIG);

        // Call getKeyIndex in AbstractTranslet with the name of the key
        // to get the index for this key (which is also a node iterator).
        il.append(classGen.loadTranslet());
        il.append(new PUSH(cpg,_index));
        il.append(new INVOKEVIRTUAL(getKeyIndex));

        // Now use the value in the second argument to determine what nodes
        // the iterator should return.
        il.append(SWAP);
        il.append(new PUSH(cpg,_value));
        if (this instanceof IdPattern)
        {
            il.append(new INVOKEVIRTUAL(lookupId));
        }
        else
        {
            il.append(new INVOKEVIRTUAL(lookupKey));
        }

        _trueList.add(il.append(new IFNE(null)));
        _falseList.add(il.append(new GOTO(null)));
    }

}
