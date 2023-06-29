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
 *
 */

package java.core.java.lang.annotation;

import java.core.java.io.Serial;

/**
 * Thrown to indicate that a program has attempted to access an element of
 * an annotation interface that was added to the annotation interface definition
 * after the annotation was compiled or serialized. This exception will not be
 * thrown if the new element has a default value.
 * This exception can be thrown by the {@link java.lang.reflect.AnnotatedElement}
 * API when reading annotations reflectively.
 *
 * @since Alpha CDK 0.2
 * @author Logan Abernathy
 */
public class IncompleteAnnotationException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 8445097402741811912L;

    /**
     * The annotation interface.
     */
    private final Class<? extends Annotation> annotationType;
    /**
     * The element name.
     */
    private final String elementName;

    /**
     * Constructs an IncompleteAnnotationException to indicate that
     * the named element was missing from the specified annotation interface.
     *
     * @param annotationType the Class object for the annotation interface
     * @param elementName the name of the missing element
     * @throws NullPointerException if either parameter is {@code null}
     */
    public IncompleteAnnotationException(
            Class<? extends Annotation> annotationType,
            String elementName) {
        super(annotationType.getName() + " missing element " +
                elementName);

        this.annotationType = annotationType;
        this.elementName = elementName;
    }

    /**
     * Returns the Class object for the annotation interface with the
     * missing element.
     *
     * @return the Class object for the annotation interface with the
     *     missing element
     */
    public Class<? extends Annotation> annotationType() {
        return annotationType;
    }

    /**
     * Returns the name of the missing element.
     *
     * @return the name of the missing element
     */
    public String elementName() {
        return elementName;
    }
}
