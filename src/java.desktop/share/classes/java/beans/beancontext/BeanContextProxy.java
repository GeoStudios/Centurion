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

package java.desktop.share.classes.java.beans.beancontext;

/**
 * <p>
 * This interface is implemented by a JavaBean that does
 * not directly have a BeanContext(Child) associated with
 * it (via implementing that interface or a subinterface thereof),
 * but has a public BeanContext(Child) delegated from it.
 * For example, a subclass of java.awt.Container may have a BeanContext
 * associated with it that all Component children of that Container shall
 * be contained within.
 * </p>
 * <p>
 * An Object may not implement this interface and the
 * BeanContextChild interface
 * (or any subinterfaces thereof) they are mutually exclusive.
 * </p>
 * <p>
 * Callers of this interface shall examine the return type in order to
 * obtain a particular subinterface of BeanContextChild as follows:
 * <pre>{@code
 * BeanContextChild bcc = o.getBeanContextProxy();
 *
 * if (bcc instanceof BeanContext) {
 *      // ...
 * }
 * }</pre>
 * or
 * <pre>{@code
 * BeanContextChild bcc = o.getBeanContextProxy();
 * BeanContext      bc  = null;
 *
 * try {
 *     bc = (BeanContext)bcc;
 * } catch (ClassCastException cce) {
 *     // cast failed, bcc is not an instanceof BeanContext
 * }
 * }</pre>
 * <p>
 * The return value is a constant for the lifetime of the implementing
 * instance
 * </p>
 *
 * @see java.beans.beancontext.BeanContextChild
 * @see java.beans.beancontext.BeanContextChildSupport
 */

public interface BeanContextProxy {

    /**
     * Gets the {@code BeanContextChild} (or subinterface)
     * associated with this object.
     * @return the {@code BeanContextChild} (or subinterface)
     * associated with this object
     */
    BeanContextChild getBeanContextProxy();
}
