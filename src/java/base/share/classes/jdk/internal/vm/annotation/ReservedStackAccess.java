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

package java.base.share.classes.jdk.internal.vm.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>An annotation expressing that a method is especially sensitive
 * to stack overflows. This is a hint the JVM can use to grant access to
 * extra stack space when executing this code if such feature is supported
 * by the JVM. The JVM is free to ignore this annotation.
 *
 * A possible way for the JVM to improve the execution context for methods
 * with this annotation is to reserve part of the thread's execution stack
 * for them. Access to this section of the stack would be denied by default
 * but could be granted if the JVM detects a possible stack overflow and
 * the thread's call stack includes at least one annotated method. Even if
 * access to this reserved area has been granted, the JVM might decide to
 * throw a delayed StackOverflowError when the thread exits the annotated
 * method.
 *
 * @since 9
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface ReservedStackAccess { }
