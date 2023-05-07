/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.share.classes.sun.security.util;

import java.math.BigInteger;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;

/**
 * Contains Elliptic Curve parameters.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public final class NamedCurve extends ECParameterSpec {

    // friendly names with stdName followed by aliases
    private final String[] nameAndAliases;

    // well known OID
    private final String oid;

    // encoded form (as NamedCurve identified via OID)
    private final byte[] encoded;

    NamedCurve(KnownOIDs ko, EllipticCurve curve,
            ECPoint g, BigInteger n, int h) {
        super(curve, g, n, h);
        String[] aliases = ko.aliases();
        this.nameAndAliases = new String[aliases.length + 1];
        nameAndAliases[0] = ko.stdName();
        System.arraycopy(aliases, 0, nameAndAliases, 1, aliases.length);

        this.oid = ko.value();

        DerOutputStream out = new DerOutputStream();
        out.putOID(ObjectIdentifier.of(ko));
        encoded = out.toByteArray();
    }

    // returns the curve's standard name followed by its aliases
    public String[] getNameAndAliases() {
        return nameAndAliases;
    }

    public byte[] getEncoded() {
        return encoded.clone();
    }

    public String getObjectId() {
        return oid;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(nameAndAliases[0]);
        if (nameAndAliases.length > 1) {
            sb.append(" [");
            int j = 1;
            while (j < nameAndAliases.length - 1) {
                sb.append(nameAndAliases[j++]);
                sb.append(',');
            }
            sb.append(nameAndAliases[j] + "]");
        }
        sb.append(" (" + oid + ")");
        return sb.toString();
    }
}
