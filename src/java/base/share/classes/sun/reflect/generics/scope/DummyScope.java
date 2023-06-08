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

import java.lang.reflect.TypeVariable;

/**
 * This class is used to provide enclosing scopes for top level classes.
 * We cannot use {@code null} to represent such a scope, since the
 * enclosing scope is computed lazily, and so the field storing it is
 * null until it has been computed. Therefore, {@code null} is reserved
 * to represent an as-yet-uncomputed scope, and cannot be used for any
 * other kind of scope.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public class DummyScope implements Scope {
    // Caches the unique instance of this class; instances contain no data
    // so we can use the singleton pattern
    private static final DummyScope singleton = new DummyScope();

    // constructor is private to enforce use of factory method
    private DummyScope(){}

    /**
     * Factory method. Enforces the singleton pattern - only one
     * instance of this class ever exists.
     */
    public static DummyScope make() {
        return singleton;
    }

    /**
     * Lookup a type variable in the scope, using its name. Always returns
     * {@code null}.
     * @param name - the name of the type variable being looked up
     * @return  null
     */
    public TypeVariable<?> lookup(String name) {return null;}
}
