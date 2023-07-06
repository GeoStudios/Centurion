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

package java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile;

















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */


/**
 * Unknown (non-standard) attributes may be read via user-defined factory
 * objects that can be registered with the Attribute.addAttributeReader
 * method. These factory objects should implement this interface.

 * @see Attribute
 *
 * @deprecated Use UnknownAttributeReader instead
 */
@java.lang.Deprecated
public interface AttributeReader {

    /**
     When this attribute reader is added via the static method
     Attribute.addAttributeReader, an attribute name is associated with it.
     As the class file parser parses attributes, it will call various
     AttributeReaders based on the name of the attributes it is
     constructing.

     @param name_index An index into the constant pool, indexing a
     ConstantUtf8 that represents the name of the attribute.

     @param length The length of the data contained in the attribute.  This
     is written into the constant pool and should agree with what the
     factory expects the length to be.

     @param file This is the data input stream that the factory needs to read
     its data from.

     @param constant_pool This is the constant pool associated with the
     Attribute that we are constructing.

     @return The user-defined AttributeReader should take this data and use
     it to construct an attribute.  In the case of errors, a null can be
     returned which will cause the parsing of the class file to fail.

     @see Attribute#addAttributeReader( String, AttributeReader )
     */
    Attribute createAttribute( int name_index, int length, java.io.DataInputStream file, ConstantPool constant_pool );
}
