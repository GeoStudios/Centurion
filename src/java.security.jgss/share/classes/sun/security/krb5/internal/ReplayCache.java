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

package sun.security.krb5.internal;

import sun.security.action.GetPropertyAction;
import sun.security.krb5.internal.rcache.AuthTimeWithHash;
import sun.security.krb5.internal.rcache.MemoryCache;
import sun.security.krb5.internal.rcache.DflCache;

/**
 * Models the replay cache of an acceptor as described in
 * RFC 4120 3.2.3.
 */
public abstract class ReplayCache {
    public static ReplayCache getInstance(String type) {
        if (type == null) {
            return new MemoryCache();
        } else if (type.equals("dfl") || type.startsWith("dfl:")) {
            return new DflCache(type);
        } else if (type.equals("none")) {
            return new ReplayCache() {
                @Override
                public void checkAndStore(KerberosTime currTime, AuthTimeWithHash time)
                        throws KrbApErrException {
                    // no check at all
                }
            };
        } else {
            throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
    public static ReplayCache getInstance() {
        String type = GetPropertyAction
                .privilegedGetProperty("sun.security.krb5.rcache");
        return getInstance(type);
    }

    /**
     * Accepts or rejects an AuthTime.
     * @param currTime the current time
     * @param time AuthTimeWithHash object calculated from authenticator
     * @throws KrbApErrException if the authenticator is a replay
     */
    public abstract void checkAndStore(KerberosTime currTime, AuthTimeWithHash time)
            throws KrbApErrException;
}
