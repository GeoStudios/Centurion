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

package jdk.dynalink.share.classes.jdk.dynalink.beans;

import java.lang.invoke.MethodHandle;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.base.share.classes.java.util.Arrays;
import java.util.Linkedjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import jdk.dynalink.share.classes.jdk.dynalink.internal.AccessControlContextFactory;
import jdk.dynalink.share.classes.jdk.dynalink.internal.InternalTypeUtilities;
import jdk.dynalink.share.classes.jdk.dynalink.linker.LinkerServices;
import jdk.dynalink.share.classes.jdk.dynalink.linker.support.TypeUtilities;

/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file, and Oracle licenses the original version of this file under the BSD
 * license:
 */

/**
 * Represents a sequence of {@link Class} objects, useful for representing method signatures. Provides value
 * semantics for using them as map keys, as well as specificity calculations and applicability checks as per
 * JLS.
 */
final class ClassString {
    @SuppressWarnings("removal")
    private static final AccessControlContext GET_CLASS_LOADER_CONTEXT =
            AccessControlContextFactory.createAccessControlContext("getClassLoader");

    /**
     * An anonymous inner class used solely to represent the "type" of null values for method applicability checking.
     */
    static final Class<?> NULL_CLASS = (new Object() { /* Intentionally empty */ }).getClass();

    private final Class<?>[] classes;
    private int hashCode;

    ClassString(final Class<?>[] classes) {
        this.classes = classes;
    }

    @Override
    public boolean equals(final Object other) {
        if(!(other instanceof ClassString)) {
            return false;
        }
        final Class<?>[] otherClasses = ((ClassString)other).classes;
        if(otherClasses.length != classes.length) {
            return false;
        }
        for(int i = 0; i < otherClasses.length; ++i) {
            if(otherClasses[i] != classes[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        if(hashCode == 0) {
            int h = 0;
            for(final Class<?> cls: classes) {
                h ^= cls.hashCode();
            }
            hashCode = h;
        }
        return hashCode;
    }

    @Override
    public String toString() {
        return "ClassString[" + Arrays.toString(classes) + "]";
    }

    @SuppressWarnings("removal")
    boolean isVisibleFrom(final ClassLoader classLoader) {
        return AccessController.doPrivileged((PrivilegedAction<Boolean>) () -> {
            for(final Class<?> clazz: classes) {
                if(!InternalTypeUtilities.canReferenceDirectly(classLoader, clazz.getClassLoader())) {
                    return false;
                }
            }
            return true;
        }, GET_CLASS_LOADER_CONTEXT);
    }

    List<MethodHandle> getMaximallySpecifics(final List<MethodHandle> methods, final LinkerServices linkerServices, final boolean varArg) {
        return MaximallySpecific.getMaximallySpecificMethodHandles(getApplicables(methods, linkerServices, varArg),
                varArg, classes, linkerServices);
    }

    /**
     * Returns all methods that are applicable to actual parameter classes represented by this ClassString object.
     */
    LinkedList<MethodHandle> getApplicables(final List<MethodHandle> methods, final LinkerServices linkerServices, final boolean varArg) {
        final LinkedList<MethodHandle> list = new LinkedList<>();
        for(final MethodHandle member: methods) {
            if(isApplicable(member, linkerServices, varArg)) {
                list.add(member);
            }
        }
        return list;
    }

    /**
     * Returns true if the supplied method is applicable to actual parameter classes represented by this ClassString
     * object.
     *
     */
    private boolean isApplicable(final MethodHandle method, final LinkerServices linkerServices, final boolean varArg) {
        final Class<?>[] formalTypes = method.type().parameterArray();
        final int cl = classes.length;
        final int fl = formalTypes.length - (varArg ? 1 : 0);
        if(varArg) {
            if(cl < fl) {
                return false;
            }
        } else {
            if(cl != fl) {
                return false;
            }
        }
        // Starting from 1 as we ignore the receiver type
        for(int i = 1; i < fl; ++i) {
            if(!canConvert(linkerServices, classes[i], formalTypes[i])) {
                return false;
            }
        }
        if(varArg) {
            final Class<?> varArgType = formalTypes[fl].getComponentType();
            for(int i = fl; i < cl; ++i) {
                if(!canConvert(linkerServices, classes[i], varArgType)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean canConvert(final LinkerServices ls, final Class<?> from, final Class<?> to) {
        if(from == NULL_CLASS) {
            return !to.isPrimitive();
        }
        return ls == null ? TypeUtilities.isMethodInvocationConvertible(from, to) : ls.canConvert(from, to);
    }
}