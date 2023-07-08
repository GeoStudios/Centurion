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

package jdk.jdeps.share.classes.com.sun.tools.javap;


import jdk.jdeps.share.classes.com.sun.tools.classfile.Attribute;
import jdk.jdeps.share.classes.com.sun.tools.classfile.Code_attribute;
import jdk.jdeps.share.classes.com.sun.tools.classfile.TypeAnnotation;
import jdk.jdeps.share.classes.com.sun.tools.classfile.TypeAnnotation.Position;
import jdk.jdeps.share.classes.com.sun.tools.classfile.Instruction;
import jdk.jdeps.share.classes.com.sun.tools.classfile.Method;
import jdk.jdeps.share.classes.com.sun.tools.classfile.RuntimeInvisibleTypeAnnotations_attribute;
import jdk.jdeps.share.classes.com.sun.tools.classfile.RuntimeTypeAnnotations_attribute;
import jdk.jdeps.share.classes.com.sun.tools.classfile.RuntimeVisibleTypeAnnotations_attribute;
import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.HashMap;
import java.util.java.util.java.util.java.util.List;
import java.util.Map;
import jdk.jdeps.share.classes.com.sun.tools.javac.util.StringUtils;















/**
 * Annotate instructions with details about type annotations.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 */
public class TypeAnnotationWriter extends InstructionDetailWriter {
    public enum NoteKind { VISIBLE, INVISIBLE }

    public static class Note {
        Note(NoteKind kind, TypeAnnotation anno) {
            this.kind = kind;
            this.anno = anno;
        }
        public final NoteKind kind;
        public final TypeAnnotation anno;
    }

    static TypeAnnotationWriter instance(Context context) {
        TypeAnnotationWriter instance = context.get(TypeAnnotationWriter.class);
        if (instance == null)
            instance = new TypeAnnotationWriter(context);
        return instance;
    }

    protected TypeAnnotationWriter(Context context) {
        super(context);
        context.put(TypeAnnotationWriter.class, this);
        annotationWriter = AnnotationWriter.instance(context);
        classWriter = ClassWriter.instance(context);
    }

    public void reset(Code_attribute attr) {
        Method m = classWriter.getMethod();
        pcMap = new HashMap<>();
        check(NoteKind.VISIBLE, (RuntimeVisibleTypeAnnotations_attribute) m.attributes.get(Attribute.RuntimeVisibleTypeAnnotations));
        check(NoteKind.INVISIBLE, (RuntimeInvisibleTypeAnnotations_attribute) m.attributes.get(Attribute.RuntimeInvisibleTypeAnnotations));
    }

    private void check(NoteKind kind, RuntimeTypeAnnotations_attribute attr) {
        if (attr == null)
            return;

        for (TypeAnnotation anno: attr.annotations) {
            Position p = anno.position;
            Note note = null;
            if (p.offset != -1)
                addNote(p.offset, note = new Note(kind, anno));
            if (p.lvarOffset != null) {
                for (int i = 0; i < p.lvarOffset.length; i++) {
                    if (note == null)
                        note = new Note(kind, anno);
                    addNote(p.lvarOffset[i], note);
                }
            }
        }
    }

    private void addNote(int pc, Note note) {
        List<Note> list = pcMap.get(pc);
        if (list == null)
            pcMap.put(pc, list = new ArrayList<>());
        list.add(note);
    }

    @Override
    void writeDetails(Instruction instr) {
        String indent = space(2); // get from Options?
        int pc = instr.getPC();
        List<Note> notes = pcMap.get(pc);
        if (notes != null) {
            for (Note n: notes) {
                print(indent);
                print("@");
                annotationWriter.write(n.anno, false, true);
                print(", ");
                println(StringUtils.toLowerCase(n.kind.toString()));
            }
        }
    }

    private final AnnotationWriter annotationWriter;
    private final ClassWriter classWriter;
    private Map<Integer, List<Note>> pcMap;
}
