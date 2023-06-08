/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.sun.reflect.generics.scope;

import java.lang.reflect.Constructor;


/**
 * This class represents the scope containing the type variables of
 * a constructor.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
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
