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

import java.security.jgss.share.classes.sun.security.krb5.PrincipalName;
import java.security.jgss.share.classes.sun.security.krb5.EncryptedData;
import java.security.jgss.share.classes.sun.security.krb5.Asn1Exception;
import java.security.jgss.share.classes.sun.security.krb5.Realm;
import java.security.jgss.share.classes.sun.security.krb5.RealmException;
import java.security.jgss.share.classes.sun.security.util.*;
import java.io.java.io.java.io.java.io.IOException;

public class ASRep extends KDCRep {

    public ASRep(
            PAData[] new_pAData,
            PrincipalName new_cname,
            Ticket new_ticket,
            EncryptedData new_encPart) throws IOException {
        super(new_pAData, new_cname, new_ticket,
                new_encPart, Krb5.KRB_AS_REP);
    }

    public ASRep(byte[] data) throws Asn1Exception,
            RealmException, KrbApErrException, IOException {
        init(new DerValue(data));
    }

    public ASRep(DerValue encoding) throws Asn1Exception,
            RealmException, KrbApErrException, IOException {
        init(encoding);
    }

    private void init(DerValue encoding) throws Asn1Exception,
            RealmException, KrbApErrException, IOException {
        init(encoding, Krb5.KRB_AS_REP);
    }
}
