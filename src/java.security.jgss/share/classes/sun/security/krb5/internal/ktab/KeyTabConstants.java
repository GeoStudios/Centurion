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

package java.security.jgss.share.classes.sun.security.krb5.internal.ktab;

import java.security.jgss.share.classes.sun.security.krb5.internal.*;

/**
 * This class represents a Key Table entry. Each entry contains the service principal of
 * the key, time stamp, key version and secret key itself.
 *
 */
public interface KeyTabConstants {
    int principalComponentSize = 2;
    int realmSize = 2;
    int principalSize = 2;
    int principalTypeSize = 4;
    int timestampSize = 4;
    int keyVersionSize = 1;
    int keyTypeSize = 2;
    int keySize = 2;
    int KRB5_KT_VNO_1 = 0x0501;    /* krb v5, keytab version 1 (DCE compat) */
    int KRB5_KT_VNO        = 0x0502;       /* krb v5, keytab version 2 (standard)  */
}
