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

package java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.util;


import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ALOAD;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instruction;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.Stylesheet;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * This class implements auxliary classes needed to compile
 * filters (predicates). These classes defined a single method
 * of type <tt>TestGenerator</tt>.
 */
public final class FilterGenerator extends ClassGenerator {
    private static final int TRANSLET_INDEX = 5;   // translet
    private final Instruction _aloadTranslet;

    public FilterGenerator(String className, String superClassName,
                           String fileName,
                           int accessFlags, String[] interfaces,
                           Stylesheet stylesheet) {
        super(className, superClassName, fileName,
              accessFlags, interfaces, stylesheet);

        _aloadTranslet = new ALOAD(TRANSLET_INDEX);
    }

    /**
     * The index of the translet pointer within the execution of
     * the test method.
     */
    public Instruction loadTranslet() {
        return _aloadTranslet;
    }

    /**
     * Returns <tt>true</tt> since this class is external to the
     * translet.
     */
    public boolean isExternal() {
        return true;
    }
}
