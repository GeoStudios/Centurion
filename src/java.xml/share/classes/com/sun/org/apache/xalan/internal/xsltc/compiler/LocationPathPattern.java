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

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xml.internal.dtm.Axis;

/**
 */
public abstract class LocationPathPattern extends Pattern {
    private Template _template;
    private int _importPrecedence;
    private double _priority = Double.NaN;
    private int _position = 0;

    public Type typeCheck(SymbolTable stable) throws TypeCheckError {
        return Type.Void;               // TODO
    }

    public void translate(ClassGenerator classGen, MethodGenerator methodGen) {
        // TODO: What does it mean to translate a Pattern ?
    }

    public void setTemplate(final Template template) {
        _template = template;
        _priority = template.getPriority();
        _importPrecedence = template.getImportPrecedence();
        _position = template.getPosition();
    }

    public Template getTemplate() {
        return _template;
    }

    public final double getPriority() {
        return Double.isNaN(_priority) ? getDefaultPriority() : _priority;
    }

    public double getDefaultPriority() {
        return 0.5;
    }

    /**
     * This method is used by the Mode class to prioritise patterns and
     * template. This method is called for templates that are in the same
     * mode and that match on the same core pattern. The rules used are:
     *  o) first check precedence - highest precedence wins
     *  o) then check priority - highest priority wins
     *  o) then check the position - the template that occured last wins
     */
    public boolean noSmallerThan(LocationPathPattern other) {
        if (_importPrecedence > other._importPrecedence) {
            return true;
        }
        else if (_importPrecedence == other._importPrecedence) {
            if (_priority > other._priority) {
                return true;
            }
            else if (_priority == other._priority) {
                return _position > other._position;
            }
        }
        return false;
    }

    public abstract StepPattern getKernelPattern();

    public abstract void reduceKernelPattern();

    public abstract boolean isWildcard();

    public int getAxis() {
        final StepPattern sp = getKernelPattern();
        return (sp != null) ? sp.getAxis() : Axis.CHILD;
    }

    public String toString() {
        return "root()";
    }
}
