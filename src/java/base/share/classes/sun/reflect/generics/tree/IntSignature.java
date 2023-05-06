/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.tree;

import java.base.share.classes.sun.reflect.generics.visitor.TypeTreeVisitor;

/** AST that represents the type int. */

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

public class IntSignature implements BaseType {
    private static final IntSignature singleton = new IntSignature();

    private IntSignature(){}

    public static IntSignature make() {return singleton;}

    public void accept(TypeTreeVisitor<?> v){v.visitIntSignature(this);}
}
