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
