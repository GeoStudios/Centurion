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
 * Denotes that an instruction may start the process of loading and resolving
 * the referenced class in the Virtual Machine.
 *
 */
public interface LoadClass {

    /**
     * Returns the ObjectType of the referenced class or interface
     * that may be loaded and resolved.
     * @return object type that may be loaded or null if a primitive is
     * referenced
     */
    ObjectType getLoadClassType( ConstantPoolGen cpg );


    /**
     * Returns the type associated with this instruction.
     * LoadClass instances are always typed, but this type
     * does not always refer to the type of the class or interface
     * that it possibly forces to load. For example, GETFIELD would
     * return the type of the field and not the type of the class
     * where the field is defined.
     * If no class is forced to be loaded, <B>null</B> is returned.
     * An example for this is an ANEWARRAY instruction that creates
     * an int[][].
     * @see #getLoadClassType(ConstantPoolGen)
     */
    Type getType( ConstantPoolGen cpg );
}
