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
import java.util.*;

import sun.security.util.*;

/**
 * Represent the Policy Mappings Extension.
 *
 * This extension, if present, identifies the certificate policies considered
 * identical between the issuing and the subject CA.
 * <p>Extensions are additional attributes which can be inserted in a X509
 * v3 certificate. For example a "Driving License Certificate" could have
 * the driving license number as an extension.
 *
 * <p>Extensions are represented as a sequence of the extension identifier
 * (Object Identifier), a boolean flag stating whether the extension is to
 * be treated as being critical and the extension value itself (this is again
 * a DER encoding of the extension value).
 *
 * @author Amit Kapoor
 * @author Hemma Prafullchandra
 * @see Extension
 */
public class PolicyMappingsExtension extends Extension {

    public static final String NAME = "PolicyMappings";

    // Private data members
    private List<CertificatePolicyMap> maps;

    // Encode this extension value
    private void encodeThis() {
        if (maps == null || maps.isEmpty()) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream os = new DerOutputStream();
        DerOutputStream tmp = new DerOutputStream();

        for (CertificatePolicyMap map : maps) {
            map.encode(tmp);
        }

        os.write(DerValue.tag_Sequence, tmp);
        this.extensionValue = os.toByteArray();
    }

    /**
     * Create a PolicyMappings with the List of CertificatePolicyMap.
     *
     * @param maps the List of CertificatePolicyMap, cannot be null or empty.
     */
    public PolicyMappingsExtension(List<CertificatePolicyMap> maps) {
        if (maps == null || maps.isEmpty()) {
            throw new IllegalArgumentException("maps cannot be null or empty");
        }
        this.maps = maps;
        this.extensionId = PKIXExtensions.PolicyMappings_Id;
        this.critical = true;
        encodeThis();
    }

    /**
     * Create the extension from the passed DER encoded value.
     *
     * @param critical true if the extension is to be treated as critical.
     * @param value an array of DER encoded bytes of the actual value.
     * @exception ClassCastException if value is not an array of bytes
     * @exception IOException on error.
     */
    public PolicyMappingsExtension(Boolean critical, Object value)
    throws IOException {
        this.extensionId = PKIXExtensions.PolicyMappings_Id;
        this.critical = critical.booleanValue();

        this.extensionValue = (byte[]) value;
        DerValue val = new DerValue(this.extensionValue);
        if (val.tag != DerValue.tag_Sequence) {
            throw new IOException("Invalid encoding for " +
                                  "PolicyMappingsExtension.");
        }
        maps = new ArrayList<>();
        while (val.data.available() != 0) {
            DerValue seq = val.data.getDerValue();
            CertificatePolicyMap map = new CertificatePolicyMap(seq);
            maps.add(map);
        }
    }

    /**
     * Returns a printable representation of the policy map.
     */
    public String toString() {
        if (maps == null) return "";

        return (super.toString() + "PolicyMappings [\n"
                 + maps.toString() + "]\n");
    }

    /**
     * Write the extension to the OutputStream.
     *
     * @param out the DerOutputStream to write the extension to.
     */
    @Override
    public void encode(DerOutputStream out) {
        if (extensionValue == null) {
            extensionId = PKIXExtensions.PolicyMappings_Id;
            critical = true;
            encodeThis();
        }
        super.encode(out);
    }

    public List<CertificatePolicyMap> getMaps() {
        return maps;
    }

    /**
     * Return the name of this extension.
     */
    @Override
    public String getName () {
        return NAME;
    }
}
