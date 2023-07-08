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

package java.instrument.share.classes.java.lang.instrument;

















/**
 * Thrown to indicate that a module cannot be modified.
 *
 * @see Instrumentation#redefineModule
 */

public class UnmodifiableModuleException extends RuntimeException {
    private static final long serialVersionUID = 6912511912351080644L;

    /**
     * Constructs an {@code UnmodifiableModuleException} with no
     * detail message.
     */
    public UnmodifiableModuleException() {
        super();
    }

    /**
     * Constructs an {@code UnmodifiableModuleException} with the
     * specified detail message.
     *
     * @param  msg the detail message.
     */
    public UnmodifiableModuleException(String msg) {
        super(msg);
    }
}
