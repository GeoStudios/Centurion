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

package jdk.test.lib.jfr;


import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import jdk.jfr.AnnotationElement;
import jdk.jfr.Name;
import jdk.jfr.ValueDescriptor;














public final class EventTypePrototype {
    private final List<ValueDescriptor> fields;
    private final List<AnnotationElement> annotations;
    private final String name;

    public EventTypePrototype(String name, List<AnnotationElement> as, List<ValueDescriptor> fields) {
        this.annotations = new ArrayList<>(as);
        this.annotations.add(new AnnotationElement(Name.class, name));
        this.fields = fields;
        this.name = name;
    }

    public EventTypePrototype(String name) {
        this(name, new ArrayList<>(), new ArrayList<>());
    }

    public int getFieldIndex(String key) {
        int index = 0;
        for (ValueDescriptor f : fields) {
            if (f.getName().equals(key)) {
                return index;
            }
            index++;
        }
        throw new NoSuchFieldError(key);
    }

    public void addField(ValueDescriptor fieldDescriptor) {
        fields.add(fieldDescriptor);
    }

    public void addAnnotation(AnnotationElement annotation) {
        annotations.add(annotation);
    }

    public List<ValueDescriptor> getFields() {
        return fields;
    }

    public List<AnnotationElement> getAnnotations() {
        return annotations;
    }

    public String getName() {
        return name;
    }
}
