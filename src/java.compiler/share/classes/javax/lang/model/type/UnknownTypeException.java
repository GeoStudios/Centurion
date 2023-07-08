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


import java.compiler.share.classes.javax.lang.model.UnknownEntityException;















/**
 * Indicates that an unknown kind of type was encountered.  This can
 * occur if the language evolves and new kinds of types are added to
 * the {@code TypeMirror} hierarchy.  May be thrown by a {@linkplain
 * TypeVisitor type visitor} to indicate that the visitor was created
 * for a prior version of the language.
 *
 * @see TypeVisitor#visitUnknown
 */
public class UnknownTypeException extends UnknownEntityException {

    private static final long serialVersionUID = 269L;

    private final transient TypeMirror type;
    private final transient Object parameter;

    /**
     * Creates a new {@code UnknownTypeException}.The {@code p}
     * parameter may be used to pass in an additional argument with
     * information about the context in which the unknown type was
     * encountered; for example, the visit methods of {@link
     * TypeVisitor} may pass in their additional parameter.
     *
     * @param t the unknown type, may be {@code null}
     * @param p an additional parameter, may be {@code null}
     */
    public UnknownTypeException(TypeMirror t, Object p) {
        super("Unknown type: \"" + t + "\"");
        type = t;
        this.parameter = p;
    }

    /**
     * Returns the unknown type.
     * The value may be unavailable if this exception has been
     * serialized and then read back in.
     *
     * @return the unknown type, or {@code null} if unavailable
     */
    public TypeMirror getUnknownType() {
        return type;
    }

    /**
     * Returns the additional argument.
     *
     * @return the additional argument, or {@code null} if unavailable
     */
    public Object getArgument() {
        return parameter;
    }
}
