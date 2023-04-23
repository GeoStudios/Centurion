/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.tree;

import java.base.share.classes.sun.reflect.generics.visitor.TypeTreeVisitor;

public class BottomSignature implements FieldTypeSignature {
    private static final BottomSignature singleton = new BottomSignature();

    private BottomSignature(){}

    public static BottomSignature make() {return singleton;}

    public void accept(TypeTreeVisitor<?> v){v.visitBottomSignature(this);}
}
