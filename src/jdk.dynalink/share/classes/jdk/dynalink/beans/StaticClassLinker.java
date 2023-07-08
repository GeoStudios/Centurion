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
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Array;
import java.base.share.classes.java.util.Arrays;
import java.util.Set;
import jdk.dynalink.share.classes.jdk.dynalink.CallSiteDescriptor;
import jdk.dynalink.share.classes.jdk.dynalink.NamedOperation;
import jdk.dynalink.share.classes.jdk.dynalink.StandardNamespace;
import jdk.dynalink.share.classes.jdk.dynalink.StandardOperation;
import jdk.dynalink.share.classes.jdk.dynalink.beans.GuardedInvocationComponent.ValidationType;
import jdk.dynalink.share.classes.jdk.dynalink.linker.GuardedInvocation;
import jdk.dynalink.share.classes.jdk.dynalink.linker.LinkRequest;
import jdk.dynalink.share.classes.jdk.dynalink.linker.LinkerServices;
import jdk.dynalink.share.classes.jdk.dynalink.linker.TypeBasedGuardingDynamicLinker;
import jdk.dynalink.share.classes.jdk.dynalink.linker.support.Lookup;















/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file, and Oracle licenses the original version of this file under the BSD
 * license:
 */



/**
 * Provides a linker for the {@link StaticClass} objects.
 */
class StaticClassLinker implements TypeBasedGuardingDynamicLinker {
    private static final ClassValue<SingleClassStaticsLinker> linkers = new ClassValue<>() {
        @Override
        protected SingleClassStaticsLinker computeValue(final Class<?> clazz) {
            return new SingleClassStaticsLinker(clazz);
        }
    };

    private static class SingleClassStaticsLinker extends AbstractJavaLinker {
        private final DynamicMethod constructor;

        SingleClassStaticsLinker(final Class<?> clazz) {
            super(clazz, IS_CLASS.bindTo(clazz));
            // Map "staticClassObject.class" to StaticClass.getRepresentedClass(). Some adventurous soul could subclass
            // StaticClass, so we use INSTANCE_OF validation instead of EXACT_CLASS.
            setPropertyGetter("class", GET_CLASS, ValidationType.INSTANCE_OF);
            constructor = createConstructorMethod(clazz);
        }

        /**
         * Creates a dynamic method containing all overloads of a class' public constructor
         * @param clazz the target class
         * @return a dynamic method containing all overloads of a class' public constructor. If the class has no public
         * constructors, returns null.
         */
        private static DynamicMethod createConstructorMethod(final Class<?> clazz) {
            if(clazz.isArray()) {
                final MethodHandle boundArrayCtor = ARRAY_CTOR.bindTo(clazz.getComponentType());
                return new SimpleDynamicMethod(StaticClassIntrospector.editConstructorMethodHandle(
                        boundArrayCtor.asType(boundArrayCtor.type().changeReturnType(clazz))), clazz, "<init>");
            }
            if(CheckRestrictedPackage.isRestrictedClass(clazz)) {
                return null;
            }
            return createDynamicMethod(Arrays.asList(clazz.getConstructors()), clazz, "<init>");
        }

        @Override
        FacetIntrospector createFacetIntrospector() {
            return new StaticClassIntrospector(clazz);
        }

        @Override
        public GuardedInvocation getGuardedInvocation(final LinkRequest request, final LinkerServices linkerServices)
                throws Exception {
            final GuardedInvocation gi = super.getGuardedInvocation(request, linkerServices);
            if(gi != null) {
                return gi;
            }
            final CallSiteDescriptor desc = request.getCallSiteDescriptor();
            if(NamedOperation.getBaseOperation(desc.getOperation()) == StandardOperation.NEW && constructor != null) {
                final MethodHandle ctorInvocation = constructor.getInvocation(desc, linkerServices);
                if(ctorInvocation != null) {
                    return new GuardedInvocation(ctorInvocation, getClassGuard(desc.getMethodType()));
                }
            }
            return null;
        }

        @Override
        protected GuardedInvocationComponent getGuardedInvocationComponent(final ComponentLinkRequest req) throws Exception {
            final GuardedInvocationComponent superGic = super.getGuardedInvocationComponent(req);
            if (superGic != null) {
                return superGic;
            }
            if (!req.namespaces.isEmpty()
                && req.namespaces.get(0) == StandardNamespace.ELEMENT
                && (req.baseOperation == StandardOperation.GET || req.baseOperation == StandardOperation.SET))
            {
                // StaticClass doesn't behave as a collection
                return getNextComponent(req.popNamespace());
            }
            return null;
        }

        @Override
        SingleDynamicMethod getConstructorMethod(final String signature) {
            return constructor != null? constructor.getMethodForExactParamTypes(signature) : null;
        }
    }

    static Object getConstructorMethod(final Class<?> clazz, final String signature) {
        return linkers.get(clazz).getConstructorMethod(signature);
    }

    static Set<String> getReadableStaticPropertyNames(final Class<?> clazz) {
        return linkers.get(clazz).getReadablePropertyNames();
    }

    static Set<String> getWritableStaticPropertyNames(final Class<?> clazz) {
        return linkers.get(clazz).getWritablePropertyNames();
    }

    static Set<String> getStaticMethodNames(final Class<?> clazz) {
        return linkers.get(clazz).getMethodNames();
    }

    @Override
    public GuardedInvocation getGuardedInvocation(final LinkRequest request, final LinkerServices linkerServices) throws Exception {
        final Object receiver = request.getReceiver();
        if(receiver instanceof StaticClass) {
            return linkers.get(((StaticClass)receiver).getRepresentedClass()).getGuardedInvocation(request,
                    linkerServices);
        }
        return null;
    }

    @Override
    public boolean canLinkType(final Class<?> type) {
        return type == StaticClass.class;
    }

    /*private*/ static final MethodHandle GET_CLASS;
    /*private*/ static final MethodHandle IS_CLASS;
    /*private*/ static final MethodHandle ARRAY_CTOR = Lookup.PUBLIC.findStatic(Array.class, "newInstance",
            MethodType.methodType(Object.class, Class.class, int.class));

    static {
        final Lookup lookup = new Lookup(MethodHandles.lookup());
        GET_CLASS = lookup.findVirtual(StaticClass.class, "getRepresentedClass", MethodType.methodType(Class.class));
        IS_CLASS = lookup.findOwnStatic("isClass", Boolean.TYPE, Class.class, Object.class);
    }

    @SuppressWarnings("unused")
    private static boolean isClass(final Class<?> clazz, final Object obj) {
        return obj instanceof StaticClass && ((StaticClass)obj).getRepresentedClass() == clazz;
    }
}
