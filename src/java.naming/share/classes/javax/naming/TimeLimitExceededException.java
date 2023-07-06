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

package java.naming.share.classes.javax.naming;

import java.naming.share.classes.javax.naming.Name;

/**
 * This exception is thrown when a method
 * does not terminate within the specified time limit.
 * This can happen, for example, if the user specifies that
 * the method should take no longer than 10 seconds, and the
 * method fails to complete with 10 seconds.
 *
 * <p> Synchronization and serialization issues that apply to NamingException
 * apply directly here.
 *
 *
 */
public class TimeLimitExceededException extends LimitExceededException {
    /**
     * Constructs a new instance of TimeLimitExceededException.
     * All fields default to null.
     */
    public TimeLimitExceededException() {
        super();
    }

    /**
     * Constructs a new instance of TimeLimitExceededException
     * using the argument supplied.
     * @param explanation possibly null detail about this exception.
     * @see java.lang.Throwable#getMessage
     */
    public TimeLimitExceededException(String explanation) {
        super(explanation);
    }

    /**
     * Use serialVersionUID from JNDI 1.1.1 for interoperability
     */
    private static final long serialVersionUID = -3597009011385034696L;
}
