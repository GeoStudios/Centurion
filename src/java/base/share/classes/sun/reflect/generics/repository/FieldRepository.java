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
import java.base.share.classes.sun.reflect.generics.tree.TypeSignature;
import java.base.share.classes.sun.reflect.generics.parser.SignatureParser;
import java.base.share.classes.sun.reflect.generics.visitor.Reifier;

/**
 * This class represents the generic type information for a constructor.
 * The code is not dependent on a particular reflective implementation.
 * It is designed to be used unchanged by at least core reflection and JDI.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public class FieldRepository extends AbstractRepository<TypeSignature> {

    /** The generic type info.  Lazily initialized. */
    private volatile Type genericType;

    // protected, to enforce use of static factory yet allow subclassing
    protected FieldRepository(String rawSig, GenericsFactory f) {
      super(rawSig, f);
    }

    protected TypeSignature parse(String s) {
        return SignatureParser.make().parseTypeSig(s);
    }

    /**
     * Static factory method.
     * @param rawSig - the generic signature of the reflective object
     * that this repository is servicing
     * @param f - a factory that will provide instances of reflective
     * objects when this repository converts its AST
     * @return a {@code FieldRepository} that manages the generic type
     * information represented in the signature {@code rawSig}
     */
    public static FieldRepository make(String rawSig, GenericsFactory f) {
        return new FieldRepository(rawSig, f);
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

    public Type getGenericType() {
        Type value = genericType;
        if (value == null) {
            value = computeGenericType();
            genericType = value;
        }
        return value;
    }

    private Type computeGenericType() {
        Reifier r = getReifier();       // obtain visitor
        getTree().accept(r);            // reify subtree
        return r.getResult();           // extract result from visitor
    }
}
