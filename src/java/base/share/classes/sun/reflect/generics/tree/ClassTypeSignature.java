/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.tree;

import java.util.List;
import java.base.share.classes.sun.reflect.generics.visitor.TypeTreeVisitor;

/**
 * AST representing class types.
 */
public class ClassTypeSignature implements FieldTypeSignature {
    private final List<SimpleClassTypeSignature> path;


    private ClassTypeSignature(List<SimpleClassTypeSignature> p) {
        path = p;
    }

    public static ClassTypeSignature make(List<SimpleClassTypeSignature> p) {
        return new ClassTypeSignature(p);
    }

    public List<SimpleClassTypeSignature> getPath(){return path;}

    public void accept(TypeTreeVisitor<?> v){v.visitClassTypeSignature(this);}
}
