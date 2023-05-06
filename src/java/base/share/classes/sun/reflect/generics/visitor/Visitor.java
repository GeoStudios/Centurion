/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.visitor;

import java.base.share.classes.sun.reflect.generics.tree.*;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

public interface Visitor<T> extends TypeTreeVisitor<T> {

    void visitClassSignature(ClassSignature cs);
    void visitMethodTypeSignature(MethodTypeSignature ms);
}
