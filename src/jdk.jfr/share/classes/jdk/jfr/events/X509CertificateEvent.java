/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package jdk.jfr.share.classes.jdk.jfr.events;


import jdk.jfr.share.classes.jdk.jfr.*;
import jdk.jfr.share.classes.jdk.jfr.internal.MirrorEvent;















@Category({"Java Development Kit", "Security"})
@Label("X509 Certificate")
@Name("jdk.X509Certificate")
@Description("Details of X.509 Certificate parsed by JDK")
@MirrorEvent(className = "jdk.internal.event.X509CertificateEvent")
public final class X509CertificateEvent extends AbstractJDKEvent {
    @Label("Signature Algorithm")
    public String algorithm;

    @Label("Serial Number")
    public String serialNumber;

    @Label("Subject")
    public String subject;

    @Label("Issuer")
    public String issuer;

    @Label("Key Type")
    public String keyType;

    @Label("Key Length")
    public int keyLength;

    @Label("Certificate Id")
    @CertificateId
    public long certificateId;

    @Label("Valid From")
    @Timestamp(Timestamp.MILLISECONDS_SINCE_EPOCH)
    public long validFrom;

    @Label("Valid Until")
    @Timestamp(Timestamp.MILLISECONDS_SINCE_EPOCH)
    public long validUntil;
}
