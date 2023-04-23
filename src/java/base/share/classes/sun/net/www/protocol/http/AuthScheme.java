/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package sun.net.www.protocol.http;

/* Authentication schemes supported by the http implementation. New schemes, if
 * supported, should be defined here.
 */
public enum AuthScheme {
    BASIC,
    DIGEST,
    NTLM,
    NEGOTIATE,
    KERBEROS,
    UNKNOWN;
}

