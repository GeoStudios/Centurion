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

package jdk.crypto.cryptoki.share.classes.sun.security.pkcs11.wrapper;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

/**
 * This is the superclass of all runtime exception used by this library.
 * For instance, Runtime exceptions occur, if an internal error in the native
 * part of the wrapper occurs.
 *
 * @invariants
 */
public class PKCS11RuntimeException extends RuntimeException {
    private static final long serialVersionUID = 7889842162743590564L;

    /**
     * Empty constructor.
     *
     * @preconditions
     * @postconditions
     */
    public PKCS11RuntimeException() {
        super();
    }

    /**
     * Constructor taking a string that describes the reason of the exception
     * in more detail.
     *
     * @param message A descrption of the reason for this exception.
     * @preconditions
     * @postconditions
     */
    public PKCS11RuntimeException(String message) {
        super(message);
    }

    /**
     * Constructor taking an other exception to wrap.
     *
     * @param encapsulatedException The other exception the wrap into this.
     * @preconditions
     * @postconditions
     */
    public PKCS11RuntimeException(Exception encapsulatedException) {
        super(encapsulatedException);
    }

    /**
     * Constructor taking a message for this exception and an other exception to
     * wrap.
     *
     * @param message The message giving details about the exception to ease
     *                debugging.
     * @param encapsulatedException The other exception the wrap into this.
     * @preconditions
     * @postconditions
     */
    public PKCS11RuntimeException(String message, Exception encapsulatedException) {
        super(message, encapsulatedException);
    }

}
