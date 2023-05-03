/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.ssl;

/**
 * SSL/TLS record
 *
 * @since Java 2
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

interface SSLRecord extends Record {

    int    headerSize = 5;             // SSLv3 record header
    int    handshakeHeaderSize = 4;    // SSLv3 handshake header

    /*
     * The size of the header plus the max IV length
     */
    int    headerPlusMaxIVSize =
                                      headerSize        // header
                                    + maxIVLength;      // iv

    /*
     * The maximum size that may be increased when translating plaintext to
     * ciphertext fragment.
     */
    int    maxPlaintextPlusSize =
                                      headerSize        // header
                                    + maxIVLength       // iv
                                    + maxMacSize        // MAC or AEAD tag
                                    + maxPadding;       // block cipher padding

    /*
     * SSL has a maximum record size.  It's header, (compressed) data,
     * padding, and a trailer for the message authentication information (MAC
     * for block and stream ciphers, and message authentication tag for AEAD
     * ciphers).
     *
     * Some compression algorithms have rare cases where they expand the data.
     * As we don't support compression at this time, leave that out.
     */
    int    maxRecordSize =
                                      headerPlusMaxIVSize   // header + iv
                                    + maxDataSize           // data
                                    + maxPadding            // padding
                                    + maxMacSize;           // MAC or AEAD tag

    /*
     * The maximum large record size.
     *
     * Some SSL/TLS implementations support large fragment up to 2^15 bytes,
     * such as Microsoft. We support large incoming fragments.
     *
     * The maximum large record size is defined as maxRecordSize plus 2^14,
     * this is the amount OpenSSL is using.
     */
    int    maxLargeRecordSize =
                maxRecordSize   // Max size with a conforming implementation
              + maxDataSize;    // extra 2^14 bytes for large data packets.

    /*
     * We may need to send this SSL v2 "No Cipher" message back, if we
     * are faced with an SSLv2 "hello" that's not saying "I talk v3".
     * It's the only one documented in the V2 spec as a fatal error.
     */
    byte[] v2NoCipher = {
        (byte)0x80, (byte)0x03, // unpadded 3 byte record
        (byte)0x00,             // ... error message
        (byte)0x00, (byte)0x01  // ... NO_CIPHER error
    };
}
