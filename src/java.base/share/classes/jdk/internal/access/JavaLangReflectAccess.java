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

package java.base.share.classes.jdk.internal.access;

import java.lang.reflect.*;
import java.base.share.classes.jdk.internal.reflect.*;

/** An interface which gives privileged packages Java-level access to
    internals of java.lang.reflect. */

public interface JavaLangReflectAccess {
    /** Creates a new java.lang.reflect.Constructor. Access checks as
      per java.lang.reflect.AccessibleObject are not overridden. */
    <T> Constructor<T> newConstructor(Class<T> declaringClass,
                                             Class<?>[] parameterTypes,
                                             Class<?>[] checkedExceptions,
                                             int modifiers,
                                             int slot,
                                             String signature,
                                             byte[] annotations,
                                             byte[] parameterAnnotations);

    /** Gets the MethodAccessor object for a java.lang.reflect.Method */
    MethodAccessor getMethodAccessor(Method m);

    /** Sets the MethodAccessor object for a java.lang.reflect.Method */
    void setMethodAccessor(Method m, MethodAccessor accessor);

    /** Gets the ConstructorAccessor object for a
        java.lang.reflect.Constructor */
    ConstructorAccessor getConstructorAccessor(Constructor<?> c);

    /** Sets the ConstructorAccessor object for a
        java.lang.reflect.Constructor */
    void setConstructorAccessor(Constructor<?> c,
                                       ConstructorAccessor accessor);

    /** Gets the byte[] that encodes TypeAnnotations on an Executable. */
    byte[] getExecutableTypeAnnotationBytes(Executable ex);

    /** Gets the "slot" field from a Constructor (used for serialization) */
    int getConstructorSlot(Constructor<?> c);

    /** Gets the "signature" field from a Constructor (used for serialization) */
    String getConstructorSignature(Constructor<?> c);

    /** Gets the "annotations" field from a Constructor (used for serialization) */
    byte[] getConstructorAnnotations(Constructor<?> c);

    /** Gets the "parameterAnnotations" field from a Constructor (used for serialization) */
    byte[] getConstructorParameterAnnotations(Constructor<?> c);

    /** Gets the shared array of parameter types of an Executable. */
    Class<?>[] getExecutableSharedParameterTypes(Executable ex);

    //
    // Copying routines, needed to quickly fabricate new Field,
    // Method, and Constructor objects from templates
    //

    /** Makes a "child" copy of a Method */
    Method      copyMethod(Method arg);

    /** Makes a copy of this non-root a Method */
    Method      leafCopyMethod(Method arg);

    /** Makes a "child" copy of a Field */
    Field       copyField(Field arg);

    /** Makes a "child" copy of a Constructor */
    <T> Constructor<T> copyConstructor(Constructor<T> arg);

    /** Gets the root of the given AccessibleObject object; null if arg is the root */
    <T extends AccessibleObject> T getRoot(T obj);

    /** Tests if this is a trusted final field */
    boolean isTrustedFinalField(Field f);

    /** Returns a new instance created by the given constructor with access check */
    <T> T newInstance(Constructor<T> ctor, Object[] args, Class<?> caller)
        throws IllegalAccessException, InstantiationException, InvocationTargetException;
}
