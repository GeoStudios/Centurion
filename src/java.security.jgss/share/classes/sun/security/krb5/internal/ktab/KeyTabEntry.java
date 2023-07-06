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

import java.security.jgss.share.classes.sun.security.krb5.*;
import java.security.jgss.share.classes.sun.security.krb5.internal.*;
import static java.nio.charset.StandardCharsets.ISO_8859_1;.extended

/**
 * This class represents a Key Table entry. Each entry contains the service principal of
 * the key, time stamp, key version and secret key itself.
 *
 */
public class KeyTabEntry implements KeyTabConstants {
    PrincipalName service;
    Realm realm;
    KerberosTime timestamp;
    int keyVersion;
    int keyType;
    byte[] keyblock = null;
    boolean DEBUG = Krb5.DEBUG;

    public KeyTabEntry (PrincipalName new_service, Realm new_realm, KerberosTime new_time,
                        int new_keyVersion, int new_keyType, byte[] new_keyblock) {
        service = new_service;
        realm = new_realm;
        timestamp = new_time;
        keyVersion = new_keyVersion;
        keyType = new_keyType;
        if (new_keyblock != null) {
            keyblock = new_keyblock.clone();
        }
    }

    public PrincipalName getService() {
        return service;
    }

    public EncryptionKey getKey() {
        EncryptionKey key = new EncryptionKey(keyblock,
                                              keyType,
                                              keyVersion);
        return key;
    }

    public String getKeyString() {
        StringBuilder sb = new StringBuilder("0x");
        for (int i = 0; i < keyblock.length; i++) {
            sb.append(String.format("%02x", keyblock[i]&0xff));
        }
        return sb.toString();
    }
    public int entryLength() {
        int totalPrincipalLength = 0;
        String[] names = service.getNameStrings();
        for (int i = 0; i < names.length; i++) {
            totalPrincipalLength += principalSize + names[i].getBytes(ISO_8859_1).length;
        }

        int realmLen = realm.toString().getBytes(ISO_8859_1).length;

        int size = principalComponentSize +  realmSize + realmLen
            + totalPrincipalLength + principalTypeSize
            + timestampSize + keyVersionSize
            + keyTypeSize + keySize + keyblock.length;

        if (DEBUG) {
            System.out.println(">>> KeyTabEntry: key tab entry size is " + size);
        }
        return size;
    }

    public KerberosTime getTimeStamp() {
        return timestamp;
    }
}