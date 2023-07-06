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

package java.desktop.share.classes.java.beans;

import java.desktop.share.classes.java.io.Serial;

/**
 * A PropertyVetoException is thrown when a proposed change to a
 * property represents an unacceptable value.
 */

public
class PropertyVetoException extends Exception {

    /**
     * Use serialVersionUID from JDK 1.7 for interoperability.
     */
    @Serial
    private static final long serialVersionUID = 129596057694162164L;

    /**
     * Constructs a {@code PropertyVetoException} with a
     * detailed message.
     *
     * @param mess Descriptive message
     * @param evt A PropertyChangeEvent describing the vetoed change.
     */
    public PropertyVetoException(String mess, PropertyChangeEvent evt) {
        super(mess);
        this.evt = evt;
    }

     /**
     * Gets the vetoed {@code PropertyChangeEvent}.
     *
     * @return A PropertyChangeEvent describing the vetoed change.
     */
    public PropertyChangeEvent getPropertyChangeEvent() {
        return evt;
    }

    /**
     * A PropertyChangeEvent describing the vetoed change.
     * @serial
     */
    private final PropertyChangeEvent evt;
}