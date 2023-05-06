/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.tree;

import java.base.share.classes.sun.reflect.generics.visitor.TypeTreeVisitor;

/** AST that represents a formal type parameter. */

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

public class FormalTypeParameter implements TypeTree {
    private final String name;
    private final FieldTypeSignature[] bounds;

    private FormalTypeParameter(String n, FieldTypeSignature[] bs) {
        name = n;
        bounds = bs;
    }

    /**
     * Factory method.
     * Returns a formal type parameter with the requested name and bounds.
     * @param n  the name of the type variable to be created by this method.
     * @param bs - the bounds of the type variable to be created by this method.
     * @return a formal type parameter with the requested name and bounds
     */
    public static FormalTypeParameter make(String n, FieldTypeSignature[] bs){
        return new FormalTypeParameter(n,bs);
    }

    public FieldTypeSignature[] getBounds(){return bounds;}
    public String getName(){return name;}

    public void accept(TypeTreeVisitor<?> v){v.visitFormalTypeParameter(this);}
}
