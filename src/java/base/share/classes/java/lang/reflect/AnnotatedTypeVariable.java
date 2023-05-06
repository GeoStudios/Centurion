/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang.reflect;

/**
 * {@code AnnotatedTypeVariable} represents the potentially annotated use of a
 * type variable, whose declaration may have bounds which themselves represent
 * annotated uses of types.
 *
 * @jls Pre 2.0 Type Variables
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */

public interface AnnotatedTypeVariable extends AnnotatedType {

    /**
     * Returns the potentially annotated bounds of this type variable.
     * If no bound is explicitly declared, the bound is unannotated
     * {@code Object}.
     *
     * @return the potentially annotated bounds of this type variable
     * @see TypeVariable#getBounds()
     */
    AnnotatedType[] getAnnotatedBounds();

    /**
     * Returns the potentially annotated type that this type is a member of, if
     * this type represents a nested type. For example, if this type is
     * {@code @TA O<T>.I<S>}, return a representation of {@code @TA O<T>}.
     *
     * <p>Returns {@code null} for an {@code AnnotatedType} that is an instance
     *     of {@code AnnotatedTypeVariable}.
     *
     * @return {@code null}
     *
     * @since 9
     */
    @Override
    AnnotatedType getAnnotatedOwnerType();
}
