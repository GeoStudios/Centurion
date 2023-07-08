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


import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.java.io.java.io.java.io.IOException;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.Collections;
import java.util.java.util.java.util.java.util.List;















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */



/**
 * represents one parameter annotation in the parameter annotation table
 *
 */
public class ParameterAnnotationEntry implements Node {

    private final AnnotationEntry[] annotationTable;


    /**
     * Construct object from input stream.
     *
     * @param input Input stream
     * @throws IOException
     */
    ParameterAnnotationEntry(final DataInput input, final ConstantPool constant_pool) throws IOException {
        final int annotation_table_length = input.readUnsignedShort();
        annotationTable = new AnnotationEntry[annotation_table_length];
        for (int i = 0; i < annotation_table_length; i++) {
            // TODO isRuntimeVisible
            annotationTable[i] = AnnotationEntry.read(input, constant_pool, false);
        }
    }


    /**
     * Called by objects that are traversing the nodes of the tree implicitely
     * defined by the contents of a Java class. I.e., the hierarchy of methods,
     * fields, attributes, etc. spawns a tree of objects.
     *
     * @param v Visitor object
     */
    @Override
    public void accept( final Visitor v ) {
        v.visitParameterAnnotationEntry(this);
    }

    /**
     * returns the array of annotation entries in this annotation
     */
    public AnnotationEntry[] getAnnotationEntries() {
        return annotationTable;
    }

    public void dump(final DataOutputStream dos) throws IOException {
        dos.writeShort(annotationTable.length);
        for (final AnnotationEntry entry : annotationTable) {
            entry.dump(dos);
        }
    }

  public static ParameterAnnotationEntry[] createParameterAnnotationEntries(final Attribute[] attrs) {
      // Find attributes that contain parameter annotation data
      final List<ParameterAnnotationEntry> accumulatedAnnotations = new ArrayList<>(attrs.length);
      for (final Attribute attribute : attrs) {
          if (attribute instanceof ParameterAnnotations runtimeAnnotations) {
              Collections.addAll(accumulatedAnnotations, runtimeAnnotations.getParameterAnnotationEntries());
          }
      }
      return accumulatedAnnotations.toArray(new ParameterAnnotationEntry[accumulatedAnnotations.size()]);
  }
}
