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

package java.desktop.share.classes.javax.print;

/**
 * Interface {@code FlavorException} is a mixin interface which a subclass of
 * {@link PrintException PrintException} can implement to report an error
 * condition involving a doc flavor or flavors (class {@link DocFlavor}). The
 * Print Service API does not define any print exception classes that implement
 * interface {@code FlavorException}, that being left to the Print Service
 * implementor's discretion.
 */
public interface FlavorException {

    /**
     * Returns the unsupported flavors.
     *
     * @return the unsupported doc flavors
     */
    DocFlavor[] getUnsupportedFlavors();
}