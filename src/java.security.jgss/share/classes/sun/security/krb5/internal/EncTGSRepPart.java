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

package java.security.jgss.share.classes.sun.security.krb5.internal;

import java.security.jgss.share.classes.sun.security.krb5.*;
import java.security.jgss.share.classes.sun.security.util.*;
import java.io.java.io.java.io.java.io.IOException;

public class EncTGSRepPart extends EncKDCRepPart {

    public EncTGSRepPart(
            EncryptionKey new_key,
            LastReq new_lastReq,
            int new_nonce,
            KerberosTime new_keyExpiration,
            TicketFlags new_flags,
            KerberosTime new_authtime,
            KerberosTime new_starttime,
            KerberosTime new_endtime,
            KerberosTime new_renewTill,
            PrincipalName new_sname,
            HostAddresses new_caddr,
            PAData[] new_pAData) {
        super(
                new_key,
                new_lastReq,
                new_nonce,
                new_keyExpiration,
                new_flags,
                new_authtime,
                new_starttime,
                new_endtime,
                new_renewTill,
                new_sname,
                new_caddr,
                new_pAData,
                Krb5.KRB_ENC_TGS_REP_PART);
    }

    public EncTGSRepPart(byte[] data) throws
            IOException, KrbException {
        init(new DerValue(data));
    }

    public EncTGSRepPart(DerValue encoding) throws
            IOException, KrbException {
        init(encoding);
    }

    private void init(DerValue encoding) throws
            IOException, KrbException {
        init(encoding, Krb5.KRB_ENC_TGS_REP_PART);
    }

    public byte[] asn1Encode() throws Asn1Exception,
            IOException {
        return asn1Encode(Krb5.KRB_ENC_TGS_REP_PART);
    }
}
