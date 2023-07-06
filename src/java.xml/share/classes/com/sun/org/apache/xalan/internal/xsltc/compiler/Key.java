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

import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.BranchHandle;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.GOTO;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.IFEQ;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.IFGE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.IFGT;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ILOAD;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ISTORE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.PUSH;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeSetType;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringType;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.xml.share.classes.com.sun.org.apache.xml.internal.dtm.Axis;
import java.xml.share.classes.com.sun.org.apache.xml.internal.utils.XML11Char;

/*
 * $Id: Key.java,v 1.6 2006/04/25 02:25:08 jeffsuttor Exp $
 */

/**
 * @LastModified: Oct 2017
 */
final class Key extends TopLevelElement {

    /**
     * The name of this key as defined in xsl:key.
     */
    private QName _name;

    /**
     * The pattern to match starting at the root node.
     */
    private Pattern _match;

    /**
     * The expression that generates the values for this key.
     */
    private Expression _use;

    /**
     * The type of the _use expression.
     */
    private Type _useType;

    /**
     * Parse the <xsl:key> element and attributes
     * @param parser A reference to the stylesheet parser
     */
    public void parseContents(Parser parser) {

        // Get the required attributes and parser XPath expressions
        final String name = getAttribute("name");
        if (!XML11Char.isXML11ValidQName(name)){
            ErrorMsg err = new ErrorMsg(ErrorMsg.INVALID_QNAME_ERR, name, this);
            parser.reportError(Constants.ERROR, err);
        }

        // Parse key name and add to symbol table
        _name = parser.getQNameIgnoreDefaultNs(name);
        getSymbolTable().addKey(_name, this);

        _match = parser.parsePattern(this, "match", null);
        _use = parser.parseExpression(this, "use", null);

        // Make sure required attribute(s) have been set
        if (_name == null) {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, "name");
            return;
        }
        if (_match.isDummy()) {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, "match");
            return;
        }
        if (_use.isDummy()) {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, "use");
        }
    }

    /**
     * Returns a String-representation of this key's name
     * @return The key's name (from the <xsl:key> elements 'name' attribute).
     */
    public String getName() {
        return _name.toString();
    }

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        // Type check match pattern
        _match.typeCheck(stable);

        // Cast node values to string values (except for nodesets)
        _useType = _use.typeCheck(stable);
        if (!(_useType instanceof StringType) &&
                !(_useType instanceof NodeSetType))
        {
            _use = new CastExpr(_use, Type.String);
        }

        return Type.Void;
    }

    /**
     * This method is called if the "use" attribute of the key contains a
     * node set. In this case we must traverse all nodes in the set and
     * create one entry in this key's index for each node in the set.
     */
    public void traverseNodeSet(ClassGenerator classGen,
                                MethodGenerator methodGen,
                                int buildKeyIndex) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        // DOM.getStringValueX(nodeIndex) => String
        final int getNodeValue = cpg.addInterfaceMethodref(DOM_INTF,
                                                           GET_NODE_VALUE,
                                                           "(I)"+STRING_SIG);

        final int getNodeIdent = cpg.addInterfaceMethodref(DOM_INTF,
                                                           "getNodeIdent",
                                                           "(I)"+NODE_SIG);

        // AbstractTranslet.SetKeyIndexDom(name, Dom) => void
        final int keyDom = cpg.addMethodref(TRANSLET_CLASS,
                                         "setKeyIndexDom",
                                         "("+STRING_SIG+DOM_INTF_SIG+")V");

        // This variable holds the id of the node we found with the "match"
        // attribute of xsl:key. This is the id we store, with the value we
        // get from the nodes we find here, in the index for this key.
        final LocalVariableGen parentNode =
            methodGen.addLocalVariable("parentNode",
                                       Util.getJCRefType("I"),
                                       null, null);

        // Get the 'parameter' from the stack and store it in a local var.
        parentNode.setStart(il.append(new ISTORE(parentNode.getIndex())));

        // Save current node and current iterator on the stack
        il.append(methodGen.loadCurrentNode());
        il.append(methodGen.loadIterator());

        // Overwrite current iterator with one that gives us only what we want
        _use.translate(classGen, methodGen);
        _use.startIterator(classGen, methodGen);
        il.append(methodGen.storeIterator());

        final BranchHandle nextNode = il.append(new GOTO(null));
        final InstructionHandle loop = il.append(NOP);

        // Prepare to call buildKeyIndex(String name, int node, String value);
        il.append(classGen.loadTranslet());
        il.append(new PUSH(cpg, _name.toString()));
        parentNode.setEnd(il.append(new ILOAD(parentNode.getIndex())));

        // Now get the node value and push it on the parameter stack
        il.append(methodGen.loadDOM());
        il.append(methodGen.loadCurrentNode());
        il.append(new INVOKEINTERFACE(getNodeValue, 2));

        // Finally do the call to add an entry in the index for this key.
        il.append(new INVOKEVIRTUAL(buildKeyIndex));

        il.append(classGen.loadTranslet());
        il.append(new PUSH(cpg, getName()));
        il.append(methodGen.loadDOM());
        il.append(new INVOKEVIRTUAL(keyDom));

        nextNode.setTarget(il.append(methodGen.loadIterator()));
        il.append(methodGen.nextNode());

        il.append(DUP);
        il.append(methodGen.storeCurrentNode());
        il.append(new IFGE(loop)); // Go on to next matching node....

        // Restore current node and current iterator from the stack
        il.append(methodGen.storeIterator());
        il.append(methodGen.storeCurrentNode());
    }

    /**
     * Gather all nodes that match the expression in the attribute "match"
     * and add one (or more) entries in this key's index.
     */
    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {

        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();
        final int current = methodGen.getLocalIndex("current");

        // AbstractTranslet.buildKeyIndex(name,node_id,value) => void
        final int key = cpg.addMethodref(TRANSLET_CLASS,
                                         "buildKeyIndex",
                                         "("+STRING_SIG+"I"+STRING_SIG+")V");

        // AbstractTranslet.SetKeyIndexDom(name, Dom) => void
        final int keyDom = cpg.addMethodref(TRANSLET_CLASS,
                                         "setKeyIndexDom",
                                         "("+STRING_SIG+DOM_INTF_SIG+")V");

        final int getNodeIdent = cpg.addInterfaceMethodref(DOM_INTF,
                                                           "getNodeIdent",
                                                           "(I)"+NODE_SIG);

        // DOM.getAxisIterator(root) => NodeIterator
        final int git = cpg.addInterfaceMethodref(DOM_INTF,
                                                  "getAxisIterator",
                                                  "(I)"+NODE_ITERATOR_SIG);

        il.append(methodGen.loadCurrentNode());
        il.append(methodGen.loadIterator());

        // Get an iterator for all nodes in the DOM
        il.append(methodGen.loadDOM());
        il.append(new PUSH(cpg,Axis.DESCENDANT));
        il.append(new INVOKEINTERFACE(git, 2));

        // Reset the iterator to start with the root node
        il.append(methodGen.loadCurrentNode());
        il.append(methodGen.setStartNode());
        il.append(methodGen.storeIterator());

        // Loop for traversing all nodes in the DOM
        final BranchHandle nextNode = il.append(new GOTO(null));
        final InstructionHandle loop = il.append(NOP);

        // Check if the current node matches the pattern in "match"
        il.append(methodGen.loadCurrentNode());
        _match.translate(classGen, methodGen);
        _match.synthesize(classGen, methodGen); // Leaves 0 or 1 on stack
        final BranchHandle skipNode = il.append(new IFEQ(null));

        // If this is a node-set we must go through each node in the set
        if (_useType instanceof NodeSetType) {
            // Pass current node as parameter (we're indexing on that node)
            il.append(methodGen.loadCurrentNode());
            traverseNodeSet(classGen, methodGen, key);
        }
        else {
            il.append(classGen.loadTranslet());
            il.append(DUP);
            il.append(new PUSH(cpg, _name.toString()));
            il.append(DUP_X1);
            il.append(methodGen.loadCurrentNode());
            _use.translate(classGen, methodGen);
            il.append(new INVOKEVIRTUAL(key));

            il.append(methodGen.loadDOM());
            il.append(new INVOKEVIRTUAL(keyDom));
        }

        // Get the next node from the iterator and do loop again...
        final InstructionHandle skip = il.append(NOP);

        il.append(methodGen.loadIterator());
        il.append(methodGen.nextNode());
        il.append(DUP);
        il.append(methodGen.storeCurrentNode());
        il.append(new IFGT(loop));

        // Restore current node and current iterator from the stack
        il.append(methodGen.storeIterator());
        il.append(methodGen.storeCurrentNode());

        nextNode.setTarget(skip);
        skipNode.setTarget(skip);
    }
}
