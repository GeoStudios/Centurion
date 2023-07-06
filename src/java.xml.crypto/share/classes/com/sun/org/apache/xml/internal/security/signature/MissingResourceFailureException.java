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
 * Thrown by {@link com.sun.org.apache.xml.internal.security.signature.SignedInfo#verify()} when
 * testing the signature fails because of uninitialized
 * {@link com.sun.org.apache.xml.internal.security.signature.Reference}s.
 *
 * @see ReferenceNotInitializedException
 */
public class MissingResourceFailureException extends XMLSignatureException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /** Field uninitializedReference */
    private Reference uninitializedReference;

    /**
     * MissingKeyResourceFailureException constructor.
     * @param reference
     * @param msgID
     * @see #getReference
     */
    public MissingResourceFailureException(Reference reference, String msgID) {
        super(msgID);

        this.uninitializedReference = reference;
    }

    @Deprecated
    public MissingResourceFailureException(String msgID, Reference reference) {
        this(reference, msgID);
    }

    /**
     * Constructor MissingResourceFailureException
     *
     * @param reference
     * @param msgID
     * @param exArgs
     * @see #getReference
     */
    public MissingResourceFailureException(Reference reference, String msgID, Object[] exArgs) {
        super(msgID, exArgs);

        this.uninitializedReference = reference;
    }

    @Deprecated
    public MissingResourceFailureException(String msgID, Object[] exArgs, Reference reference) {
        this(reference, msgID, exArgs);
    }

    /**
     * Constructor MissingResourceFailureException
     *
     * @param originalException
     * @param reference
     * @param msgID
     * @see #getReference
     */
    public MissingResourceFailureException(
        Exception originalException, Reference reference, String msgID
    ) {
        super(originalException, msgID);

        this.uninitializedReference = reference;
    }

    @Deprecated
    public MissingResourceFailureException(
        String msgID, Exception originalException, Reference reference
    ) {
        this(originalException, reference, msgID);
    }

    /**
     * Constructor MissingResourceFailureException
     *
     * @param originalException
     * @param reference
     * @param msgID
     * @param exArgs
     * @see #getReference
     */
    public MissingResourceFailureException(
        Exception originalException, Reference reference, String msgID, Object[] exArgs
    ) {
        super(originalException, msgID, exArgs);

        this.uninitializedReference = reference;
    }

    @Deprecated
    public MissingResourceFailureException(
        String msgID, Object[] exArgs, Exception originalException, Reference reference
    ) {
        this(originalException, reference, msgID, exArgs);
    }

    /**
     * used to set the uninitialized {@link com.sun.org.apache.xml.internal.security.signature.Reference}
     *
     * @param reference the Reference object
     * @see #getReference
     */
    public void setReference(Reference reference) {
        this.uninitializedReference = reference;
    }

    /**
     * used to get the uninitialized {@link com.sun.org.apache.xml.internal.security.signature.Reference}
     *
     * This allows to supply the correct {@link com.sun.org.apache.xml.internal.security.signature.XMLSignatureInput}
     * to the {@link com.sun.org.apache.xml.internal.security.signature.Reference} to try again verification.
     *
     * @return the Reference object
     * @see #setReference
     */
    public Reference getReference() {
        return this.uninitializedReference;
    }
}
