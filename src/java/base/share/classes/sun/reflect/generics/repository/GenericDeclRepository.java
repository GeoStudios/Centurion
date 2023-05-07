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

import java.lang.reflect.TypeVariable;
import java.base.share.classes.sun.reflect.generics.factory.GenericsFactory;
import java.base.share.classes.sun.reflect.generics.tree.FormalTypeParameter;
import java.base.share.classes.sun.reflect.generics.tree.Signature;
import java.base.share.classes.sun.reflect.generics.visitor.Reifier;

/**
 * This class represents the generic type information for a generic
 * declaration.
 * The code is not dependent on a particular reflective implementation.
 * It is designed to be used unchanged by at least core reflection and JDI.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public abstract class GenericDeclRepository<S extends Signature>
    extends AbstractRepository<S> {

    public static final TypeVariable<?>[] EMPTY_TYPE_VARS = new TypeVariable<?>[0];

    /** The formal type parameters.  Lazily initialized. */
    private volatile TypeVariable<?>[] typeParameters;

    protected GenericDeclRepository(String rawSig, GenericsFactory f) {
        super(rawSig, f);
    }

    /*
     * When queried for a particular piece of type information, the
     * general pattern is to consult the corresponding cached value.
     * If the corresponding field is non-null, it is returned.
     * If not, it is created lazily. This is done by selecting the appropriate
     * part of the tree and transforming it into a reflective object
     * using a visitor, which is created by feeding it the factory
     * with which the repository was created.
     */

    /**
     * Returns the formal type parameters of this generic declaration.
     * @return the formal type parameters of this generic declaration
     */
    public TypeVariable<?>[] getTypeParameters() {
        TypeVariable<?>[] value = typeParameters;
        if (value == null) {
            value = computeTypeParameters();
            typeParameters = value;
        }
        return value.clone();
    }

    private TypeVariable<?>[] computeTypeParameters() {
        // first, extract type parameter subtree(s) from AST
        FormalTypeParameter[] ftps = getTree().getFormalTypeParameters();
        // create array to store reified subtree(s)
        int length = ftps.length;
        if (length == 0) {
            return EMPTY_TYPE_VARS;
        }
        TypeVariable<?>[] typeParameters = new TypeVariable<?>[length];
        // reify all subtrees
        for (int i = 0; i < length; i++) {
            Reifier r = getReifier(); // obtain visitor
            ftps[i].accept(r); // reify subtree
            // extract result from visitor and store it
            typeParameters[i] = (TypeVariable<?>) r.getResult();
        }
        return typeParameters;
    }
}
