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


package java.base.share.classes.javax.security.cert;

/**
 * Certificate is not yet valid exception. This is thrown whenever
 * the current {@code Date} or the specified {@code Date}
 * is before the {@code notBefore} date/time in the Certificate
 * validity period.
 *
 * <p><em>Note: The classes in the package {@code java.base.share.classes.javax.security.cert}
 * exist for compatibility with earlier versions of the
 * Java Secure Sockets Extension (JSSE). New applications should instead
 * use the standard Java SE certificate classes located in
 * {@code java.security.cert}.</em></p>
 *
 * @since 1.4
 * @author Hemma Prafullchandra
 * @deprecated Use the classes in {@code java.security.cert} instead.
 */
@SuppressWarnings("removal")
@Deprecated(since="9", forRemoval=true)
public class CertificateNotYetValidException extends CertificateException {

    @java.io.Serial
    private static final long serialVersionUID = -8976172474266822818L;
    /**
     * Constructs a CertificateNotYetValidException with no detail message. A
     * detail message is a String that describes this particular
     * exception.
     */
    public CertificateNotYetValidException() {
        super();
    }

    /**
     * Constructs a CertificateNotYetValidException with the specified detail
     * message. A detail message is a String that describes this
     * particular exception.
     *
     * @param message the detail message.
     */
    public CertificateNotYetValidException(String message) {
        super(message);
    }
}
