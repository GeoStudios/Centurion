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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.signature;

















/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */


/**
 * Raised if verifying a {@link com.sun.org.apache.xml.internal.security.signature.Reference} fails
 * because of an uninitialized {@link com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput}
 *
 */
public class ReferenceNotInitializedException extends XMLSignatureException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor ReferenceNotInitializedException
     *
     */
    public ReferenceNotInitializedException() {
        super();
    }

    public ReferenceNotInitializedException(Exception ex) {
        super(ex);
    }

    /**
     * Constructor ReferenceNotInitializedException
     *
     * @param msgID
     */
    public ReferenceNotInitializedException(String msgID) {
        super(msgID);
    }

    /**
     * Constructor ReferenceNotInitializedException
     *
     * @param msgID
     * @param exArgs
     */
    public ReferenceNotInitializedException(String msgID, Object[] exArgs) {
        super(msgID, exArgs);
    }

    /**
     * Constructor ReferenceNotInitializedException
     *
     * @param originalException
     * @param msgID
     */
    public ReferenceNotInitializedException(Exception originalException, String msgID) {
        super(originalException, msgID);
    }

    @Deprecated
    public ReferenceNotInitializedException(String msgID, Exception originalException) {
        this(originalException, msgID);
    }

    /**
     * Constructor ReferenceNotInitializedException
     *
     * @param originalException
     * @param msgID
     * @param exArgs
     */
    public ReferenceNotInitializedException(Exception originalException, String msgID, Object[] exArgs) {
        super(originalException, msgID, exArgs);
    }

    @Deprecated
    public ReferenceNotInitializedException(String msgID, Object[] exArgs, Exception originalException) {
        this(originalException, msgID, exArgs);
    }
}
