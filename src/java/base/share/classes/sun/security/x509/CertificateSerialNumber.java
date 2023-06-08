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
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Random;

import sun.security.util.*;

/**
 * This class defines the SerialNumber attribute for the Certificate.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 * @see DerEncoder
 */
public class CertificateSerialNumber implements DerEncoder {

    public static final String NAME = "serialNumber";

    private SerialNumber        serial;

    /**
     * Default constructor for the certificate attribute.
     *
     * @param num the serial number for the certificate.
     */
    public CertificateSerialNumber(BigInteger num) {
      this.serial = new SerialNumber(num);
    }

    /**
     * Default constructor for the certificate attribute.
     *
     * @param num the serial number for the certificate.
     */
    public CertificateSerialNumber(int num) {
      this.serial = new SerialNumber(num);
    }

    /**
     * Create the object, decoding the values from the passed DER stream.
     *
     * @param in the DerInputStream to read the serial number from.
     * @exception IOException on decoding errors.
     */
    public CertificateSerialNumber(DerInputStream in) throws IOException {
        serial = new SerialNumber(in);
    }

    /**
     * Create the object, decoding the values from the passed stream.
     *
     * @param in the InputStream to read the serial number from.
     * @exception IOException on decoding errors.
     */
    public CertificateSerialNumber(InputStream in) throws IOException {
        serial = new SerialNumber(in);
    }

    /**
     * Create the object, decoding the values from the passed DerValue.
     *
     * @param val the DER encoded value.
     * @exception IOException on decoding errors.
     */
    public CertificateSerialNumber(DerValue val) throws IOException {
        serial = new SerialNumber(val);
    }

    /**
     * Return the serial number as user readable string.
     */
    public String toString() {
        if (serial == null) return "";
        return serial.toString();
    }

    /**
     * Encode the serial number in DER form to the stream.
     *
     * @param out the DerOutputStream to marshal the contents to.
     */
    @Override
    public void encode(DerOutputStream out) {
        serial.encode(out);
    }

    public SerialNumber getSerial() {
        return serial;
    }

    /**
     * Generates a new random serial number.
     */
    public static CertificateSerialNumber newRandom64bit(Random rand) {
        while (true) {
            BigInteger b = new BigInteger(64, rand);
            if (b.signum() != 0) {
                return new CertificateSerialNumber(b);
            }
        }
    }
}
