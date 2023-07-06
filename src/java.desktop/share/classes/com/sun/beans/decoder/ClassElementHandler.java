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
 * This class is intended to handle &lt;class&gt; element.
 * This element specifies {@link Class} values.
 * The result value is created from text of the body of this element.
 * The body parsing is described in the class {@link StringElementHandler}.
 * For example:<pre>
 * &lt;class&gt;java.lang.Class&lt;/class&gt;</pre>
 * is shortcut to<pre>
 * &lt;method name="forName" class="java.lang.Class"&gt;
 *     &lt;string&gt;java.lang.Class&lt;/string&gt;
 * &lt;/method&gt;</pre>
 * which is equivalent to {@code Class.forName("java.lang.Class")} in Java code.
 * <p>The following attribute is supported:
 * <dl>
 * <dt>id
 * <dd>the identifier of the variable that is intended to store the result
 * </dl>
 *
 *
 */
final class ClassElementHandler extends StringElementHandler {

    /**
     * Creates class by the name from
     * the text of the body of this element.
     *
     * @param argument  the text of the body
     * @return evaluated {@code Class} value
     */
    @Override
    public Object getValue(String argument) {
        return getOwner().findClass(argument);
    }
}