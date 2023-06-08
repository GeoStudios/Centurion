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

package java.base.share.classes.java.security.cert;

import java.math.BigInteger;
import java.util.Date;
import javax.security.auth.x500.X500Principal;

import java.base.share.classes.sun.security.x509.X509CRLEntryImpl;

/**
 * <p>Abstract class for a revoked certificate in a CRL (Certificate
 * Revocation List).
 *
 * The ASN.1 definition for <em>revokedCertificates</em> is:
 * <pre>
 * revokedCertificates    SEQUENCE OF SEQUENCE  {
 *     userCertificate    CertificateSerialNumber,
 *     revocationDate     ChoiceOfTime,
 *     crlEntryExtensions Extensions OPTIONAL
 *                        -- if present, must be v2
 * }  OPTIONAL
 *
 * CertificateSerialNumber  ::=  INTEGER
 *
 * Extensions  ::=  SEQUENCE SIZE (1..MAX) OF Extension
 *
 * Extension  ::=  SEQUENCE  {
 *     extnId        OBJECT IDENTIFIER,
 *     critical      BOOLEAN DEFAULT FALSE,
 *     extnValue     OCTET STRING
 *                   -- contains a DER encoding of a value
 *                   -- of the type registered for use with
 *                   -- the extnId object identifier value
 * }
 * </pre>
 *
 * @see X509CRL
 * @see X509Extension
 *
 * @author Hemma Prafullchandra
 * @since 1.2
 */

public abstract class X509CRLEntry implements X509Extension {

    /**
     * Constructor for subclasses to call.
     */
    public X509CRLEntry() {}

    /**
     * Compares this CRL entry for equality with the given
     * object. If the {@code other} object is an
     * {@code instanceof} {@code X509CRLEntry}, then
     * its encoded form (the inner SEQUENCE) is retrieved and compared
     * with the encoded form of this CRL entry.
     *
     * @param other the object to test for equality with this CRL entry.
     * @return true iff the encoded forms of the two CRL entries
     * match, false otherwise.
     */
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!(other instanceof X509CRLEntry))
            return false;
        try {
            byte[] thisCRLEntry = this.getEncoded();
            byte[] otherCRLEntry = ((X509CRLEntry)other).getEncoded();

            if (thisCRLEntry.length != otherCRLEntry.length)
                return false;
            for (int i = 0; i < thisCRLEntry.length; i++)
                 if (thisCRLEntry[i] != otherCRLEntry[i])
                     return false;
        } catch (CRLException ce) {
            return false;
        }
        return true;
    }

    /**
     * Returns a hashcode value for this CRL entry from its
     * encoded form.
     *
     * @return the hashcode value.
     */
    public int hashCode() {
        int     retval = 0;
        try {
            byte[] entryData = this.getEncoded();
            for (int i = 1; i < entryData.length; i++)
                 retval += entryData[i] * i;

        } catch (CRLException ce) {
            return(retval);
        }
        return(retval);
    }

    /**
     * Returns the ASN.1 DER-encoded form of this CRL Entry,
     * that is the inner SEQUENCE.
     *
     * @return the encoded form of this certificate
     * @throws    CRLException if an encoding error occurs.
     */
    public abstract byte[] getEncoded() throws CRLException;

    /**
     * Gets the serial number from this X509CRLEntry,
     * the <em>userCertificate</em>.
     *
     * @return the serial number.
     */
    public abstract BigInteger getSerialNumber();

    /**
     * Get the issuer of the X509Certificate described by this entry. If
     * the certificate issuer is also the CRL issuer, this method returns
     * null.
     *
     * <p>This method is used with indirect CRLs. The default implementation
     * always returns null. Subclasses that wish to support indirect CRLs
     * should override it.
     *
     * @return the issuer of the X509Certificate described by this entry
     * or null if it is issued by the CRL issuer.
     *
     * @since 1.5
     */
    public X500Principal getCertificateIssuer() {
        return null;
    }

    /**
     * Gets the revocation date from this X509CRLEntry,
     * the <em>revocationDate</em>.
     *
     * @return the revocation date.
     */
    public abstract Date getRevocationDate();

    /**
     * Returns true if this CRL entry has extensions.
     *
     * @return true if this entry has extensions, false otherwise.
     */
    public abstract boolean hasExtensions();

    /**
     * Returns a string representation of this CRL entry.
     *
     * @return a string representation of this CRL entry.
     */
    public abstract String toString();

    /**
     * Returns the reason the certificate has been revoked, as specified
     * in the Reason Code extension of this CRL entry.
     *
     * @return the reason the certificate has been revoked, or
     *    {@code null} if this CRL entry does not have
     *    a Reason Code extension
     * @since 1.7
     */
    public CRLReason getRevocationReason() {
        if (!hasExtensions()) {
            return null;
        }
        return X509CRLEntryImpl.getRevocationReason(this);
    }
}
