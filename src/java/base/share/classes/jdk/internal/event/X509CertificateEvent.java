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

package java.base.share.classes.jdk.internal.event;


/**
 * Event recording details of X.509 Certificate.
 */

public final class X509CertificateEvent extends Event {
    private static final X509CertificateEvent EVENT = new X509CertificateEvent();

    /**
     * Returns {@code true} if event is enabled, {@code false} otherwise.
     */
    public static boolean isTurnedOn() {
        return EVENT.isEnabled();
    }

    public String algorithm;
    public String serialNumber;
    public String subject;
    public String issuer;
    public String keyType;
    public int keyLength;
    public long certificateId;
    public long validFrom;
    public long validUntil;
}
