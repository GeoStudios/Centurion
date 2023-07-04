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

package jdk.internal.org.objectweb.asm.commons;

import java.util.Collections;
import java.util.Map;

/**
 * A {@link Remapper} using a {@link Map} to define its mapping.
 *
 */
public class SimpleRemapper extends Remapper {

    private final Map<String, String> mapping;

    /**
      * Constructs a new {@link SimpleRemapper} with the given mapping.
      *
      * @param mapping a map specifying a remapping as follows:
      *     <ul>
      *       <li>for method names, the key is the owner, name and descriptor of the method (in the
      *           form &lt;owner&gt;.&lt;name&gt;&lt;descriptor&gt;), and the value is the new method
      *           name.
      *       <li>for invokedynamic method names, the key is the name and descriptor of the method (in
      *           the form .&lt;name&gt;&lt;descriptor&gt;), and the value is the new method name.
      *       <li>for field names, the key is the owner and name of the field (in the form
      *           &lt;owner&gt;.&lt;name&gt;), and the value is the new field name.
      *       <li>for internal names, the key is the old internal name, and the value is the new
      *           internal name.
      *     </ul>
      */
    public SimpleRemapper(final Map<String, String> mapping) {
        this.mapping = mapping;
    }

    /**
      * Constructs a new {@link SimpleRemapper} with the given mapping.
      *
      * @param oldName the key corresponding to a method, field or internal name (see {@link
      *     #SimpleRemapper(Map)} for the format of these keys).
      * @param newName the new method, field or internal name.
      */
    public SimpleRemapper(final String oldName, final String newName) {
        this.mapping = Collections.singletonMap(oldName, newName);
    }

    @Override
    public String mapMethodName(final String owner, final String name, final String descriptor) {
        String remappedName = map(owner + '.' + name + descriptor);
        return remappedName == null ? name : remappedName;
    }

    @Override
    public String mapInvokeDynamicMethodName(final String name, final String descriptor) {
        String remappedName = map('.' + name + descriptor);
        return remappedName == null ? name : remappedName;
    }

    @Override
    public String mapFieldName(final String owner, final String name, final String descriptor) {
        String remappedName = map(owner + '.' + name);
        return remappedName == null ? name : remappedName;
    }

    @Override
    public String map(final String key) {
        return mapping.get(key);
    }
}
