/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.tree;

import java.base.share.classes.sun.reflect.generics.visitor.TypeTreeVisitor;

/**
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

public class ArrayTypeSignature implements FieldTypeSignature {
    private final TypeSignature componentType;

    private ArrayTypeSignature(TypeSignature ct) {componentType = ct;}

    public static ArrayTypeSignature make(TypeSignature ct) {
        return new ArrayTypeSignature(ct);
    }

    public TypeSignature getComponentType(){return componentType;}

    public void accept(TypeTreeVisitor<?> v){
        v.visitArrayTypeSignature(this);
    }
}
