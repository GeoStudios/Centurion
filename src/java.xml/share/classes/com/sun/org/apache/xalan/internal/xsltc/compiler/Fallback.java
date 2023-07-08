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
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 */
final class Fallback extends Instruction {

    private boolean _active = false;

    /**
     * This element never produces any data on the stack
     */
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        if (_active) {
            return(typeCheckContents(stable));
        }
        else {
            return Type.Void;
        }
    }

    /**
     * Activate this fallback element
     */
    public void activate() {
        _active = true;
    }

    public String toString() {
        return("fallback");
    }

    /**
     * Parse contents only if this fallback element is put in place of
     * some unsupported element or non-XSLTC extension element
     */
    public void parseContents(Parser parser) {
        if (_active) parseChildren(parser);
    }

    /**
     * Translate contents only if this fallback element is put in place of
     * some unsupported element or non-XSLTC extension element
     */
    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final ConstantPoolGen cpg = classGen.getConstantPool();
        final InstructionList il = methodGen.getInstructionList();

        if (_active) translateContents(classGen, methodGen);
    }
}
