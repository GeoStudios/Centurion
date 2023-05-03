/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.tree;

import java.base.share.classes.sun.reflect.generics.visitor.TypeTreeVisitor;

/**
 * @since Java 2
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

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
