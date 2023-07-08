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


import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.CHECKCAST;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.GETFIELD;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeSetType;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.runtime.BasisLibrary;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 */
final class ParameterRef extends VariableRefBase {

    /**
     * Name of param being referenced.
     */
    QName _name = null;

    public ParameterRef(Param param) {
        super(param);
        _name = param._name;

    }

    public String toString() {
        return "parameter-ref("+_variable.getName()+'/'+_variable.getType()+')';
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        /*
         * To fix bug 24518 related to setting parameters of the form
         * {namespaceuri}localName, which will get mapped to an instance
         * variable in the class.
         */
        final String name = BasisLibrary.mapQNameToJavaName (_name.toString());
        final String signature = _type.toSignature();

        if (_variable.isLocal()) {
            if (classGen.isExternal()) {
                Closure variableClosure = _closure;
                while (variableClosure != null) {
                    if (variableClosure.inInnerClass()) break;
                    variableClosure = variableClosure.getParentClosure();
                }

                if (variableClosure != null) {
                    il.append(ALOAD_0);
                    il.append(new GETFIELD(
                        cpg.addFieldref(variableClosure.getInnerClassName(),
                            name, signature)));
                }
                else {
                    il.append(_variable.loadInstruction());
                }
            }
            else {
                il.append(_variable.loadInstruction());
            }
        }
        else {
            final String className = classGen.getClassName();
            il.append(classGen.loadTranslet());
            if (classGen.isExternal()) {
                il.append(new CHECKCAST(cpg.addClass(className)));
            }
            il.append(new GETFIELD(cpg.addFieldref(className,name,signature)));
        }

        if (_variable.getType() instanceof NodeSetType) {
            // The method cloneIterator() also does resetting
            final int clone = cpg.addInterfaceMethodref(NODE_ITERATOR,
                                                       "cloneIterator",
                                                       "()" +
                                                        NODE_ITERATOR_SIG);
            il.append(new INVOKEINTERFACE(clone, 1));
        }
    }
}
