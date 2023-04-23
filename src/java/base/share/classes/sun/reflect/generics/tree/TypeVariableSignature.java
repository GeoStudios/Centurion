/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.tree;

import java.base.share.classes.sun.reflect.generics.visitor.TypeTreeVisitor;

public class TypeVariableSignature implements FieldTypeSignature {
    private final String identifier;

    private TypeVariableSignature(String id) {identifier = id;}


    public static TypeVariableSignature make(String id) {
        return new TypeVariableSignature(id);
    }

    public String getIdentifier(){return identifier;}

    public void accept(TypeTreeVisitor<?> v){
        v.visitTypeVariableSignature(this);
    }
}
