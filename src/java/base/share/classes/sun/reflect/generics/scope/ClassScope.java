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
import java.lang.reflect.Method;


/**
 * This class represents the scope containing the type variables of
 * a class.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public class ClassScope extends AbstractScope<Class<?>> implements Scope {

    // constructor is private to enforce use of factory method
    private ClassScope(Class<?> c){
        super(c);
    }

    /**
     * Overrides the abstract method in the superclass.
     * @return the enclosing scope
     */
    protected Scope computeEnclosingScope() {
        Class<?> receiver = getRecvr();

        Method m = receiver.getEnclosingMethod();
        if (m != null)
            // Receiver is a local or anonymous class enclosed in a
            // method.
            return MethodScope.make(m);

        Constructor<?> cnstr = receiver.getEnclosingConstructor();
        if (cnstr != null)
            // Receiver is a local or anonymous class enclosed in a
            // constructor.
            return ConstructorScope.make(cnstr);

        Class<?> c = receiver.getEnclosingClass();
        // if there is a declaring class, recvr is a member class
        // and its enclosing scope is that of the declaring class
        if (c != null)
            // Receiver is a local class, an anonymous class, or a
            // member class (static or not).
            return ClassScope.make(c);

        // otherwise, recvr is a top level class, and it has no real
        // enclosing scope.
        return DummyScope.make();
    }

    /**
     * Factory method. Takes a {@code Class} object and creates a
     * scope for it.
     * @param c - a Class whose scope we want to obtain
     * @return The type-variable scope for the class c
     */
    public static ClassScope make(Class<?> c) { return new ClassScope(c);}

}
