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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.DCONST;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ICONST;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.PUTFIELD;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.BooleanType;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeType;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.RealType;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

final class Variable extends VariableBase {

    public int getIndex() {
        return (_local != null) ? _local.getIndex() : -1;
    }

    /**
     * Parse the contents of the variable
     */
    public void parseContents(Parser parser) {
        // Parse 'name' and 'select' attributes plus parameter contents
        super.parseContents(parser);

        // Add a ref to this var to its enclosing construct
        SyntaxTreeNode parent = getParent();
        if (parent instanceof Stylesheet) {
            // Mark this as a global variable
            _isLocal = false;
            // Check if a global variable with this name already exists...
            Variable var = parser.getSymbolTable().lookupVariable(_name);
            // ...and if it does we need to check import precedence
            if (var != null) {
                final int us = this.getImportPrecedence();
                final int them = var.getImportPrecedence();
                // It is an error if the two have the same import precedence
                if (us == them) {
                    final String name = _name.toString();
                    reportError(this, parser, ErrorMsg.VARIABLE_REDEF_ERR,name);
                }
                // Ignore this if previous definition has higher precedence
                else if (them > us) {
                    _ignore = true;
                    copyReferences(var);
                    return;
                }
                else {
                    var.copyReferences(this);
                    var.disable();
                }
                // Add this variable if we have higher precedence
            }
            ((Stylesheet)parent).addVariable(this);
            parser.getSymbolTable().addVariable(this);
        }
        else {
            _isLocal = true;
        }
    }

    /**
     * Runs a type check on either the variable element body or the
     * expression in the 'select' attribute
     */
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {

        // Type check the 'select' expression if present
        if (_select != null) {
            _type = _select.typeCheck(stable);
        }
        // Type check the element contents otherwise
        else if (hasContents()) {
            typeCheckContents(stable);
            _type = Type.ResultTree;
        }
        else {
            _type = Type.Reference;
        }
        // The return type is void as the variable element does not leave
        // anything on the JVM's stack. The '_type' global will be returned
        // by the references to this variable, and not by the variable itself.
        return Type.Void;
    }

    /**
     * This method is part of a little trick that is needed to use local
     * variables inside nested for-each loops. See the initializeVariables()
     * method in the ForEach class for an explanation
     */
    public void initialize(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        // This is only done for local variables that are actually used
        if (isLocal() && !_refs.isEmpty()) {
            // Create a variable slot if none is allocated
            if (_local == null) {
                _local = methodGen.addLocalVariable2(getEscapedName(),
                                                     _type.toJCType(),
                                                     null);
            }
            // Push the default value on the JVM's stack
            if ((_type instanceof IntType) ||
                (_type instanceof NodeType) ||
                (_type instanceof BooleanType))
                il.append(new ICONST(0)); // 0 for node-id, integer and boolean
            else if (_type instanceof RealType)
                il.append(new DCONST(0)); // 0.0 for floating point numbers
            else
                il.append(new ACONST_NULL()); // and 'null' for anything else

            // Mark the store as the start of the live range of the variable
            _local.setStart(il.append(_type.STORE(_local.getIndex())));

        }
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        // Don't generate code for unreferenced variables
        if (_refs.isEmpty()) {
            _ignore = true;
        }

        // Make sure that a variable instance is only compiled once
        if (_ignore) return;
        _ignore = true;

        final String name = getEscapedName();

        if (isLocal()) {
            // Compile variable value computation
            translateValue(classGen, methodGen);

            // Add a new local variable and store value
            boolean createLocal = _local == null;
            if (createLocal) {
                mapRegister(methodGen);
            }
            InstructionHandle storeInst =
            il.append(_type.STORE(_local.getIndex()));

            // If the local is just being created, mark the store as the start
            // of its live range.  Note that it might have been created by
            // initializeVariables already, which would have set the start of
            // the live range already.
            if (createLocal) {
                _local.setStart(storeInst);
        }
        }
        else {
            String signature = _type.toSignature();

            // Global variables are store in class fields
            if (classGen.containsField(name) == null) {
                classGen.addField(new Field(ACC_PUBLIC,
                                            cpg.addUtf8(name),
                                            cpg.addUtf8(signature),
                                            null, cpg.getConstantPool()));

                // Push a reference to "this" for putfield
                il.append(classGen.loadTranslet());
                // Compile variable value computation
                translateValue(classGen, methodGen);
                // Store the variable in the allocated field
                il.append(new PUTFIELD(cpg.addFieldref(classGen.getClassName(),
                                                       name, signature)));
            }
        }
    }
}