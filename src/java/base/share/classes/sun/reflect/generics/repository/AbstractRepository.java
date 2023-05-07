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

import java.base.share.classes.sun.reflect.generics.factory.GenericsFactory;
import java.base.share.classes.sun.reflect.generics.tree.Tree;
import java.base.share.classes.sun.reflect.generics.visitor.Reifier;


/**
 * Abstract superclass for representing the generic type information for
 * a reflective entity.
 * The code is not dependent on a particular reflective implementation.
 * It is designed to be used unchanged by at least core reflection and JDI.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public abstract class AbstractRepository<T extends Tree> {

    // A factory used to produce reflective objects. Provided when the
    //repository is created. Will vary across implementations.
    private final GenericsFactory factory;

    private final T tree; // the AST for the generic type info

    //accessors
    private GenericsFactory getFactory() { return factory;}

    /**
     * Accessor for {@code tree}.
     * @return the cached AST this repository holds
     */
    protected T getTree(){ return tree;}

    /**
     * Returns a {@code Reifier} used to convert parts of the
     * AST into reflective objects.
     * @return a {@code Reifier} used to convert parts of the
     * AST into reflective objects
     */
    protected Reifier getReifier(){return Reifier.make(getFactory());}

    /**
     * Constructor. Should only be used by subclasses. Concrete subclasses
     * should make their constructors private and provide public factory
     * methods.
     * @param rawSig - the generic signature of the reflective object
     * that this repository is servicing
     * @param f - a factory that will provide instances of reflective
     * objects when this repository converts its AST
     */
    protected AbstractRepository(String rawSig, GenericsFactory f) {
        tree = parse(rawSig);
        factory = f;
    }

    /**
     * Returns the AST for the generic type info of this entity.
     * @param s - a string representing the generic signature of this
     * entity
     * @return the AST for the generic type info of this entity.
     */
    protected abstract T parse(String s);
}
