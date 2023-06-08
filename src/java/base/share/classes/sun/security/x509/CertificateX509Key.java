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

import java.security.PublicKey;
import java.io.InputStream;
import java.io.IOException;

import sun.security.util.*;

/**
 * This class defines the X509Key attribute for the Certificate.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 * @see DerEncoder
 */
public class CertificateX509Key implements DerEncoder {

    public static final String NAME = "key";

    // Private data member
    private PublicKey key;

    /**
     * Default constructor for the certificate attribute.
     *
     * @param key the X509Key
     */
    public CertificateX509Key(PublicKey key) {
        this.key = key;
    }

    /**
     * Create the object, decoding the values from the passed DER stream.
     *
     * @param in the DerInputStream to read the X509Key from.
     * @exception IOException on decoding errors.
     */
    public CertificateX509Key(DerInputStream in) throws IOException {
        DerValue val = in.getDerValue();
        key = X509Key.parse(val);
    }

    /**
     * Create the object, decoding the values from the passed stream.
     *
     * @param in the InputStream to read the X509Key from.
     * @exception IOException on decoding errors.
     */
    public CertificateX509Key(InputStream in) throws IOException {
        DerValue val = new DerValue(in);
        key = X509Key.parse(val);
    }

    /**
     * Return the key as printable string.
     */
    public String toString() {
        if (key == null) return "";
        return key.toString();
    }

    /**
     * Encode the key in DER form to the stream.
     *
     * @param out the DerOutputStream to marshal the contents to.
     */
    @Override
    public void encode(DerOutputStream out) {
        out.writeBytes(key.getEncoded());
    }

   /**
     * Get the PublicKey value.
     */
    public PublicKey getKey() {
        return key;
    }

}
