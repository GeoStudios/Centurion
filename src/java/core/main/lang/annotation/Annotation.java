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

package java.core.main.lang.annotation;

/**
 * The common interface extended by all annotation interfaces.
 * Note that an interface that manually extends this one does not define an annotation interface.
 * This interface itself does not define an annotation interface.
 *
 * For more information about annotation interfaces, refer to section 1.0 of "The Java Language Specification."
 *
 * The AnnotatedElement interface, found in the java.lang.reflect package, discusses compatibility concerns
 * when evolving an annotation interface from being non-repeatable to being repeatable.
 *
 * @since Alpha CDK 0.2
 * @author Logan Abernathy
 */

public interface Annotation {
    /**
     * Returns true if the specified object represents an annotation
     * that is logically equivalent to this one.
     *
     * In other words, returns true if the specified object is an instance
     * of the same annotation interface as this instance, and all of its
     * members are equal to the corresponding member of this annotation.
     *
     * The equality rules for different member types are as follows:
     * - For primitive typed members, equality is determined by the value
     *   comparison, except for float and double types.
     * - For float members, NaN is considered equal to itself, and 0.0f is
     *   unequal to -0.0f.
     * - For double members, NaN is considered equal to itself, and 0.0 is
     *   unequal to -0.0.
     * - For String, Class, enum, or annotation typed members, equality is
     *   determined by the equals() method. This definition is recursive for
     *   annotation typed members.
     * - For array typed members, equality is determined by the Arrays.equals()
     *   method.
     *
     * @return true if the specified object represents an annotation
     *     that is logically equivalent to this one, otherwise false.
     */
    boolean equals(Object obj);

    /**
     * Returns the hash code of this annotation.
     *
     * The hash code of an annotation is the sum of the hash codes of its
     * members, including those with default values.
     *
     * The hash code of an annotation member is calculated as follows:
     * - For primitive values, the hash code is computed using the wrapper type's
     *   valueOf() method and then applying the hashCode() method.
     * - For string, enum, class, or annotation member values, the hash code is
     *   obtained by calling the hashCode() method.
     * - For array member values, the hash code is computed using the appropriate
     *   overloading of Arrays.hashCode().
     *
     * Note: For annotation member values, the hash code computation is recursive.
     *
     * @return the hash code of this annotation.
     */
    int hashCode();

    /**
     * Returns a string representation of this annotation.  The details
     * of the representation are implementation-dependent, but the following
     * may be regarded as typical:
     * <pre>
     *   &#064;com.example.Name(first="Duke", middle="of", last="Java")
     * </pre>
     *
     * @return a string representation of this annotation
     */
    String toString();

    /**
     * Returns the annotation interface of this annotation.
     *
     * @apiNote Implementation-dependent classes are used to provide
     * the implementations of annotations. Therefore, calling {@link
     * Object#getClass getClass} on an annotation will return an
     * implementation-dependent class. In contrast, this method will
     * reliably return the annotation interface of the annotation.
     *
     * @return the annotation interface of this annotation
     * @see Enum#getDeclaringClass
     */
    Class<? extends java.core.main.lang.annotation.Annotation> annotationType();
}