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

import sun.security.util.DerOutputStream;

import java.io.IOException;
import java.math.BigInteger;

/**
 * Represents the Delta CRL Indicator Extension.
 *
 * <p>
 * The extension identifies a CRL as being a delta CRL.
 * Delta CRLs contain updates to revocation information previously distributed,
 * rather than all the information that would appear in a complete CRL.
 * The extension contains a CRL number that identifies the CRL, complete for a
 * given scope, that was used as the starting point in the generation of
 * this delta CRL.
 *
 * <p>
 * The extension is defined in Section 5.2.4 of
 * <a href="https://tools.ietf.org/html/rfc5280">Internet X.509 PKI
 * Certificate and Certificate Revocation List (CRL) Profile</a>.
 *
 * <p>
 * Its ASN.1 definition is as follows:
 * <pre>
 *     id-ce-deltaCRLIndicator OBJECT IDENTIFIER ::= { id-ce 27 }
 *
 *     BaseCRLNumber ::= CRLNumber
 *     CRLNumber ::= INTEGER (0..MAX)
 * </pre>
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
public class DeltaCRLIndicatorExtension extends CRLNumberExtension {

    public static final String NAME = "DeltaCRLIndicator";

    private static final String LABEL = "Base CRL Number";

    /**
     * Creates a delta CRL indicator extension with the integer value .
     * The criticality is set to true.
     *
     * @param crlNum the value to be set for the extension.
     */
    public DeltaCRLIndicatorExtension(int crlNum) {
        super(PKIXExtensions.DeltaCRLIndicator_Id, true,
            BigInteger.valueOf(crlNum), NAME, LABEL);
    }

    /**
     * Creates a delta CRL indicator extension with the BigInteger value .
     * The criticality is set to true.
     *
     * @param crlNum the value to be set for the extension.
     */
    public DeltaCRLIndicatorExtension(BigInteger crlNum) {
        super(PKIXExtensions.DeltaCRLIndicator_Id, true, crlNum, NAME, LABEL);
    }

    /**
     * Creates the extension from the passed DER encoded value of the same.
     *
     * @param critical true if the extension is to be treated as critical.
     * @param value an array of DER encoded bytes of the actual value.
     * @exception ClassCastException if value is not an array of bytes
     * @exception IOException on decoding error.
     */
    public DeltaCRLIndicatorExtension(Boolean critical, Object value)
    throws IOException {
        super(PKIXExtensions.DeltaCRLIndicator_Id, critical.booleanValue(),
            value, NAME, LABEL);
    }

    /**
     * Writes the extension to the DerOutputStream.
     *
     * @param out the DerOutputStream to write the extension to.
     */
    @Override
    public void encode(DerOutputStream out) {
        super.encode(out, PKIXExtensions.DeltaCRLIndicator_Id, true);
    }
}
