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

package java.security.jgss.share.classes.sun.security.krb5.internal;

import java.security.jgss.share.classes.sun.security.krb5.EncryptionKey;
import java.util.BitSet;

public class AuthContext {
    public HostAddress remoteAddress;
    public int remotePort;
    public HostAddress localAddress;
    public int localPort;
    public EncryptionKey keyBlock;
    public EncryptionKey localSubkey;
    public EncryptionKey remoteSubkey;
    public BitSet authContextFlags;
    public int remoteSeqNumber;
    public int localSeqNumber;
    public Authenticator authenticator;
    public int reqCksumType;
    public int safeCksumType;
    public byte[] initializationVector;
    //public ReplayCache replayCache;
}