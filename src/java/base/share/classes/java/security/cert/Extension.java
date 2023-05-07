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

import java.io.IOException;
import java.io.OutputStream;

/**
 * This interface represents an X.509 extension.
 *
 * <p>
 * Extensions provide a means of associating additional attributes with users
 * or public keys and for managing a certification hierarchy.  The extension
 * format also allows communities to define private extensions to carry
 * information unique to those communities.
 *
 * <p>
 * Each extension contains an object identifier, a criticality setting
 * indicating whether it is a critical or a non-critical extension, and
 * an ASN.1 DER-encoded value. Its ASN.1 definition is:
 *
 * <pre>
 *
 *     Extension ::= SEQUENCE {
 *         extnId        OBJECT IDENTIFIER,
 *         critical      BOOLEAN DEFAULT FALSE,
 *         extnValue     OCTET STRING
 *                 -- contains a DER encoding of a value
 *                 -- of the type registered for use with
 *                 -- the extnId object identifier value
 *     }
 *
 * </pre>
 *
 * <p>
 * This interface is designed to provide access to a single extension,
 * unlike {@link java.base.share.classes.java.security.cert.X509Extension} which is more suitable
 * for accessing a set of extensions.
 *
 * @since 1.7
 */
public interface Extension {

    /**
     * Gets the extensions's object identifier.
     *
     * @return the object identifier as a String
     */
    String getId();

    /**
     * Gets the extension's criticality setting.
     *
     * @return true if this is a critical extension.
     */
    boolean isCritical();

    /**
     * Gets the extensions's DER-encoded value. Note, this is the bytes
     * that are encoded as an OCTET STRING. It does not include the OCTET
     * STRING tag and length.
     *
     * @return a copy of the extension's value, or {@code null} if no
     *    extension value is present.
     */
    byte[] getValue();

    /**
     * Generates the extension's DER encoding and writes it to the output
     * stream.
     *
     * @param out the output stream
     * @throws    IOException on encoding or output error.
     * @throws    NullPointerException if {@code out} is {@code null}.
     */
    void encode(OutputStream out) throws IOException;
}
