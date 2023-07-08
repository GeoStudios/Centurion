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

import java.lang.invoke.MethodType;
import java.util.Linkedjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import jdk.dynalink.share.classes.jdk.dynalink.linker.support.TypeUtilities;

/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file, and Oracle licenses the original version of this file under the BSD
 * license:
 */

/**
 * Represents overloaded methods applicable to a specific call site signature.
 */
class ApplicableOverloadedMethods {
    private final List<SingleDynamicMethod> methods;
    private final boolean varArgs;

    /**
     * Creates a new ApplicableOverloadedMethods instance
     *
     * @param methods a list of all overloaded methods with the same name for a class.
     * @param callSiteType the type of the call site
     * @param test applicability test. One of {@link #APPLICABLE_BY_SUBTYPING},
     * {@link #APPLICABLE_BY_METHOD_INVOCATION_CONVERSION}, or {@link #APPLICABLE_BY_VARIABLE_ARITY}.
     */
    ApplicableOverloadedMethods(final List<SingleDynamicMethod> methods, final MethodType callSiteType,
            final ApplicabilityTest test) {
        this.methods = new LinkedList<>();
        for(final SingleDynamicMethod m: methods) {
            if(test.isApplicable(callSiteType, m)) {
                this.methods.add(m);
            }
        }
        varArgs = test == APPLICABLE_BY_VARIABLE_ARITY;
    }

    /**
     * Retrieves all the methods this object holds.
     *
     * @return list of all methods.
     */
    List<SingleDynamicMethod> getMethods() {
        return methods;
    }

    /**
     * Returns a list of all methods in this objects that are maximally specific.
     *
     * @return a list of maximally specific methods.
     */
    List<SingleDynamicMethod> findMaximallySpecificMethods() {
        return MaximallySpecific.getMaximallySpecificMethods(methods, varArgs);
    }

    abstract static class ApplicabilityTest {
        abstract boolean isApplicable(MethodType callSiteType, SingleDynamicMethod method);
    }

    /**
     * Implements the applicability-by-subtyping test from JLS 15.12.2.2.
     */
    static final ApplicabilityTest APPLICABLE_BY_SUBTYPING = new ApplicabilityTest() {
        @Override
        boolean isApplicable(final MethodType callSiteType, final SingleDynamicMethod method) {
            final MethodType methodType = method.getMethodType();
            final int methodArity = methodType.parameterCount();
            if(methodArity != callSiteType.parameterCount()) {
                return false;
            }
            // 0th arg is receiver; it doesn't matter for overload
            // resolution.
            for(int i = 1; i < methodArity; ++i) {
                if(!TypeUtilities.isSubtype(callSiteType.parameterType(i), methodType.parameterType(i))) {
                    return false;
                }
            }
            return true;
        }
    };

    /**
     * Implements the applicability-by-method-invocation-conversion test from JLS 15.12.2.3.
     */
    static final ApplicabilityTest APPLICABLE_BY_METHOD_INVOCATION_CONVERSION = new ApplicabilityTest() {
        @Override
        boolean isApplicable(final MethodType callSiteType, final SingleDynamicMethod method) {
            final MethodType methodType = method.getMethodType();
            final int methodArity = methodType.parameterCount();
            if(methodArity != callSiteType.parameterCount()) {
                return false;
            }
            // 0th arg is receiver; it doesn't matter for overload
            // resolution.
            for(int i = 1; i < methodArity; ++i) {
                if(!TypeUtilities.isMethodInvocationConvertible(callSiteType.parameterType(i),
                        methodType.parameterType(i))) {
                    return false;
                }
            }
            return true;
        }
    };

    /**
     * Implements the applicability-by-variable-arity test from JLS 15.12.2.4.
     */
    static final ApplicabilityTest APPLICABLE_BY_VARIABLE_ARITY = new ApplicabilityTest() {
        @Override
        boolean isApplicable(final MethodType callSiteType, final SingleDynamicMethod method) {
            if(!method.isVarArgs()) {
                return false;
            }
            final MethodType methodType = method.getMethodType();
            final int methodArity = methodType.parameterCount();
            final int fixArity = methodArity - 1;
            final int callSiteArity = callSiteType.parameterCount();
            if(fixArity > callSiteArity) {
                return false;
            }
            // 0th arg is receiver; it doesn't matter for overload
            // resolution.
            for(int i = 1; i < fixArity; ++i) {
                if(!TypeUtilities.isMethodInvocationConvertible(callSiteType.parameterType(i),
                        methodType.parameterType(i))) {
                    return false;
                }
            }
            final Class<?> varArgType = methodType.parameterType(fixArity).getComponentType();
            for(int i = fixArity; i < callSiteArity; ++i) {
                if(!TypeUtilities.isMethodInvocationConvertible(callSiteType.parameterType(i), varArgType)) {
                    return false;
                }
            }
            return true;
        }
    };
}
