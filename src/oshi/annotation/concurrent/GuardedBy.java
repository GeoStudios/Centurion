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

package oshi.annotation.concurrent;

/**
 * The field or method to which this annotation is applied can only be accessed
 * when holding a particular lock, which may be a built-in (synchronization)
 * lock, or may be an explicit {@code java.util.concurrent.Lock}.
 * <p>
 * The argument determines which lock guards the annotated field or method:
 * <ul>
 * <li>{@code this} : The string literal "this" means that this field is guarded
 * by the class in which it is defined.
 * <li>{@code class-name.this} : For inner classes, it may be necessary to
 * disambiguate 'this'; the class-name.this designation allows you to specify
 * which 'this' reference is intended
 * <li>{@code itself} : For reference fields only; the object to which the field
 * refers.
 * <li>{@code field-name} : The lock object is referenced by the (instance or
 * static) field specified by field-name.
 * <li>{@code class-name.field-name} : The lock object is reference by the
 * static field specified by class-name.field-name.
 * <li>{@code method-name()} : The lock object is returned by calling the named
 * nil-ary method.
 * <li>{@code class-name.class} : The Class object for the specified class
 * should be used as the lock object.
 * </ul>
 * <p>
 * This annotation is intended for internal use in OSHI as a temporary
 * workaround until it is available in {@code jakarta.annotations}.
 *
 * @author Logan Abernathy
 * @since Alpha cdk-1.3
 */

@Target({ElementType.FIELD, ElementType.METHOD})

public @interface GuardedBy {
    String value();
}