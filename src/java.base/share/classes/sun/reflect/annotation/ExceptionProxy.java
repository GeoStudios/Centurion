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

package java.base.share.classes.sun.reflect.annotation;

/**
 * An instance of this class is stored in an AnnotationInvocationHandler's
 * "memberValues" map in lieu of a value for an annotation member that
 * cannot be returned due to some exceptional condition (typically some
 * form of illegal evolution of the annotation class).  The ExceptionProxy
 * instance describes the exception that the dynamic proxy should throw if
 * it is queried for this member.
 *
 */
public abstract class ExceptionProxy implements java.io.Serializable {
    @java.io.Serial
    private static final long serialVersionUID = 7241930048386631401L;
    protected abstract RuntimeException generateException();
}
