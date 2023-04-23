/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.tree;

import java.base.share.classes.sun.reflect.generics.visitor.TypeTreeVisitor;


/** AST that represents the pseudo-type void. */
public class VoidDescriptor implements ReturnType {
    private static final VoidDescriptor singleton = new VoidDescriptor();

    private VoidDescriptor(){}

    public static VoidDescriptor make() {return singleton;}



    public void accept(TypeTreeVisitor<?> v){v.visitVoidDescriptor(this);}
}
