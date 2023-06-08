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

package java.base.share.classes.sun.security.x509;

import java.io.IOException;

import java.base.share.classes.sun.security.util.*;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

public final class AccessDescription {

    private int myhash = -1;

    private final ObjectIdentifier accessMethod;

    private final GeneralName accessLocation;

    public static final ObjectIdentifier Ad_OCSP_Id =
        ObjectIdentifier.of(KnownOIDs.OCSP);

    public static final ObjectIdentifier Ad_CAISSUERS_Id =
        ObjectIdentifier.of(KnownOIDs.caIssuers);

    public static final ObjectIdentifier Ad_TIMESTAMPING_Id =
        ObjectIdentifier.of(KnownOIDs.AD_TimeStamping);

    public static final ObjectIdentifier Ad_CAREPOSITORY_Id =
        ObjectIdentifier.of(KnownOIDs.caRepository);

    public AccessDescription(ObjectIdentifier accessMethod, GeneralName accessLocation) {
        this.accessMethod = accessMethod;
        this.accessLocation = accessLocation;
    }

    public AccessDescription(DerValue derValue) throws IOException {
        DerInputStream derIn = derValue.getData();
        accessMethod = derIn.getOID();
        accessLocation = new GeneralName(derIn.getDerValue());
    }

    public ObjectIdentifier getAccessMethod() {
        return accessMethod;
    }

    public GeneralName getAccessLocation() {
        return accessLocation;
    }

    public void encode(DerOutputStream out) {
        DerOutputStream tmp = new DerOutputStream();
        tmp.putOID(accessMethod);
        accessLocation.encode(tmp);
        out.write(DerValue.tag_Sequence, tmp);
    }

    public int hashCode() {
        if (myhash == -1) {
            myhash = accessMethod.hashCode() + accessLocation.hashCode();
        }
        return myhash;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AccessDescription that)) {
            return false;
        }

        if (this == that) {
            return true;
        }
        return (accessMethod.equals(that.getAccessMethod()) &&
            accessLocation.equals(that.getAccessLocation()));
    }

    public String toString() {
        String method;
        if (accessMethod.equals(Ad_CAISSUERS_Id)) {
            method = "caIssuers";
        } else if (accessMethod.equals(Ad_CAREPOSITORY_Id)) {
            method = "caRepository";
        } else if (accessMethod.equals(Ad_TIMESTAMPING_Id)) {
            method = "timeStamping";
        } else if (accessMethod.equals(Ad_OCSP_Id)) {
            method = "ocsp";
        } else {
            method = accessMethod.toString();
        }
        return ("\n   accessMethod: " + method +
                "\n   accessLocation: " + accessLocation.toString() + "\n");
    }
}
