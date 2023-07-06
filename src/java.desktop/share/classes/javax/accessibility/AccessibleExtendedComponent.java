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

package java.desktop.share.classes.javax.accessibility;

/**
 * The {@code AccessibleExtendedComponent} interface should be supported by any
 * object that is rendered on the screen. This interface provides the standard
 * mechanism for an assistive technology to determine the extended graphical
 * representation of an object. Applications can determine if an object supports
 * the {@code AccessibleExtendedComponent} interface by first obtaining its
 * {@code AccessibleContext} and then calling the
 * {@link AccessibleContext#getAccessibleComponent} method. If the return value
 * is not {@code null} and the type of the return value is
 * {@code AccessibleExtendedComponent}, the object supports this interface.
 *
 * @see Accessible
 * @see Accessible#getAccessibleContext
 * @see AccessibleContext
 * @see AccessibleContext#getAccessibleComponent
 */
public interface AccessibleExtendedComponent extends AccessibleComponent {

    /**
     * Returns the tool tip text.
     *
     * @return the tool tip text, if supported, of the object; otherwise,
     *         {@code null}
     */
    String getToolTipText();

    /**
     * Returns the titled border text.
     *
     * @return the titled border text, if supported, of the object; otherwise,
     *         {@code null}
     */
    String getTitledBorderText();

    /**
     * Returns key bindings associated with this object.
     *
     * @return the key bindings, if supported, of the object; otherwise,
     *         {@code null}
     * @see AccessibleKeyBinding
     */
    AccessibleKeyBinding getAccessibleKeyBinding();
}