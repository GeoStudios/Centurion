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
package java.net;

/**
 * Constants used by the SOCKS protocol implementation.
 */

interface SocksConsts {
    int PROTO_VERS4                = 4;
    int PROTO_VERS         = 5;
    int DEFAULT_PORT               = 1080;

    int NO_AUTH            = 0;
    int GSSAPI             = 1;
    int USER_PASSW         = 2;
    int NO_METHODS         = -1;

    int CONNECT            = 1;
    int BIND                       = 2;
    int UDP_ASSOC          = 3;

    int IPV4                       = 1;
    int DOMAIN_NAME                = 3;
    int IPV6                       = 4;

    int REQUEST_OK         = 0;
    int GENERAL_FAILURE    = 1;
    int NOT_ALLOWED                = 2;
    int NET_UNREACHABLE    = 3;
    int HOST_UNREACHABLE   = 4;
    int CONN_REFUSED               = 5;
    int TTL_EXPIRED                = 6;
    int CMD_NOT_SUPPORTED  = 7;
    int ADDR_TYPE_NOT_SUP  = 8;
}
