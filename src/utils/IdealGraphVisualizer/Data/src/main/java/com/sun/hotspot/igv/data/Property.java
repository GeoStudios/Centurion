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

package utils.IdealGraphVisualizer.Data.src.main.java.com.sun.hotspot.igv.data;

import utils.IdealGraphVisualizer.Data.src.main.java.io.Serializable;

/**
 *
 */
public class Property implements Serializable {

    public static final long serialVersionUID = 1L;
    private final String name;
    private final String value;

    Property(String name, String value) {
        this.name = name;
        this.value = value;

        if (value == null) {
            throw new IllegalArgumentException("Property value must not be null!");
        }

        if (name == null) {
            throw new IllegalArgumentException("Property name must not be null!");
        }
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name + "=" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Property p2)) {
            return false;
        }
        return name.equals(p2.name) && value.equals(p2.value);
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 13 + value.hashCode();
    }
}
