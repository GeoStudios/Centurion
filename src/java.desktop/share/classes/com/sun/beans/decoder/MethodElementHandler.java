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

import java.desktop.share.classes.com.sun.beans.finder.MethodFinder;
import java.lang.reflect.Method;
import java.desktop.share.classes.com.sun.reflect.misc.MethodUtil;

/**
 * This class is intended to handle &lt;method&gt; element.
 * It describes invocation of the method.
 * The {@code name} attribute denotes
 * the name of the method to invoke.
 * If the {@code class} attribute is specified
 * this element invokes static method of specified class.
 * The inner elements specifies the arguments of the method.
 * For example:<pre>
 * &lt;method name="valueOf" class="java.lang.Long"&gt;
 *     &lt;string&gt;10&lt;/string&gt;
 * &lt;/method&gt;</pre>
 * is equivalent to {@code Long.valueOf("10")} in Java code.
 * <p>The following attributes are supported:
 * <dl>
 * <dt>name
 * <dd>the method name
 * <dt>class
 * <dd>the type of object for instantiation
 * <dt>id
 * <dd>the identifier of the variable that is intended to store the result
 * </dl>
 *
 *
 */
final class MethodElementHandler extends NewElementHandler {
    private String name;

    /**
     * Parses attributes of the element.
     * The following attributes are supported:
     * <dl>
     * <dt>name
     * <dd>the method name
     * <dt>class
     * <dd>the type of object for instantiation
     * <dt>id
     * <dd>the identifier of the variable that is intended to store the result
     * </dl>
     *
     * @param name   the attribute name
     * @param value  the attribute value
     */
    @Override
    public void addAttribute(String name, String value) {
        if (name.equals("name")) { // NON-NLS: the attribute name
            this.name = value;
        } else {
            super.addAttribute(name, value);
        }
    }

    /**
     * Returns the result of method execution.
     *
     * @param type  the base class
     * @param args  the array of arguments
     * @return the value of this element
     * @throws Exception if calculation is failed
     */
    @Override
    protected ValueObject getValueObject(Class<?> type, Object[] args) throws Exception {
        Object bean = getContextBean();
        Class<?>[] types = getArgumentTypes(args);
        Method method = (type != null)
                ? MethodFinder.findStaticMethod(type, this.name, types)
                : MethodFinder.findMethod(bean.getClass(), this.name, types);

        if (method.isVarArgs()) {
            args = getArguments(args, method.getParameterTypes());
        }
        Object value = MethodUtil.invoke(method, bean, args);
        return method.getReturnType().equals(void.class)
                ? ValueObjectImpl.VOID
                : ValueObjectImpl.create(value);
    }
}