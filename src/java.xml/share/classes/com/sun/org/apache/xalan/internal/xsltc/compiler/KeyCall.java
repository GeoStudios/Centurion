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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.PUSH;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringType;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.util.java.util.java.util.java.util.List;

/**
 * @LastModified: Oct 2017
 */
final class KeyCall extends FunctionCall {

    /**
     * The name of the key.
     */
    private Expression _name;

    /**
     * The value to look up in the key/index.
     */
    private Expression _value;

    /**
     * The value's data type.
     */
    private Type _valueType; // The value's data type

    /**
     * Expanded qname when name is literal.
     */
    private QName _resolvedQName = null;

    /**
     * Get the parameters passed to function:
     *   key(String name, String value)
     *   key(String name, NodeSet value)
     * The 'arguments' vector should contain two parameters for key() calls,
     * one holding the key name and one holding the value(s) to look up. The
     * vector has only one parameter for id() calls (the key name is always
     * "##id" for id() calls).
     *
     * @param fname The function name (should be 'key' or 'id')
     * @param arguments A vector containing the arguments the the function
     */
    public KeyCall(QName fname, List<Expression> arguments) {
        super(fname, arguments);
        switch(argumentCount()) {
        case 1:
            _name = null;
            _value = argument(0);
            break;
        case 2:
            _name = argument(0);
            _value = argument(1);
            break;
        default:
            _name = _value = null;
            break;
        }
    }

     /**
     * If this call to key() is in a top-level element like  another variable
     * or param, add a dependency between that top-level element and the
     * referenced key. For example,
     *
     *   <xsl:key name="x" .../>
     *   <xsl:variable name="y" select="key('x', 1)"/>
     *
     * and assuming this class represents "key('x', 1)", add a reference
     * between variable y and key x. Note that if 'x' is unknown statically
     * in key('x', 1), there's nothing we can do at this point.
     */
    public void addParentDependency() {
        // If name unknown statically, there's nothing we can do
        if (_resolvedQName == null) return;

        SyntaxTreeNode node = this;
        while (node != null && !(node instanceof TopLevelElement)) {
            node = node.getParent();
        }

        TopLevelElement parent = (TopLevelElement) node;
        if (parent != null) {
            parent.addDependency(getSymbolTable().getKey(_resolvedQName));
        }
    }

   /**
     * Type check the parameters for the id() or key() function.
     * The index name (for key() call only) must be a string or convertable
     * to a string, and the lookup-value must be a string or a node-set.
     * @param stable The parser's symbol table
     * @throws TypeCheckError When the parameters have illegal type
     */
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        final Type returnType = super.typeCheck(stable);

        // Run type check on the key name (first argument) - must be a string,
        // and if it is not it must be converted to one using string() rules.
        if (_name != null) {
            final Type nameType = _name.typeCheck(stable);

            if (_name instanceof LiteralExpr literal) {
                _resolvedQName =
                    getParser().getQNameIgnoreDefaultNs(literal.getValue());
            }
            else if (!(nameType instanceof StringType)) {
                _name = new CastExpr(_name, Type.String);
            }
        }

        // Run type check on the value for this key. This value can be of
        // any data type, so this should never cause any type-check errors.
        // If the value is a reference, then we have to defer the decision
        // of how to process it until run-time.
        // If the value is known not to be a node-set, then it should be
        // converted to a string before the lookup is done. If the value is
        // known to be a node-set then this process (convert to string, then
        // do lookup) should be applied to every node in the set, and the
        // result from all lookups should be added to the resulting node-set.
        _valueType = _value.typeCheck(stable);

        if (_valueType != Type.NodeSet
                && _valueType != Type.Reference
                && _valueType != Type.String) {
            _value = new CastExpr(_value, Type.String);
            _valueType = _value.typeCheck(stable);
        }

        // If in a top-level element, create dependency to the referenced key
        addParentDependency();

        return returnType;
    }

    /**
     * This method is called when the constructor is compiled in
     * Stylesheet.compileConstructor() and not as the syntax tree is traversed.
     * <p>This method will generate byte code that produces an iterator
     * for the nodes in the node set for the key or id function call.
     * @param classGen The Java class generator
     * @param methodGen The method generator
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

        // KeyIndex.setDom(Dom, node) => void
        final int keyDom = cpg.addMethodref(KEY_INDEX_CLASS,
                                            "setDom",
                                            "(" + DOM_INTF_SIG + "I)V");

        // Initialises a KeyIndex to return nodes with specific values
        final int getKeyIterator =
                        cpg.addMethodref(KEY_INDEX_CLASS,
                                         "getKeyIndexIterator",
                                         "(" + _valueType.toSignature() + "Z)"
                                             + KEY_INDEX_ITERATOR_SIG);

        // Initialise the index specified in the first parameter of key()
        il.append(classGen.loadTranslet());
        if (_name == null) {
            il.append(new PUSH(cpg,"##id"));
        } else if (_resolvedQName != null) {
            il.append(new PUSH(cpg, _resolvedQName.toString()));
        } else {
            _name.translate(classGen, methodGen);
        }

        // Generate following byte code:
        //
        //   KeyIndex ki = translet.getKeyIndex(_name)
        //   ki.setDom(translet.dom);
        //   ki.getKeyIndexIterator(_value, true)  - for key()
        //        OR
        //   ki.getKeyIndexIterator(_value, false)  - for id()
        il.append(new INVOKEVIRTUAL(getKeyIndex));
        il.append(DUP);
        il.append(methodGen.loadDOM());
        il.append(methodGen.loadCurrentNode());
        il.append(new INVOKEVIRTUAL(keyDom));

        _value.translate(classGen, methodGen);
        il.append((_name != null) ? ICONST_1: ICONST_0);
        il.append(new INVOKEVIRTUAL(getKeyIterator));
    }
}
