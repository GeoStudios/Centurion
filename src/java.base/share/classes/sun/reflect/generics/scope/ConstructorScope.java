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

package java.base.share.classes.sun.reflect.generics.scope;


import java.lang.reflect.Constructor;















/**
 * This class represents the scope containing the type variables of
 * a constructor.
 */
public class ConstructorScope extends AbstractScope<Constructor<?>> {

    // constructor is private to enforce use of factory method
    private ConstructorScope(Constructor<?> c){
        super(c);
    }

    // utility method; computes enclosing class, from which we can
    // derive enclosing scope.
    private Class<?> getEnclosingClass(){
        return getRecvr().getDeclaringClass();
    }

    /**
     * Overrides the abstract method in the superclass.
     * @return the enclosing scope
     */
    protected Scope computeEnclosingScope() {
        // the enclosing scope of a (generic) constructor is the scope of the
        // class in which it was declared.
        return ClassScope.make(getEnclosingClass());
    }

    /**
     * Factory method. Takes a {@code Constructor} object and creates a
     * scope for it.
     * @param c - A Constructor whose scope we want to obtain
     * @return The type-variable scope for the constructor m
     */
    public static ConstructorScope make(Constructor<?> c) {
        return new ConstructorScope(c);
    }
}
