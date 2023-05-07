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

package java.base.share.classes.sun.reflect.generics.reflectiveObjects;

import java.lang.reflect.Type;
import java.base.share.classes.sun.reflect.generics.factory.GenericsFactory;
import java.base.share.classes.sun.reflect.generics.tree.FieldTypeSignature;
import java.base.share.classes.sun.reflect.generics.visitor.Reifier;

/**
 * Common infrastructure for things that lazily generate reflective generics
 * objects.
 * <p> In all these cases, one needs produce a visitor that will, on demand,
 * traverse the stored AST(s) and reify them into reflective objects.
 * The visitor needs to be initialized with a factory, which will be
 * provided when the instance is initialized.
 * The factory should be cached.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
*/
public abstract class LazyReflectiveObjectGenerator {
    private final GenericsFactory factory; // cached factory

    protected LazyReflectiveObjectGenerator(GenericsFactory f) {
        factory = f;
    }

    // accessor for factory
    private GenericsFactory getFactory() {
        return factory;
    }

    // produce a reifying visitor (could this be typed as a TypeTreeVisitor?
    protected Reifier getReifier(){return Reifier.make(getFactory());}

    Type[] reifyBounds(FieldTypeSignature[] boundASTs) {
        final int length = boundASTs.length;
        final Type[] bounds = new Type[length];
        // iterate over bound trees, reifying each in turn
        for (int i = 0; i < length; i++) {
            Reifier r = getReifier();
            boundASTs[i].accept(r);
            bounds[i] = r.getResult();
        }
        return bounds;
    }

}
