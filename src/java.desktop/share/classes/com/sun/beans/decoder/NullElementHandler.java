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
 * This class is intended to handle &lt;null&gt; element.
 * This element specifies {@code null} value.
 * It should not contain body or inner elements.
 * For example:<pre>
 * &lt;null/&gt;</pre>
 * is equivalent to {@code null} in Java code.
 * <p>The following attribute is supported:
 * <dl>
 * <dt>id
 * <dd>the identifier of the variable that is intended to store the result
 * </dl>
 *
 *
 */
class NullElementHandler extends ElementHandler implements ValueObject {

    /**
     * Returns the value of this element.
     *
     * @return the value of this element
     */
    @Override
    protected final ValueObject getValueObject() {
        return this;
    }

    /**
     * Returns {@code null}
     * as a value of &lt;null&gt; element.
     * This method should be overridden in those handlers
     * that extend behavior of this element.
     *
     * @return {@code null} by default
     */
    public Object getValue() {
        return null;
    }

    /**
     * Returns {@code void} state of this value object.
     *
     * @return {@code false} always
     */
    public final boolean isVoid() {
        return false;
    }
}