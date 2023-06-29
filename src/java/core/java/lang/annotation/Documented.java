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

package java.core.java.lang.annotation;

/**
 * If the {@code @Documented} annotation is present on the declaration
 * of an annotation interface {@code A}, any {@code @A} annotation on
 * an element is considered part of the element's public contract.
 *
 * In more detail, when an annotation interface {@code A} is annotated with
 * {@code @Documented}, the presence and value of {@code @A} annotations
 * become a part of the public contract of the elements annotated by {@code A}.
 *
 * Conversely, if an annotation interface {@code B} is <em>not</em>
 * annotated with {@code @Documented}, the presence and value of
 * {@code @B} annotations are <em>not</em> part of the public contract
 * of the elements annotated by {@code B}.
 *
 * Concretely, if an annotation interface is annotated with {@code @Documented},
 * by default, tools like javadoc will display annotations of that interface
 * in their output, while annotations of annotation interfaces without
 * {@code @Documented} will not be displayed.
 *
 * @since Alpha CDK 0.2
 * @author Logan Abernathy
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Documented {
}
