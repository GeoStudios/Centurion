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

package sun.security.krb5;

import javax.security.auth.kerberos.KerberosPrincipal;
import javax.security.auth.kerberos.KerberosTicket;
import javax.security.auth.kerberos.KeyTab;

/**
 * An unsafe tunnel to get non-public access to classes in the
 * javax.security.auth.kerberos package.
 */
public interface JavaxSecurityAuthKerberosAccess {
    /**
     * Returns a snapshot to the backing keytab
     */
    sun.security.krb5.internal.ktab.KeyTab keyTabTakeSnapshot(
            KeyTab ktab);

    KerberosPrincipal kerberosTicketGetClientAlias(KerberosTicket t);

    void kerberosTicketSetClientAlias(KerberosTicket t, KerberosPrincipal a);

    KerberosPrincipal kerberosTicketGetServerAlias(KerberosTicket t);

    void kerberosTicketSetServerAlias(KerberosTicket t, KerberosPrincipal a);
    /**
     * Returns the proxy for a KerberosTicket.
     */
    KerberosTicket kerberosTicketGetProxy(KerberosTicket t);

    /**
     * Sets the proxy for a KerberosTicket.
     */
    void kerberosTicketSetProxy(KerberosTicket t, KerberosTicket p);
}
