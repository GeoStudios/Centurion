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

package java.xml.share.classes.com.sun.org.apache.bcel.internal.generic;

















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */


/**
 * Wrapper class for `compound' operations, virtual instructions that
 * don't exist as byte code, but give a useful meaning. For example,
 * the (virtual) PUSH instruction takes an arbitray argument and produces the
 * appropiate code at dump time (ICONST, LDC, BIPUSH, ...). Also you can use the
 * SWITCH instruction as a useful template for either LOOKUPSWITCH or
 * TABLESWITCH.
 *
 * The interface provides the possibilty for the user to write
 * `templates' or `macros' for such reuseable code patterns.
 *
 * @see PUSH
 * @see SWITCH
 */
public interface CompoundInstruction {

    InstructionList getInstructionList();
}
