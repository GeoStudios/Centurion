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

import java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile.Field;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.BranchHandle;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.CHECKCAST;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.IFNONNULL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instruction;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.PUSH;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.PUTFIELD;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ReferenceType;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ObjectType;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 */
final class Param extends VariableBase {

    /**
     * True if this Param is declared in a simple named template.
     * This is used to optimize codegen for parameter passing
     * in named templates.
     */
    private boolean _isInSimpleNamedTemplate = false;

    /**
     * Display variable as single string
     */
    public String toString() {
        return "param(" + _name + ")";
    }

    /**
     * Set the instruction for loading the value of this variable onto the
     * JVM stack and returns the old instruction.
     */
    public Instruction setLoadInstruction(Instruction instruction) {
        Instruction tmp = _loadInstruction;
        _loadInstruction = instruction;
        return tmp;
    }

    /**
     * Set the instruction for storing a value from the stack into this
     * variable and returns the old instruction.
     */
    public Instruction setStoreInstruction(Instruction instruction) {
        Instruction tmp = _storeInstruction;
        _storeInstruction = instruction;
        return tmp;
    }

    /**
     * Display variable in a full AST dump
     */
    public void display(int indent) {
        indent(indent);
        System.out.println("param " + _name);
        if (_select != null) {
            indent(indent + IndentIncrement);
            System.out.println("select " + _select.toString());
        }
        displayContents(indent + IndentIncrement);
    }

    /**
     * Parse the contents of the <xsl:param> element. This method must read
     * the 'name' (required) and 'select' (optional) attributes.
     */
    public void parseContents(Parser parser) {

        // Parse 'name' and 'select' attributes plus parameter contents
        super.parseContents(parser);

        // Add a ref to this param to its enclosing construct
        final SyntaxTreeNode parent = getParent();
        if (parent instanceof Stylesheet) {
            // Mark this as a global parameter
            _isLocal = false;
            // Check if a global variable with this name already exists...
            Param param = parser.getSymbolTable().lookupParam(_name);
            // ...and if it does we need to check import precedence
            if (param != null) {
                final int us = this.getImportPrecedence();
                final int them = param.getImportPrecedence();
                // It is an error if the two have the same import precedence
                if (us == them) {
                    final String name = _name.toString();
                    reportError(this, parser, ErrorMsg.VARIABLE_REDEF_ERR,name);
                }
                // Ignore this if previous definition has higher precedence
                else if (them > us) {
                    _ignore = true;
                    copyReferences(param);
                    return;
                }
                else {
                    param.copyReferences(this);
                    param.disable();
                }
            }
            // Add this variable if we have higher precedence
            ((Stylesheet)parent).addParam(this);
            parser.getSymbolTable().addParam(this);
        }
        else if (parent instanceof Template template) {
            _isLocal = true;
            template.addParameter(this);
            if (template.isSimpleNamedTemplate()) {
                _isInSimpleNamedTemplate = true;
            }
        }
    }

    /**
     * Type-checks the parameter. The parameter type is determined by the
     * 'select' expression (if present) or is a result tree if the parameter
     * element has a body and no 'select' expression.
     */
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        if (_select != null) {
            _type = _select.typeCheck(stable);
            if (!(_type instanceof ReferenceType) && !(_type instanceof ObjectType)) {
                _select = new CastExpr(_select, Type.Reference);
            }
        }
        else if (hasContents()) {
            typeCheckContents(stable);
        }
        _type = Type.Reference;

        // This element has no type (the parameter does, but the parameter
        // element itself does not).
        return Type.Void;
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        if (_ignore) return;
        _ignore = true;

        /*
         * To fix bug 24518 related to setting parameters of the form
         * {namespaceuri}localName which will get mapped to an instance
         * variable in the class.
         */
        final String name = BasisLibrary.mapQNameToJavaName(_name.toString());
        final String signature = _type.toSignature();
        final String className = _type.getClassName();

        if (isLocal()) {
            /*
              * If simple named template then generate a conditional init of the
              * param using its default value:
              *       if (param == null) param = <default-value>
              */
            if (_isInSimpleNamedTemplate) {
                il.append(loadInstruction());
                BranchHandle ifBlock = il.append(new IFNONNULL(null));
                translateValue(classGen, methodGen);
                il.append(storeInstruction());
                ifBlock.setTarget(il.append(NOP));
                return;
            }

            il.append(classGen.loadTranslet());
            il.append(new PUSH(cpg, name));
            translateValue(classGen, methodGen);
            il.append(new PUSH(cpg, true));

            // Call addParameter() from this class
            il.append(new INVOKEVIRTUAL(cpg.addMethodref(TRANSLET_CLASS,
                                                         ADD_PARAMETER,
                                                         ADD_PARAMETER_SIG)));
            if (className != EMPTYSTRING) {
                il.append(new CHECKCAST(cpg.addClass(className)));
            }

            _type.translateUnBox(classGen, methodGen);

            if (_refs.isEmpty()) { // nobody uses the value
                il.append(_type.POP());
                _local = null;
            }
            else {              // normal case
                _local = methodGen.addLocalVariable2(name,
                                                     _type.toJCType(),
                                                     il.getEnd());
                // Cache the result of addParameter() in a local variable
                il.append(_type.STORE(_local.getIndex()));
            }
        }
        else {
            if (classGen.containsField(name) == null) {
                classGen.addField(new Field(ACC_PUBLIC, cpg.addUtf8(name),
                                            cpg.addUtf8(signature),
                                            null, cpg.getConstantPool()));
                il.append(classGen.loadTranslet());
                il.append(DUP);
                il.append(new PUSH(cpg, name));
                translateValue(classGen, methodGen);
                il.append(new PUSH(cpg, true));

                // Call addParameter() from this class
                il.append(new INVOKEVIRTUAL(cpg.addMethodref(TRANSLET_CLASS,
                                                     ADD_PARAMETER,
                                                     ADD_PARAMETER_SIG)));

                _type.translateUnBox(classGen, methodGen);

                // Cache the result of addParameter() in a field
                if (className != EMPTYSTRING) {
                    il.append(new CHECKCAST(cpg.addClass(className)));
                }
                il.append(new PUTFIELD(cpg.addFieldref(classGen.getClassName(),
                                                       name, signature)));
            }
        }
    }
}