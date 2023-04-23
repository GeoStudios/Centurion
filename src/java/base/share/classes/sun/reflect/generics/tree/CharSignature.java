/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.tree;

import java.base.share.classes.sun.reflect.generics.visitor.TypeTreeVisitor;

/** AST that represents the type char. */
public class CharSignature implements BaseType {
    private static final CharSignature singleton = new CharSignature();

    private CharSignature(){}

    public static CharSignature make() {return singleton;}

    public void accept(TypeTreeVisitor<?> v){
        v.visitCharSignature(this);
    }
}
