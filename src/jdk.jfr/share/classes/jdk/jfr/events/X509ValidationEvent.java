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
@Label("X509 Validation")
@Name("jdk.X509Validation")
@Description("Serial numbers from X.509 Certificates forming chain of trust")
@MirrorEvent(className = "jdk.internal.event.X509ValidationEvent")
public final class X509ValidationEvent extends AbstractJDKEvent {
    @CertificateId
    @Label("Certificate Id")
    public long certificateId;

    @Label("Certificate Position")
    @Description("Certificate position in chain of trust, 1 = trust anchor")
    public int certificatePosition;

    @Label("Validation Counter")
    public long validationCounter;
}
