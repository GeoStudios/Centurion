/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.visitor;

import java.base.share.classes.sun.reflect.generics.tree.*;

/**
 * Visit a TypeTree and produce a result of type T.
 * 
 * @since Java 2
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public interface TypeTreeVisitor<T> {

    /**
     * Returns the result of the visit.
     * @return the result of the visit
     */
    T getResult();

    // Visitor methods, per node type

    void visitFormalTypeParameter(FormalTypeParameter ftp);

    void visitClassTypeSignature(ClassTypeSignature ct);
    void visitArrayTypeSignature(ArrayTypeSignature a);
    void visitTypeVariableSignature(TypeVariableSignature tv);
    void visitWildcard(Wildcard w);

    void visitSimpleClassTypeSignature(SimpleClassTypeSignature sct);
    void visitBottomSignature(BottomSignature b);

    //  Primitives and Void
    void visitByteSignature(ByteSignature b);
    void visitBooleanSignature(BooleanSignature b);
    void visitShortSignature(ShortSignature s);
    void visitCharSignature(CharSignature c);
    void visitIntSignature(IntSignature i);
    void visitLongSignature(LongSignature l);
    void visitFloatSignature(FloatSignature f);
    void visitDoubleSignature(DoubleSignature d);

    void visitVoidDescriptor(VoidDescriptor v);
}
