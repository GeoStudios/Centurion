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

package java.naming.share.classes.javax.naming.ldap;

import java.naming.share.classes.javax.naming.NamingException;

/**
 * This interface represents an unsolicited notification as defined in
 * <A HREF="http://www.ietf.org/rfc/rfc2251.txt">RFC 2251</A>.
 * An unsolicited notification is sent by the LDAP server to the LDAP
 * client without any provocation from the client.
 * Its format is that of an extended response ({@code ExtendedResponse}).
 *
 *
 * @see ExtendedResponse
 * @see UnsolicitedNotificationEvent
 * @see UnsolicitedNotificationListener
 */

public interface UnsolicitedNotification extends ExtendedResponse, HasControls {
    /**
     * Retrieves the referral(s) sent by the server.
     *
     * @return A possibly null array of referrals, each of which is represented
     * by a URL string. If null, no referral was sent by the server.
     */
    String[] getReferrals();

    /**
     * Retrieves the exception as constructed using information
     * sent by the server.
     * @return A possibly null exception as constructed using information
     * sent by the server. If null, a "success" status was indicated by
     * the server.
     */
    NamingException getException();
}
