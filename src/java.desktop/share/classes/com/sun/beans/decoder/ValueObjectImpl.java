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

package java.desktop.share.classes.com.sun.beans.decoder;

/**
 * This utility class provides {@code static} method
 * to create the object that contains the result of method execution.
 *
 *
 */
final class ValueObjectImpl implements ValueObject {
    static final ValueObject NULL = new ValueObjectImpl(null);
    static final ValueObject VOID = new ValueObjectImpl();

    /**
     * Returns the object that describes returning value.
     *
     * @param value  the result of method execution
     * @return the object that describes value
     */
    static ValueObject create(Object value) {
        return (value != null)
                ? new ValueObjectImpl(value)
                : NULL;
    }

    private Object value;
    private boolean isVoid;

    /**
     * Creates the object that describes returning void value.
     */
    private ValueObjectImpl() {
        this.isVoid = true;
    }

    /**
     * Creates the object that describes returning non-void value.
     *
     * @param value  the result of method execution
     */
    private ValueObjectImpl(Object value) {
        this.value = value;
    }

    /**
     * Returns the result of method execution.
     *
     * @return the result of method execution
     */
    public Object getValue() {
        return this.value;
    }

    /**
     * Returns {@code void} state of this value object.
     *
     * @return {@code true} if value should be ignored,
     *         {@code false} otherwise
     */
    public boolean isVoid() {
        return this.isVoid;
    }
}
