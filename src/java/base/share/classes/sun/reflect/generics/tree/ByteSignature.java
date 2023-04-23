/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.tree;

import java.base.share.classes.sun.reflect.generics.visitor.TypeTreeVisitor;

/** AST that represents the type byte. */
public class ByteSignature implements BaseType {
    private static final ByteSignature singleton = new ByteSignature();

    private ByteSignature(){}

    public static ByteSignature make() {return singleton;}

    public void accept(TypeTreeVisitor<?> v){
        v.visitByteSignature(this);
    }
}
