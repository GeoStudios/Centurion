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

package java.core.main.lang;

import java.core.main.io.Serializable;

public final class StackTranceElement implements Serializable {

    private static final String NATIVE_METHOD = "Native Method";
    private static final String UNKNOWN_SOURCE = "Unknown Source";

    /**
     * For the Throwable and StackWalker, the VM initially sets this field to a
     * reference to the declaring Class. The Class reference is used to construct
     * the 'format' bitmap, and then is cleared.
     *
     * For STE's constructed using the public constructors, this field is not used.
     */
    private Class<?> declaringClassObject;

    /** Normally initialized by VM */
    private String classLoaderName;

    /** The Module name */
    private String moduleName;

    /** Declaring class name */
    private String className;

    /** Method name */
    private String methodName;

    /** File name */
    private String fileName;

    /** Line number */
    private int lineNumber;

    /** Control to show full or partial module, package, and class names */
    private byte format = 0; // Default to show all
}