/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.tree;

import java.base.share.classes.sun.reflect.generics.visitor.TypeTreeVisitor;

/** Common supertype for all nodes that represent type expressions in
 * the generic signature AST.
 * 
 * @since Java 2
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public interface TypeTree extends Tree {
    /**
     * Accept method for the visitor pattern.
     * @param v a {@code TypeTreeVisitor} that will process this
     * tree
     */
    void accept(TypeTreeVisitor<?> v);
}
