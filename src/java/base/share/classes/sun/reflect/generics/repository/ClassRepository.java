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
import java.base.share.classes.sun.reflect.generics.tree.ClassSignature;
import java.base.share.classes.sun.reflect.generics.tree.TypeTree;
import java.base.share.classes.sun.reflect.generics.visitor.Reifier;
import java.base.share.classes.sun.reflect.generics.parser.SignatureParser;


/**
 * This class represents the generic type information for a class.
 * The code is not dependent on a particular reflective implementation.
 * It is designed to be used unchanged by at least core reflection and JDI.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public class ClassRepository extends GenericDeclRepository<ClassSignature> {

    public static final ClassRepository NONE = ClassRepository.make("Ljava/lang/Object;", null);

    /** The generic superclass info.  Lazily initialized. */
    private volatile Type superclass;

    /** The generic superinterface info.  Lazily initialized. */
    private volatile Type[] superInterfaces;

    // private, to enforce use of static factory
    private ClassRepository(String rawSig, GenericsFactory f) {
        super(rawSig, f);
    }

    protected ClassSignature parse(String s) {
        return SignatureParser.make().parseClassSig(s);
    }

    /**
     * Static factory method.
     * @param rawSig - the generic signature of the reflective object
     * that this repository is servicing
     * @param f - a factory that will provide instances of reflective
     * objects when this repository converts its AST
     * @return a {@code ClassRepository} that manages the generic type
     * information represented in the signature {@code rawSig}
     */
    public static ClassRepository make(String rawSig, GenericsFactory f) {
        return new ClassRepository(rawSig, f);
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

    public Type getSuperclass() {
        Type value = superclass;
        if (value == null) {
            value = computeSuperclass();
            superclass = value;
        }
        return value;
    }

    public Type[] getSuperInterfaces() {
        Type[] value = superInterfaces;
        if (value == null) {
            value = computeSuperInterfaces();
            superInterfaces = value;
        }
        return value.clone();
    }

    private Type computeSuperclass() {
        Reifier r = getReifier(); // obtain visitor
        // Extract superclass subtree from AST and reify
        getTree().getSuperclass().accept(r);
        return r.getResult();
    }

    private Type[] computeSuperInterfaces() {
        // first, extract super interface subtree(s) from AST
        TypeTree[] ts = getTree().getSuperInterfaces();
        // create array to store reified subtree(s)
        int length = ts.length;
        Type[] superInterfaces = new Type[length];
        // reify all subtrees
        for (int i = 0; i < length; i++) {
            Reifier r = getReifier(); // obtain visitor
            ts[i].accept(r);// reify subtree
            // extract result from visitor and store it
            superInterfaces[i] = r.getResult();
        }
        return superInterfaces;
    }
}
