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

import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ALOAD;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ASTORE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ILOAD;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.ISTORE;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instruction;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Instructionjava.util.java.util.java.util.List;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import java.xml.share.classes.com.sun.org.apache.bcel.internal.generic.Type;
import java.xml.share.classes.com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 */
public final class CompareGenerator extends MethodGenerator {

    private static final int DOM_INDEX      = 1;
    private static final int CURRENT_INDEX  = 2;
    private static final int LEVEL_INDEX    = 3;
    private static final int TRANSLET_INDEX = 4;
    private static final int LAST_INDEX     = 5;
    private int ITERATOR_INDEX = 6;

    private final Instruction _iloadCurrent;
    private final Instruction _istoreCurrent;
    private final Instruction _aloadDom;
    private final Instruction _iloadLast;
    private final Instruction _aloadIterator;
    private final Instruction _astoreIterator;

    public CompareGenerator(int access_flags, Type return_type,
                            Type[] arg_types, String[] arg_names,
                            String method_name, String class_name,
                            InstructionList il, ConstantPoolGen cp) {
        super(access_flags, return_type, arg_types, arg_names, method_name,
              class_name, il, cp);

        _iloadCurrent = new ILOAD(CURRENT_INDEX);
        _istoreCurrent = new ISTORE(CURRENT_INDEX);
        _aloadDom = new ALOAD(DOM_INDEX);
        _iloadLast = new ILOAD(LAST_INDEX);

        LocalVariableGen iterator =
            addLocalVariable("iterator",
                             Util.getJCRefType(Constants.NODE_ITERATOR_SIG),
                             null, null);
        ITERATOR_INDEX = iterator.getIndex();
        _aloadIterator = new ALOAD(ITERATOR_INDEX);
        _astoreIterator = new ASTORE(ITERATOR_INDEX);
        il.append(new ACONST_NULL());
        il.append(storeIterator());
    }

    public Instruction loadLastNode() {
        return _iloadLast;
    }

    public Instruction loadCurrentNode() {
        return _iloadCurrent;
    }

    public Instruction storeCurrentNode() {
        return _istoreCurrent;
    }

    public Instruction loadDOM() {
        return _aloadDom;
    }

    public int getHandlerIndex() {
        return INVALID_INDEX;           // not available
    }

    public int getIteratorIndex() {
        return INVALID_INDEX;
    }

    public Instruction storeIterator() {
        return _astoreIterator;
    }

    public Instruction loadIterator() {
        return _aloadIterator;
    }

    //??? may not be used anymore
    public int getLocalIndex(String name) {
        if (name.equals("current")) {
            return CURRENT_INDEX;
        }
        return super.getLocalIndex(name);
    }
}
