/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.tree;

import java.base.share.classes.sun.reflect.generics.visitor.TypeTreeVisitor;

/** AST that represents the type float. */

/**
 * @since Java 2
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

public class FloatSignature implements BaseType {
    private static final FloatSignature singleton = new FloatSignature();

    private FloatSignature(){}

    public static FloatSignature make() {return singleton;}

    public void accept(TypeTreeVisitor<?> v){v.visitFloatSignature(this);}
}
