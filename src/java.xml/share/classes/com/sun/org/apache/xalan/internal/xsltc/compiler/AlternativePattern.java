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


import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.GOTO;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.InstructionHandle;
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
final class AlternativePattern extends Pattern {
    private final Pattern _left;
    private final Pattern _right;

    /**
     * Construct an alternative pattern. The method <code>setParent</code>
     * should not be called in this case.
     */
    public AlternativePattern(Pattern left, Pattern right) {
        _left = left;
        _right = right;
    }

    public void setParser(Parser parser) {
        super.setParser(parser);
        _left.setParser(parser);
        _right.setParser(parser);
    }

    public Pattern getLeft() {
        return _left;
    }

    public Pattern getRight() {
        return _right;
    }

    /**
     * The type of an '|' is not really defined, hence null is returned.
     */
    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        _left.typeCheck(stable);
        _right.typeCheck(stable);
        return null;
    }

    public double getPriority() {
        double left = _left.getPriority();
        double right = _right.getPriority();

        if (left < right)
            return(left);
        else
            return(right);
    }

    public String toString() {
        return "alternative(" + _left + ", " + _right + ')';
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        final InstructionList il = methodGen.getInstructionList();

        _left.translate(classGen, methodGen);
        final InstructionHandle gotot = il.append(new GOTO(null));
        il.append(methodGen.loadContextNode());
        _right.translate(classGen, methodGen);

        _left._trueList.backPatch(gotot);
        _left._falseList.backPatch(gotot.getNext());

        _trueList.append(_right._trueList.add(gotot));
        _falseList.append(_right._falseList);
    }
}
