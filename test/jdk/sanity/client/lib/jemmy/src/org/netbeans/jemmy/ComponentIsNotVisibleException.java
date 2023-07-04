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
package org.netbeans.jemmy;

import java.awt.Component;

/**
 *
 * Exception can be thrown as a result of attempt to produce a mouse operation
 * for a component which is not visible.
 *
 * @author Alexandre Iline (alexandre.iline@oracle.com)
 */
public class ComponentIsNotVisibleException extends JemmyInputException {

    private static final long serialVersionUID = 42L;

    /**
     * Constructs a ComponentIsNotVisibleException object.
     *
     * @param comp a Component.
     */
    public ComponentIsNotVisibleException(Component comp) {
        super("Component is not visible", comp);
    }

}
