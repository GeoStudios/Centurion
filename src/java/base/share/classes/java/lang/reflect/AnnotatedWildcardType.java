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
 * {@code AnnotatedWildcardType} represents the potentially annotated use of a
 * wildcard type argument, whose upper or lower bounds may themselves represent
 * annotated uses of types.
 *
 * @jls 4.5.1 Type Arguments of Parameterized Types
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */

public interface AnnotatedWildcardType extends AnnotatedType {

    /**
     * Returns the potentially annotated lower bounds of this wildcard type.
     * If no lower bound is explicitly declared, the lower bound is the
     * type of null. In this case, a zero length array is returned.
     *
     * @apiNote While to date a wildcard may have at most one lower
     * bound, callers of this method should be written to accommodate
     * multiple bounds.
     *
     * @return the potentially annotated lower bounds of this wildcard type or
     * an empty array if no lower bound is explicitly declared.
     * @see WildcardType#getLowerBounds()
     */
    AnnotatedType[] getAnnotatedLowerBounds();

    /**
     * Returns the potentially annotated upper bounds of this wildcard type.
     * If no upper bound is explicitly declared, the upper bound is
     * unannotated {@code Object}
     *
     * @apiNote While to date a wildcard may have at most one upper
     * bound, callers of this method should be written to accommodate
     * multiple bounds.
     *
     * @return the potentially annotated upper bounds of this wildcard type
     * @see WildcardType#getUpperBounds()
     */
    AnnotatedType[] getAnnotatedUpperBounds();

    /**
     * Returns the potentially annotated type that this type is a member of, if
     * this type represents a nested type. For example, if this type is
     * {@code @TA O<T>.I<S>}, return a representation of {@code @TA O<T>}.
     *
     * <p>Returns {@code null} for an {@code AnnotatedType} that is an instance
     *     of {@code AnnotatedWildcardType}.
     *
     * @return {@code null}
     *
     * @since 9
     */
    @Override
    AnnotatedType getAnnotatedOwnerType();
}
