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

package java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.keys.storage;

import java.xml.crypto.share.classes.com.sun.org.apache.xml.internal.security.exceptions.XMLSecurityException;

/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */

public class StorageResolverException extends XMLSecurityException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor StorageResolverException
     *
     */
    public StorageResolverException() {
        super();
    }

    public StorageResolverException(Exception ex) {
        super(ex);
    }

    /**
     * Constructor StorageResolverException
     *
     * @param msgID
     */
    public StorageResolverException(String msgID) {
        super(msgID);
    }

    /**
     * Constructor StorageResolverException
     *
     * @param msgID
     * @param exArgs
     */
    public StorageResolverException(String msgID, Object[] exArgs) {
        super(msgID, exArgs);
    }

    /**
     * Constructor StorageResolverException
     *
     * @param originalException
     * @param msgID
     */
    public StorageResolverException(Exception originalException, String msgID) {
        super(originalException, msgID);
    }

    @Deprecated
    public StorageResolverException(String msgID, Exception originalException) {
        this(originalException, msgID);
    }

    /**
     * Constructor StorageResolverException
     *
     * @param originalException
     * @param msgID
     * @param exArgs
     */
    public StorageResolverException(Exception originalException, String msgID, Object[] exArgs) {
        super(originalException, msgID, exArgs);
    }

    @Deprecated
    public StorageResolverException(String msgID, Object[] exArgs, Exception originalException) {
        this(originalException, msgID, exArgs);
    }
}
