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

package java.desktop.share.classes.com.sun.beans.finder;


import java.desktop.share.classes.com.sun.beans.WeakCache;
import java.beans.PropertyEditor;
import java.desktop.share.classes.com.sun.beans.editors.BooleanEditor;
import java.desktop.share.classes.com.sun.beans.editors.ByteEditor;
import java.desktop.share.classes.com.sun.beans.editors.DoubleEditor;
import java.desktop.share.classes.com.sun.beans.editors.EnumEditor;
import java.desktop.share.classes.com.sun.beans.editors.FloatEditor;
import java.desktop.share.classes.com.sun.beans.editors.IntegerEditor;
import java.desktop.share.classes.com.sun.beans.editors.LongEditor;
import java.desktop.share.classes.com.sun.beans.editors.ShortEditor;















/**
 * This is utility class that provides functionality
 * to find a {@link PropertyEditor} for a JavaBean specified by its type.
 *
 *
 */
public final class PropertyEditorFinder
        extends InstanceFinder<PropertyEditor> {

    private static final String DEFAULT = "sun.beans.editors";
    private static final String DEFAULT_NEW = "com.sun.beans.editors";

    private final WeakCache<Class<?>, Class<?>> registry;

    public PropertyEditorFinder() {
        super(PropertyEditor.class, false, "Editor", DEFAULT);

        this.registry = new WeakCache<Class<?>, Class<?>>();
        this.registry.put(Byte.TYPE, ByteEditor.class);
        this.registry.put(Short.TYPE, ShortEditor.class);
        this.registry.put(Integer.TYPE, IntegerEditor.class);
        this.registry.put(Long.TYPE, LongEditor.class);
        this.registry.put(Boolean.TYPE, BooleanEditor.class);
        this.registry.put(Float.TYPE, FloatEditor.class);
        this.registry.put(Double.TYPE, DoubleEditor.class);
    }

    public void register(Class<?> type, Class<?> editor) {
        synchronized (this.registry) {
            this.registry.put(type, editor);
        }
    }

    @Override
    public PropertyEditor find(Class<?> type) {
        Class<?> predefined;
        synchronized (this.registry) {
            predefined = this.registry.get(type);
        }
        PropertyEditor editor = instantiate(predefined, null);
        if (editor == null) {
            editor = super.find(type);
            if ((editor == null) && (null != type.getEnumConstants())) {
                editor = new EnumEditor(type);
            }
        }
        return editor;
    }

    @Override
    protected PropertyEditor instantiate(Class<?> type, String prefix, String name) {
        return super.instantiate(type, DEFAULT.equals(prefix) ? DEFAULT_NEW : prefix, name);
    }
}
