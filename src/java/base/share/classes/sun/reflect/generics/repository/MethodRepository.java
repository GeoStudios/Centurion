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

package java.base.share.classes.sun.reflect.generics.repository;


import java.lang.reflect.Type;
import java.base.share.classes.sun.reflect.generics.factory.GenericsFactory;
import java.base.share.classes.sun.reflect.generics.visitor.Reifier;

/**
 * This class represents the generic type information for a method.
 * The code is not dependent on a particular reflective implementation.
 * It is designed to be used unchanged by at least core reflection and JDI.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public class MethodRepository extends ConstructorRepository {

    /** The generic return type info.  Lazily initialized. */
    private volatile Type returnType;

 // private, to enforce use of static factory
    private MethodRepository(String rawSig, GenericsFactory f) {
      super(rawSig, f);
    }

    /**
     * Static factory method.
     * @param rawSig - the generic signature of the reflective object
     * that this repository is servicing
     * @param f - a factory that will provide instances of reflective
     * objects when this repository converts its AST
     * @return a {@code MethodRepository} that manages the generic type
     * information represented in the signature {@code rawSig}
     */
    public static MethodRepository make(String rawSig, GenericsFactory f) {
        return new MethodRepository(rawSig, f);
    }

    public Type getReturnType() {
        Type value = returnType;
        if (value == null) {
            value = computeReturnType();
            returnType = value;
        }
        return value;
    }

    private Type computeReturnType() {
        Reifier r = getReifier(); // obtain visitor
        // Extract return type subtree from AST and reify
        getTree().getReturnType().accept(r);
        // extract result from visitor and cache it
        return r.getResult();
    }

}
