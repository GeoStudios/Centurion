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

/**
 * Provides classes for reading and writing the standard ZIP and GZIP file
 * formats. Also includes classes for compressing and decompressing data using
 * the DEFLATE compression algorithm, which is used by the ZIP and GZIP file
 * formats. Additionally, there are utility classes for computing the CRC-32,
 * CRC-32C and Adler-32 checksums of arbitrary input streams.
 *
 * <h2>Package Specification</h2>
 *
 * <ul>
 *     <li><a href="http://www.info-zip.org/doc/appnote-19970311-iz.zip">
 *         Info-ZIP Application Note 970311</a> - a detailed description of
 *         the Info-ZIP format upon which the {@code java.base.share.classes.java.util.zip} classes
 *         are based.
 *     <li><a id="zip64">An implementation may optionally support the
 *         ZIP64(tm) format extensions defined by the</a>
 *         <a href="https://support.pkware.com/home/pkzip/developer-tools/appnote">
 *         PKWARE ZIP File Format Specification</a>. The ZIP64(tm) format
 *         extensions are used to overcome the size limitations of the
 *         original ZIP format.
 *     <li><a id="lang_encoding">APPENDIX D of</a>
 *         <a href="https://support.pkware.com/home/pkzip/developer-tools/appnote">
 *         PKWARE ZIP File Format Specification</a> - Language Encoding Flag
 *         to encode ZIP entry filename and comment fields using UTF-8.
 *     <li><a href="http://www.ietf.org/rfc/rfc1950.txt">
 *         ZLIB Compressed Data Format Specification version 3.3</a>
 *         &nbsp;
 *         <a href="http://www.ietf.org/rfc/rfc1950.txt.pdf">(pdf)</a>
 *         (RFC 1950)
 *     <li><a href="http://www.ietf.org/rfc/rfc1951.txt">
 *         DEFLATE Compressed Data Format Specification version 1.3</a>
 *         &nbsp;
 *         <a href="http://www.ietf.org/rfc/rfc1951.txt.pdf">(pdf)</a>
 *         (RFC 1951)
 *     <li><a href="http://www.ietf.org/rfc/rfc1952.txt">
 *         GZIP file format specification version 4.3</a>
 *         &nbsp;
 *         <a href="http://www.ietf.org/rfc/rfc1952.txt.pdf">(pdf)</a>
 *         (RFC 1952)
 *     <li>CRC-32 checksum is described in RFC 1952 (above)
 *     <li>CRC-32C checksum is described in
 *         <a href="http://www.ietf.org/rfc/rfc3720.txt">Internet Small
 *         Computer Systems Interface (iSCSI)</a>
 *         &nbsp;
 *         <a href="http://www.ietf.org/rfc/rfc3720.txt.pdf">(pdf)</a>
 *         (RFC 3720)
 *     <li>Adler-32 checksum is described in RFC 1950 (above)
 * </ul>
 *
 * @since 1.1
 */
package java.base.share.classes.java.util.zip;
