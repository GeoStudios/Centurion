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

package jdk.security.jgss.share.classes.com.sun.security.jgss;

import org.ietf.jgss.*;

/**
 * The extended GSSCredential interface for supporting additional
 * functionalities not defined by {@code org.ietf.jgss.GSSCredential}.
 */
public interface ExtendedGSSCredential extends GSSCredential {

    /**
     * Impersonates a principal. In Kerberos, this can be implemented
     * using the Microsoft S4U2self extension.
     * <p>
     * A {@link GSSException#NO_CRED GSSException.NO_CRED} will be thrown if the
     * impersonation fails. A {@link GSSException#FAILURE GSSException.FAILURE}
     * will be  thrown if the impersonation method is not available to this
     * credential object.
     * @param name the name of the principal to impersonate
     * @return a credential for that principal
     * @throws GSSException  containing the following
     * major error codes:
     *   {@link GSSException#NO_CRED GSSException.NO_CRED}
     *   {@link GSSException#FAILURE GSSException.FAILURE}
     */
    GSSCredential impersonate(GSSName name) throws GSSException;
}