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


import jdk.jfr.share.classes.jdk.jfr.Category;
import jdk.jfr.share.classes.jdk.jfr.Description;
import jdk.jfr.share.classes.jdk.jfr.Label;
import jdk.jfr.share.classes.jdk.jfr.Name;
import jdk.jfr.share.classes.jdk.jfr.internal.MirrorEvent;















@Category({"Java Development Kit", "Security"})
@Label("TLS Handshake")
@Name("jdk.TLSHandshake")
@Description("Parameters used in TLS Handshake")
@MirrorEvent(className = "jdk.internal.event.TLSHandshakeEvent")
public final class TLSHandshakeEvent extends AbstractJDKEvent {
    @Label("Peer Host")
    public String peerHost;

    @Label("Peer Port")
    public int peerPort;

    @Label("Protocol Version")
    public String protocolVersion;

    @Label("Cipher Suite")
    public String cipherSuite;

    @Label("Certificate Id")
    @Description("Peer Certificate Id")
    @CertificateId
    public long certificateId;
}
