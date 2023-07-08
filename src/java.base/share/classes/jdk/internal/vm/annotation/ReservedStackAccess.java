/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
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
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface ReservedStackAccess { }
