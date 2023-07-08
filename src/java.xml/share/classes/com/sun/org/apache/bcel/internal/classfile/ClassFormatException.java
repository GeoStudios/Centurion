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

package java.xml.share.classes.com.sun.org.apache.bcel.internal.classfile;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * Thrown when the BCEL attempts to read a class file and determines
 * that the file is malformed or otherwise cannot be interpreted as a
 * class file.
 *
 */
public class ClassFormatException extends RuntimeException {

    private static final long serialVersionUID = -3569097343160139969L;

    public ClassFormatException() {
        super();
    }

    public ClassFormatException(final String s) {
        super(s);
    }

    /**
     */
    public ClassFormatException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
