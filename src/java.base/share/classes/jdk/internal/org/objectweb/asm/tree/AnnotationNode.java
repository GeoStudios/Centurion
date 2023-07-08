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

package java.base.share.classes.jdk.internal.org.objectweb.asm.tree;


import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import java.base.share.classes.jdk.internal.org.objectweb.asm.AnnotationVisitor;
import java.base.share.classes.jdk.internal.org.objectweb.asm.Opcodes;















/**
 * A node that represents an annotation.
 *
 */
public class AnnotationNode extends AnnotationVisitor {

    /** The class descriptor of the annotation class. */
    public String desc;

    /**
      * The name value pairs of this annotation. Each name value pair is stored as two consecutive
      * elements in the list. The name is a {@link String}, and the value may be a {@link Byte}, {@link
      * Boolean}, {@link Character}, {@link Short}, {@link Integer}, {@link Long}, {@link Float},
      * {@link Double}, {@link String} or {@link jdk.internal.org.objectweb.asm.Type}, or a two elements String
      * array (for enumeration values), an {@link AnnotationNode}, or a {@link List} of values of one
      * of the preceding types. The list may be {@literal null} if there is no name value pair.
      */
    public List<Object> values;

    /**
      * Constructs a new {@link AnnotationNode}. <i>Subclasses must not use this constructor</i>.
      * Instead, they must use the {@link #AnnotationNode(int, String)} version.
      *
      * @param descriptor the class descriptor of the annotation class.
      * @throws IllegalStateException If a subclass calls this constructor.
      */
    public AnnotationNode(final String descriptor) {
        this(/* latest api = */ Opcodes.ASM8, descriptor);
        if (getClass() != AnnotationNode.class) {
            throw new IllegalStateException();
        }
    }

    /**
      * Constructs a new {@link AnnotationNode}.
      *
      * @param api the ASM API version implemented by this visitor. Must be one of {@link
      *     Opcodes#ASM4}, {@link Opcodes#ASM5}, {@link Opcodes#ASM6}, {@link Opcodes#ASM7} or {@link
      *     Opcodes#ASM8}
      * @param descriptor the class descriptor of the annotation class.
      */
    public AnnotationNode(final int api, final String descriptor) {
        super(api);
        this.desc = descriptor;
    }

    /**
      * Constructs a new {@link AnnotationNode} to visit an array value.
      *
      * @param values where the visited values must be stored.
      */
    AnnotationNode(final List<Object> values) {
        super(/* latest api = */ Opcodes.ASM8);
        this.values = values;
    }

    // ------------------------------------------------------------------------
    // Implementation of the AnnotationVisitor abstract class
    // ------------------------------------------------------------------------

    @Override
    public void visit(final String name, final Object value) {
        if (values == null) {
            values = new ArrayList<>(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            values.add(name);
        }
        if (value instanceof byte[]) {
            values.add(Util.asArrayList((byte[]) value));
        } else if (value instanceof boolean[]) {
            values.add(Util.asArrayList((boolean[]) value));
        } else if (value instanceof short[]) {
            values.add(Util.asArrayList((short[]) value));
        } else if (value instanceof char[]) {
            values.add(Util.asArrayList((char[]) value));
        } else if (value instanceof int[]) {
            values.add(Util.asArrayList((int[]) value));
        } else if (value instanceof long[]) {
            values.add(Util.asArrayList((long[]) value));
        } else if (value instanceof float[]) {
            values.add(Util.asArrayList((float[]) value));
        } else if (value instanceof double[]) {
            values.add(Util.asArrayList((double[]) value));
        } else {
            values.add(value);
        }
    }

    @Override
    public void visitEnum(final String name, final String descriptor, final String value) {
        if (values == null) {
            values = new ArrayList<>(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            values.add(name);
        }
        values.add(new String[] {descriptor, value});
    }

    @Override
    public AnnotationVisitor visitAnnotation(final String name, final String descriptor) {
        if (values == null) {
            values = new ArrayList<>(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            values.add(name);
        }
        AnnotationNode annotation = new AnnotationNode(descriptor);
        values.add(annotation);
        return annotation;
    }

    @Override
    public AnnotationVisitor visitArray(final String name) {
        if (values == null) {
            values = new ArrayList<>(this.desc != null ? 2 : 1);
        }
        if (this.desc != null) {
            values.add(name);
        }
        List<Object> array = new ArrayList<>();
        values.add(array);
        return new AnnotationNode(array);
    }

    @Override
    public void visitEnd() {
        // Nothing to do.
    }

    // ------------------------------------------------------------------------
    // Accept methods
    // ------------------------------------------------------------------------

    /**
      * Checks that this annotation node is compatible with the given ASM API version. This method
      * checks that this node, and all its children recursively, do not contain elements that were
      * introduced in more recent versions of the ASM API than the given version.
      *
      * @param api an ASM API version. Must be one of {@link Opcodes#ASM4}, {@link Opcodes#ASM5},
      *     {@link Opcodes#ASM6}, {@link Opcodes#ASM7} or {@link Opcodes#ASM8}.
      */
    public void check(final int api) {
        // nothing to do
    }

    /**
      * Makes the given visitor visit this annotation.
      *
      * @param annotationVisitor an annotation visitor. Maybe {@literal null}.
      */
    public void accept(final AnnotationVisitor annotationVisitor) {
        if (annotationVisitor != null) {
            if (values != null) {
                for (int i = 0, n = values.size(); i < n; i += 2) {
                    String name = (String) values.get(i);
                    Object value = values.get(i + 1);
                    accept(annotationVisitor, name, value);
                }
            }
            annotationVisitor.visitEnd();
        }
    }

    /**
      * Makes the given visitor visit a given annotation value.
      *
      * @param annotationVisitor an annotation visitor. Maybe {@literal null}.
      * @param name the value name.
      * @param value the actual value.
      */
    static void accept(
            final AnnotationVisitor annotationVisitor, final String name, final Object value) {
        if (annotationVisitor != null) {
            if (value instanceof String[] typeValue) {
                annotationVisitor.visitEnum(name, typeValue[0], typeValue[1]);
            } else if (value instanceof AnnotationNode annotationValue) {
                annotationValue.accept(annotationVisitor.visitAnnotation(name, annotationValue.desc));
            } else if (value instanceof List) {
                AnnotationVisitor arrayAnnotationVisitor = annotationVisitor.visitArray(name);
                if (arrayAnnotationVisitor != null) {
                    List<?> arrayValue = (List<?>) value;
                    for (int i = 0, n = arrayValue.size(); i < n; ++i) {
                        accept(arrayAnnotationVisitor, null, arrayValue.get(i));
                    }
                    arrayAnnotationVisitor.visitEnd();
                }
            } else {
                annotationVisitor.visit(name, value);
            }
        }
    }
}
