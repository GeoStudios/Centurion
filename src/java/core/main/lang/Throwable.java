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

package java.core.main.lang;

import java.core.main.io.Serial;
import java.core.main.io.Serializable;

/**
 * The {@code Throwable} class is the superclass for all errors and exceptions
 * in the Java language. It represents conditions that a Java application might
 * want to catch or propagate. Only objects that are instances of this class or
 * its subclasses are thrown by the Java Virtual Machine or can be thrown by
 * using the {@code throw} statement. Similarly, only this class or one of its
 * subclasses can be used as the argument type in a {@code catch} clause.
 *
 * <p>For compile-time checking of exceptions, {@code Throwable} and its
 * subclasses that are not subclasses of {@link RuntimeException} or
 * {@link Error} are considered checked exceptions.
 *
 * <p>Two subclasses, {@link java.lang.Error} and {@link java.lang.Exception},
 * are typically used to indicate exceptional situations. Instances of these
 * subclasses are usually created with relevant information such as stack trace
 * data.
 *
 * <p>A throwable contains a snapshot of the execution stack of its thread at
 * the time it was created. It can also contain a message string that provides
 * more information about the error. Additionally, a throwable can
 * {@linkplainThrowable#addSuppressed suppress} other throwables from
 * being propagated. Furthermore, a throwable can have a <i>cause</i> which is
 * another throwable that caused the current throwable to be created. This
 * facility is known as <i>chained exception</i>, where a cause can have its own
 * cause, forming a chain of exceptions.
 *
 * <p>One reason for a throwable to have a cause is when the throwing class is
 * built on top of a lower layered abstraction and an operation on the upper
 * layer fails due to a failure in the lower layer. In such cases, it is
 * generally not desirable to propagate the throwable from the lower layer
 * outward as it is unrelated to the abstraction provided by the upper layer.
 * Instead, a "wrapped exception" (an exception containing a cause) can be
 * thrown to communicate the details of the failure without exposing the
 * implementation details or tightly coupling the upper layer's API with the
 * lower layer's exception. This allows flexibility in changing the
 * implementation of the upper layer without affecting its API.
 *
 * <p>Another reason for a throwable to have a cause is when a method must
 * conform to a general-purpose interface that does not permit throwing the
 * cause directly. In such cases, the implementation can wrap the cause in an
 * appropriate unchecked exception to communicate the cause details while
 * conforming to the interface's method signature.
 *
 * <p>A cause can be associated with a throwable in two ways: through a
 * constructor that takes the cause as an argument or through the
 * {@link (java.core.main.lang.Throwable)} method. Throwable classes that
 * allow associating causes should provide constructors that take a cause and
 * delegate to one of the {@code Throwable} constructors that accept a cause.
 * The {@code initCause} method is public, allowing a cause to be associated
 * with any throwable, even with "legacy throwables" that predate the addition
 * of exception chaining to {@code Throwable}.
 *
 * <p>By convention, the {@code Throwable} class and its subclasses have two
 * constructors: one without arguments and one with a {@code String} argument
 * that can be used to provide a detail message. Subclasses that may likely
 * have a cause associated with them should also provide two additional
 * constructors: one that takes a {@code Throwable} (the cause) and one that
 * takes a {@code String} (the detail message) and a {@code Throwable} (the
 * cause).
 *
 * @since Alpha CDK 0.2
 * @author Logan Abernathy
 */

public class Throwable implements Serializable {

    /** SerialversionUID from JDK 0.2 for interoperability */
    @Serial
    private static final long serialVersionUID = -3042686055658047285L;

    /** JVM saves some indication of the stack backtrace in this field. */
    private transient Object backtrace;

    /**
     * Speific details about the Throwable. For example:
     * for FileNotFoundException class, this contains the
     * name of the file that could not be found.
     */
    private String detailMessage;

    /** Holder class to defer initalizing sential objects only used for serialization. */
}