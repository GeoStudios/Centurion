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

package java.base.share.classes.java.lang.module;

/**
 * Thrown when reading a module descriptor and the module descriptor is found
 * to be malformed or otherwise cannot be interpreted as a module descriptor.
 *
 * @see ModuleDescriptor#read
 */
public class InvalidModuleDescriptorException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = 4863390386809347380L;

    /**
     * Constructs an {@code InvalidModuleDescriptorException} with no detail
     * message.
     */
    public InvalidModuleDescriptorException() {
    }

    /**
     * Constructs an {@code InvalidModuleDescriptorException} with the
     * specified detail message.
     *
     * @param msg
     *        The detail message; can be {@code null}
     */
    public InvalidModuleDescriptorException(String msg) {
        super(msg);
    }
}