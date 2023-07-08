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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.CHECKCAST;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.PUSH;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ReferenceType;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;
import java.xml.share.classes.com.sun.org.apache.xml.internal.utils.XML11Char;















/**
 */
final class WithParam extends Instruction {

    /**
     * Parameter's name.
     */
    private QName _name;

    /**
     * The escaped qname of the with-param.
     */
    private String _escapedName;

    /**
     * Parameter's default value.
     */
    private Expression _select;

    /**
     * Reference to JVM variable holding temporary result tree.
     */
    private LocalVariableGen _domAdapter;

    /**
     * %OPT% This is set to true when the WithParam is used in a CallTemplate
     * for a simple named template. If this is true, the parameters are
     * passed to the named template through method arguments rather than
     * using the expensive Translet.addParameter() call.
     */
    private boolean _doParameterOptimization = false;

    /**
     * Displays the contents of this element
     */
    public void display(int indent) {
        indent(indent);
        Util.println("with-param " + _name);
        if (_select != null) {
            indent(indent + IndentIncrement);
            Util.println("select " + _select.toString());
        }
        displayContents(indent + IndentIncrement);
    }

    /**
     * Returns the escaped qname of the parameter
     */
    public String getEscapedName() {
        return _escapedName;
    }

    /**
     * Return the name of this WithParam.
     */
    public QName getName() {
        return _name;
    }

    /**
     * Set the name of the variable or paremeter. Escape all special chars.
     */
    public void setName(QName name) {
        _name = name;
        _escapedName = Util.escape(name.getStringRep());
    }

    /**
     * Set the do parameter optimization flag
     */
    public void setDoParameterOptimization(boolean flag) {
        _doParameterOptimization = flag;
    }

    /**
     * The contents of a <xsl:with-param> elements are either in the element's
     * 'select' attribute (this has precedence) or in the element body.
     */
    public void parseContents(Parser parser) {
        final String name = getAttribute("name");
        if (name.length() > 0) {
            if (!XML11Char.isXML11ValidQName(name)) {
                ErrorMsg err = new ErrorMsg(ErrorMsg.INVALID_QNAME_ERR, name,
                                            this);
                parser.reportError(Constants.ERROR, err);
            }
            setName(parser.getQNameIgnoreDefaultNs(name));
        } else {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, "name");
        }

        final String select = getAttribute("select");
        if (select.length() > 0) {
            _select = parser.parseExpression(this, "select", null);
        }

        parseChildren(parser);
    }

    /**
     * Type-check either the select attribute or the element body, depending
     * on which is in use.
     */
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        if (_select != null) {
            final Type tselect = _select.typeCheck(stable);
            if (!(tselect instanceof ReferenceType)) {
                _select = new CastExpr(_select, Type.Reference);
            }
        } else {
            typeCheckContents(stable);
        }
        return Type.Void;
    }

    /**
     * Compile the value of the parameter, which is either in an expression in
     * a 'select' attribute, or in the with-param element's body
     */
    public void translateValue(ClassGenerator classGen,
                               MethodGenerator methodGen)
    {
        // Compile expression is 'select' attribute if present
        if (_select != null) {
            _select.translate(classGen, methodGen);
            _select.startIterator(classGen, methodGen);
        // If not, compile result tree from parameter body if present.
        // Store result tree into local variable for releasing it later
        } else if (hasContents()) {
            final InstructionList il = methodGen.getInstructionList();
            compileResultTree(classGen, methodGen);
            _domAdapter = methodGen.addLocalVariable2("@" + _escapedName,
                                                      Type.ResultTree.toJCType(),
                                                      il.getEnd());
            il.append(DUP);
            il.append(new ASTORE(_domAdapter.getIndex()));
        // If neither are present then store empty string in parameter slot
        } else {
            final ConstantPoolGen cpg = classGen.getConstantPool();
            final InstructionList il = methodGen.getInstructionList();
            il.append(new PUSH(cpg, Constants.EMPTYSTRING));
        }
    }

    /**
     * This code generates a sequence of bytecodes that call the
     * addParameter() method in AbstractTranslet. The method call will add
     * (or update) the parameter frame with the new parameter value.
     */
    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        // Translate the value and put it on the stack
        if (_doParameterOptimization) {
            translateValue(classGen, methodGen);
            return;
        }

        // Make name acceptable for use as field name in class
        String name = Util.escape(getEscapedName());

        // Load reference to the translet (method is in AbstractTranslet)
        il.append(classGen.loadTranslet());

        // Load the name of the parameter
        il.append(new PUSH(cpg, name)); // TODO: namespace ?
        // Generete the value of the parameter (use value in 'select' by def.)
        translateValue(classGen, methodGen);
        // Mark this parameter value is not being the default value
        il.append(new PUSH(cpg, false));
        // Pass the parameter to the template
        il.append(new INVOKEVIRTUAL(cpg.addMethodref(TRANSLET_CLASS,
                                                     ADD_PARAMETER,
                                                     ADD_PARAMETER_SIG)));
        il.append(POP); // cleanup stack
    }

    /**
     * Release the compiled result tree.
     */
    public void releaseResultTree(ClassGenerator classGen,
                                  MethodGenerator methodGen)
    {
        if (_domAdapter != null) {
            final ConstantPoolGen cpg = classGen.getConstantPool();
            final InstructionList il = methodGen.getInstructionList();
            if (classGen.getStylesheet().callsNodeset() &&
                classGen.getDOMClass().equals(MULTI_DOM_CLASS))
            {
                final int removeDA =
                    cpg.addMethodref(MULTI_DOM_CLASS, "removeDOMAdapter",
                                     "(" + DOM_ADAPTER_SIG + ")V");
                il.append(methodGen.loadDOM());
                il.append(new CHECKCAST(cpg.addClass(MULTI_DOM_CLASS)));
                il.append(new ALOAD(_domAdapter.getIndex()));
                il.append(new CHECKCAST(cpg.addClass(DOM_ADAPTER_CLASS)));
                il.append(new INVOKEVIRTUAL(removeDA));
            }
            final int release =
                cpg.addInterfaceMethodref(DOM_IMPL_CLASS, "release", "()V");
            il.append(new ALOAD(_domAdapter.getIndex()));
            il.append(new INVOKEINTERFACE(release, 1));
            _domAdapter.setEnd(il.getEnd());
            methodGen.removeLocalVariable(_domAdapter);
            _domAdapter = null;
        }
    }
}
