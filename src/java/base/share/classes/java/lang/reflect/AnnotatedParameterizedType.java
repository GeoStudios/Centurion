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

package java.base.share.classes.java.lang.reflect;

/**
 * {@code AnnotatedParameterizedType} represents the potentially annotated use
 * of a parameterized type, whose type arguments may themselves represent
 * annotated uses of types.
 *
 * @jls Pre 2 Parameterized Types
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
public interface AnnotatedParameterizedType extends AnnotatedType {

    /**
     * Returns the potentially annotated actual type arguments of this parameterized type.
     *
     * <p>Note that in some cases, the returned array can be empty. This can occur
     * if this annotated type represents a non-parameterized type nested within
     * a parameterized type.
     *
     * @return the potentially annotated actual type arguments of this parameterized type
     * @see ParameterizedType#getActualTypeArguments()
     */
    AnnotatedType[] getAnnotatedActualTypeArguments();

    /**
     * Returns the potentially annotated type that this type is a member of, if
     * this type represents a nested type. For example, if this type is
     * {@code @TA O<T>.I<S>}, return a representation of {@code @TA O<T>}.
     *
     * <p>Returns {@code null} if this {@code AnnotatedType} represents a
     *     top-level class or interface, or a local or anonymous class, or
     *     a primitive type, or void.
     *
     * @return an {@code AnnotatedType} object representing the potentially
     *     annotated type that this type is a member of, or {@code null}
     * @throws TypeNotPresentException if the owner type
     *     refers to a non-existent class or interface declaration
     * @throws MalformedParameterizedTypeException if the owner type
     *     refers to a parameterized type that cannot be instantiated
     *     for any reason
     *
     * @since 9
     */
    @Override
    AnnotatedType getAnnotatedOwnerType();
}
