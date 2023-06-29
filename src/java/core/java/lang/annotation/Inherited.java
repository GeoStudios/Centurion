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
 * Indicates that an annotation interface is automatically inherited. If
 * an {@code @Inherited} meta-annotation is present on an annotation interface
 * declaration, and the user queries the annotation interface on a class
 * declaration without an annotation for this interface, the class's superclass
 * will automatically be queried for the annotation interface. This process
 * continues until an annotation for this interface is found, or the top of
 * the class hierarchy (Object) is reached. If no superclass has an annotation
 * for this interface, the query will indicate that the class in question does
 * not have such an annotation.
 *
 * <p>Note that this meta-annotation interface has no effect if the annotated
 * interface is used to annotate anything other than a class. Also, this
 * meta-annotation only causes annotations to be inherited from superclasses;
 * annotations on implemented interfaces have no effect.
 *
 * @since Alpha CDK 0.2
 * @author Logan Abernathy
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Inherited {
}