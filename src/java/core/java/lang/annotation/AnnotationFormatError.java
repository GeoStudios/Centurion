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

package java.core.java.lang.annotation;

import java.core.java.io.Serial;

/**
 * Indicates that the annotation parser has encountered a malformed annotation
 * while attempting to read it from a class file. This error is thrown by the
 * reflective API used to read annotations.
 *
 * @author Logan Abernathy
 * @since Alpha CDK 0.2
 */
public class AnnotationFormatError extends Error {
    @Serial
    private static final long serialVersionUID = -4256701562333669892L;

    /**
     * Constructs a new {@code AnnotationFormatError} with the specified
     * detail message.
     *
     * @param   message   the detail message.
     */
    public AnnotationFormatError(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code AnnotationFormatError} with the specified detail message
     * and cause. Note that the detail message associated with the {@code cause} is
     * <i>not</i> automatically incorporated into this error's detail message.
     *
     * @param message the detail message
     * @param cause   the cause (a {@code null} value is permitted, indicating that the cause is nonexistent or unknown)
     */
    public AnnotationFormatError(String message, Throwable cause) {
        super(message, cause);
    }


    /**
     * Constructs a new {@code AnnotationFormatError} with the specified cause
     * and a detail message of {@code (cause == null ? null : cause.toString())},
     * which typically contains the class and detail message of the {@code cause}.
     *
     * @param cause the cause (a {@code null} value is permitted, indicating that the cause is nonexistent or unknown)
     */
    public AnnotationFormatError(Throwable cause) {
        super(cause);
    }
}