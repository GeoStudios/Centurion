/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
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
