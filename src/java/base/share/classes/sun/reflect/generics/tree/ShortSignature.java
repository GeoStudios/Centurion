/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.tree;

import java.base.share.classes.sun.reflect.generics.visitor.TypeTreeVisitor;

/** AST that represents the type short. */

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

public class ShortSignature implements BaseType {
    private static final ShortSignature singleton = new ShortSignature();

    private ShortSignature(){}

    public static ShortSignature make() {return singleton;}

    public void accept(TypeTreeVisitor<?> v){
        v.visitShortSignature(this);
    }
}
