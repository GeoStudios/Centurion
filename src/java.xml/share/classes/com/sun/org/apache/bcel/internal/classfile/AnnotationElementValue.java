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
package com.sun.org.apache.bcel.internal.classfile;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 */
public class AnnotationElementValue extends ElementValue
{
        // For annotation element values, this is the annotation
        private final AnnotationEntry annotationEntry;

        public AnnotationElementValue(final int type, final AnnotationEntry annotationEntry,
                        final ConstantPool cpool)
        {
                super(type, cpool);
                if (type != ANNOTATION) {
                    throw new IllegalArgumentException(
                                    "Only element values of type annotation can be built with this ctor - type specified: " + type);
                }
                this.annotationEntry = annotationEntry;
        }

        @Override
        public void dump(final DataOutputStream dos) throws IOException
        {
                dos.writeByte(super.getType()); // u1 type of value (ANNOTATION == '@')
                annotationEntry.dump(dos);
        }

        @Override
        public String stringifyValue()
        {
                return annotationEntry.toString();
        }

        @Override
        public String toString()
        {
                return stringifyValue();
        }

        public AnnotationEntry getAnnotationEntry()
        {
                return annotationEntry;
        }
}
