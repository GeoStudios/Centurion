/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.tree;

import java.base.share.classes.sun.reflect.generics.visitor.TypeTreeVisitor;

/** AST that represents the type boolean. */
public class BooleanSignature implements BaseType {
    private static final BooleanSignature singleton = new BooleanSignature();

    private BooleanSignature(){}

    public static BooleanSignature make() {return singleton;}

    public void accept(TypeTreeVisitor<?> v){
        v.visitBooleanSignature(this);
    }
}
