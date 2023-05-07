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
import java.util.List;

/**
 * Represents the Freshest CRL Extension.
 *
 * <p>
 * The extension identifies how delta CRL information for a
 * complete CRL is obtained.
 *
 * <p>
 * The extension is defined in Section 5.2.6 of
 * <a href="https://tools.ietf.org/html/rfc5280">Internet X.509 PKI
 * Certificate and Certificate Revocation List (CRL) Profile</a>.
 *
 * <p>
 * Its ASN.1 definition is as follows:
 * <pre>
 *     id-ce-freshestCRL OBJECT IDENTIFIER ::=  { id-ce 46 }
 *
 *     FreshestCRL ::= CRLDistributionPoints
 * </pre>
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */
public class FreshestCRLExtension extends CRLDistributionPointsExtension {

    /**
     * Attribute name.
     */
    public static final String NAME = "FreshestCRL";

    /**
     * Creates a fresh CRL extension.
     * The criticality is set to false.
     *
     * @param distributionPoints the list of delta CRL distribution points.
     */
    public FreshestCRLExtension(List<DistributionPoint> distributionPoints) {

        super(PKIXExtensions.FreshestCRL_Id, false, distributionPoints, NAME);
    }

    /**
     * Creates the extension from the passed DER encoded value of the same.
     *
     * @param critical true if the extension is to be treated as critical.
     * @param value an array of DER encoded bytes of the actual value.
     * @exception IOException on decoding error.
     */
    public FreshestCRLExtension(Boolean critical, Object value)
    throws IOException {
        super(PKIXExtensions.FreshestCRL_Id, critical.booleanValue(), value,
            NAME);
    }

    /**
     * Writes the extension to the DerOutputStream.
     *
     * @param out the DerOutputStream to write the extension to.
     */
    @Override
    public void encode(DerOutputStream out) {
        super.encode(out, PKIXExtensions.FreshestCRL_Id, false);
    }
}
