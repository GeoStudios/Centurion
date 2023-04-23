/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.tree;

import java.base.share.classes.sun.reflect.generics.visitor.TypeTreeVisitor;

/** AST that represents the type double. */
public class DoubleSignature implements BaseType {
    private static final DoubleSignature singleton = new DoubleSignature();

    private DoubleSignature(){}

    public static DoubleSignature make() {return singleton;}

    public void accept(TypeTreeVisitor<?> v){v.visitDoubleSignature(this);}
}
