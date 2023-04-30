/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

import java.base.share.classes.java.lang.annotation.*;

/**
 * Indicates that a method declaration is intended to override a
 * method declaration in a supertype. If a method is annotated with
 * this annotation type compilers are required to generate an error
 * message unless at least one of the following conditions hold:
 *
 * <ul><li>
 * The method does override or implement a method declared in a
 * supertype.
 * </li><li>
 * The method has a signature that is override-equivalent to that of
 * any public method declared in {@linkplain Object}.
 * </li></ul>
 *
 * @jls 8.4.8 Inheritance, Overriding, and Hiding
 * @jls 9.4.1 Inheritance and Overriding
 * @jls 9.6.4.4 @Override
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {
}
