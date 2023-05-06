/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang.reflect;

/**
 * {@code AnnotatedArrayType} represents the potentially annotated use of an
 * array type, whose component type may itself represent the annotated use of a
 * type.
 *
 * @jls Pre 2.0 Array Types
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */

public interface AnnotatedArrayType extends AnnotatedType {

    /**
     * Returns the potentially annotated generic component type of this array type.
     *
     * @return the potentially annotated generic component type of this array type
     * @see GenericArrayType#getGenericComponentType()
     */
    AnnotatedType  getAnnotatedGenericComponentType();

    /**
     * Returns the potentially annotated type that this type is a member of, if
     * this type represents a nested class or interface. For example, if this
     * type is {@code @TA O<T>.I<S>}, return a representation of {@code @TA O<T>}.
     *
     * <p>Returns {@code null} for an {@code AnnotatedType} that is an instance
     *     of {@code AnnotatedArrayType}.
     *
     * @return {@code null}
     *
     * @since 9
     */
    @Override
    AnnotatedType getAnnotatedOwnerType();
}
