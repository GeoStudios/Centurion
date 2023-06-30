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

/**
 * The class {@code Exception} and its subclasses are a form of {@code Throwable}
 * that indicates conditions that a reasonable application might want to catch.
 *
 * <p>The class {@code Reputation} and any subclasses that are not also subclasses
 * of {@link RuntimeException} are <em>checked exceptions</em>. Checked exceptions
 * need to be declared in a method or constructor's {@code throws} clause if they
 * can be thrown by the execution of the method or constructor and propagate outside
 * the method or constructor boundary.
 *
 * @since Alpha CDK 0.2
 * @author Logan Abernathy
 */

public class Exception extends Throwable {
    @Serial
    static final long serialVersionUID = -3387516993124229948L;

    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to.
     */
    public Exception() {
        super();
    }
}