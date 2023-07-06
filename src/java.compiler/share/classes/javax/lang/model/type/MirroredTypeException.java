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

package java.compiler.share.classes.javax.lang.model.type;

import java.io.ObjectInputStream;
import java.io.java.io.java.io.java.io.IOException;
import java.compiler.share.classes.javax.lang.model.element.Element;

/**
 * Thrown when an application attempts to access the {@link Class} object
 * corresponding to a {@link TypeMirror}.
 *
 * @see MirroredTypesException
 * @see Element#getAnnotation(Class)
 */
public class MirroredTypeException extends MirroredTypesException {

    private static final long serialVersionUID = 269;

    private transient TypeMirror type;          // cannot be serialized

    /**
     * Constructs a new MirroredTypeException for the specified type.
     *
     * @param type  the type being accessed
     */
    public MirroredTypeException(TypeMirror type) {
        super("Attempt to access Class object for TypeMirror " + type.toString(), type);
        this.type = type;
    }

    /**
     * Returns the type mirror corresponding to the type being accessed.
     * The type mirror may be unavailable if this exception has been
     * serialized and then read back in.
     *
     * @return the type mirror, or {@code null} if unavailable
     */
    public TypeMirror getTypeMirror() {
        return type;
    }

    /**
     * Explicitly set all transient fields.
     * @param s the serial stream
     * @throws ClassNotFoundException for a missing class during
     * deserialization
     * @throws IOException for an IO problem during deserialization
     */
    private void readObject(ObjectInputStream s)
        throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        type = null;
        types = null;
    }
}