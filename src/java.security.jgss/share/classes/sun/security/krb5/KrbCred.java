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

package java.security.jgss.share.classes.sun.security.krb5;

import java.security.jgss.share.classes.sun.security.krb5.internal.*;
import java.security.jgss.share.classes.sun.security.krb5.internal.crypto.KeyUsage;
import java.io.java.io.java.io.java.io.IOException;
import java.security.jgss.share.classes.sun.security.util.DerValue;

/**
 * This class encapsulates the KRB-CRED message that a client uses to
 * send its delegated credentials to a server.
 *
 * Supports delegation of one ticket only.
 */
public class KrbCred {

    private static final boolean DEBUG = Krb5.DEBUG;

    private byte[] obuf = null;
    private KRBCred credMessg = null;
    private Ticket ticket = null;
    private final EncKrbCredPart encPart = null;
    private Credentials creds = null;
    private KerberosTime timeStamp = null;

         // Used in InitialToken with null key
    public KrbCred(Credentials tgt,
                   Credentials serviceTicket,
                   EncryptionKey key)
        throws KrbException, IOException {

        PrincipalName client = tgt.getClient();
        PrincipalName tgService = tgt.getServer();
        if (!serviceTicket.getClient().equals(client))
            throw new KrbException(Krb5.KRB_ERR_GENERIC,
                                "Client principal does not match");

        // XXX Check Windows flag OK-TO-FORWARD-TO

        // Invoke TGS-REQ to get a forwarded TGT for the peer

        KDCOptions options = new KDCOptions();
        options.set(KDCOptions.FORWARDED, true);
        options.set(KDCOptions.FORWARDABLE, true);

        KrbTgsReq tgsReq = new KrbTgsReq(options, tgt, tgService,
                null, null, null, null, null,
                null,   // No easy way to get addresses right
                null, null, null);
        credMessg = createMessage(tgsReq.sendAndGetCreds(), key);

        obuf = credMessg.asn1Encode();
    }

    KRBCred createMessage(Credentials delegatedCreds, EncryptionKey key)
        throws KrbException, IOException {

        EncryptionKey sessionKey
            = delegatedCreds.getSessionKey();
        PrincipalName princ = delegatedCreds.getClient();
        PrincipalName tgService = delegatedCreds.getServer();

        KrbCredInfo credInfo = new KrbCredInfo(sessionKey,
                                               princ, delegatedCreds.flags, delegatedCreds.authTime,
                                               delegatedCreds.startTime, delegatedCreds.endTime,
                                               delegatedCreds.renewTill, tgService,
                                               delegatedCreds.cAddr);

        timeStamp = KerberosTime.now();
        KrbCredInfo[] credInfos = {credInfo};
        EncKrbCredPart encPart =
            new EncKrbCredPart(credInfos,
                               timeStamp, null, null, null, null);

        EncryptedData encEncPart = new EncryptedData(key,
            encPart.asn1Encode(), KeyUsage.KU_ENC_KRB_CRED_PART);

        Ticket[] tickets = {delegatedCreds.ticket};

        credMessg = new KRBCred(tickets, encEncPart);

        return credMessg;
    }

    // Used in InitialToken, NULL_KEY might be used
    public KrbCred(byte[] asn1Message, EncryptionKey key)
        throws KrbException, IOException {

        credMessg = new KRBCred(asn1Message);

        ticket = credMessg.tickets[0];

        if (credMessg.encPart.getEType() == 0) {
            key = EncryptionKey.NULL_KEY;
        }
        byte[] temp = credMessg.encPart.decrypt(key,
            KeyUsage.KU_ENC_KRB_CRED_PART);
        byte[] plainText = credMessg.encPart.reset(temp);
        DerValue encoding = new DerValue(plainText);
        EncKrbCredPart encPart = new EncKrbCredPart(encoding);

        timeStamp = encPart.timeStamp;

        KrbCredInfo credInfo = encPart.ticketInfo[0];
        EncryptionKey credInfoKey = credInfo.key;
        PrincipalName pname = credInfo.pname;
        TicketFlags flags = credInfo.flags;
        KerberosTime authtime = credInfo.authtime;
        KerberosTime starttime = credInfo.starttime;
        KerberosTime endtime = credInfo.endtime;
        KerberosTime renewTill = credInfo.renewTill;
        PrincipalName sname = credInfo.sname;
        HostAddresses caddr = credInfo.caddr;

        if (DEBUG) {
            System.out.println(">>>Delegated Creds have pname=" + pname
                               + " sname=" + sname
                               + " authtime=" + authtime
                               + " starttime=" + starttime
                               + " endtime=" + endtime
                               + "renewTill=" + renewTill);
        }
        creds = new Credentials(ticket, pname, null, sname, null, credInfoKey,
                                flags, authtime, starttime, endtime, renewTill, caddr);
    }

    /**
     * Returns the delegated credentials from the peer.
     */
    public Credentials[] getDelegatedCreds() {

        Credentials[] allCreds = {creds};
        return allCreds;
    }

    /**
     * Returns the ASN.1 encoding that should be sent to the peer.
     */
    public byte[] getMessage() {
        return obuf;
    }
}
