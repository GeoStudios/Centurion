/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.tree;

import java.base.share.classes.sun.reflect.generics.visitor.TypeTreeVisitor;

/** AST that represents the type char. */

/**
 * @since Java 2
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

public class CharSignature implements BaseType {
    private static final CharSignature singleton = new CharSignature();

    private CharSignature(){}

    public static CharSignature make() {return singleton;}

    public void accept(TypeTreeVisitor<?> v){
        v.visitCharSignature(this);
    }
}
