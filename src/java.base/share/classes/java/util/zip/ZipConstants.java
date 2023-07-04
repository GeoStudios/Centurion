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

package java.util.zip;

/*
 * This interface defines the constants that are used by the classes
 * which manipulate ZIP files.
 *
 */
interface ZipConstants {

    /**
     * Local file (LOC) header signature.
     */
    long LOCSIG = 0x04034b50L;   // "PK\003\004"

    /**
     * Extra local (EXT) header signature.
     */
    long EXTSIG = 0x08074b50L;   // "PK\007\008"

    /**
     * Central directory (CEN) header signature.
     */
    long CENSIG = 0x02014b50L;   // "PK\001\002"

    /**
     * End of central directory (END) header signature.
     */
    long ENDSIG = 0x06054b50L;   // "PK\005\006"

    /**
     * Local file (LOC) header size in bytes (including signature).
     */
    int LOCHDR = 30;

    /**
     * Extra local (EXT) header size in bytes (including signature).
     */
    int EXTHDR = 16;

    /**
     * Central directory (CEN) header size in bytes (including signature).
     */
    int CENHDR = 46;

    /**
     * End of central directory (END) header size in bytes (including signature).
     */
    int ENDHDR = 22;

    /**
     * Local file (LOC) header version needed to extract field offset.
     */
    int LOCVER = 4;

    /**
     * Local file (LOC) header general purpose bit flag field offset.
     */
    int LOCFLG = 6;

    /**
     * Local file (LOC) header compression method field offset.
     */
    int LOCHOW = 8;

    /**
     * Local file (LOC) header modification time field offset.
     */
    int LOCTIM = 10;

    /**
     * Local file (LOC) header uncompressed file crc-32 value field offset.
     */
    int LOCCRC = 14;

    /**
     * Local file (LOC) header compressed size field offset.
     */
    int LOCSIZ = 18;

    /**
     * Local file (LOC) header uncompressed size field offset.
     */
    int LOCLEN = 22;

    /**
     * Local file (LOC) header filename length field offset.
     */
    int LOCNAM = 26;

    /**
     * Local file (LOC) header extra field length field offset.
     */
    int LOCEXT = 28;

    /**
     * Extra local (EXT) header uncompressed file crc-32 value field offset.
     */
    int EXTCRC = 4;

    /**
     * Extra local (EXT) header compressed size field offset.
     */
    int EXTSIZ = 8;

    /**
     * Extra local (EXT) header uncompressed size field offset.
     */
    int EXTLEN = 12;

    /**
     * Central directory (CEN) header version made by field offset.
     */
    int CENVEM = 4;

    /**
     * Central directory (CEN) header version needed to extract field offset.
     */
    int CENVER = 6;

    /**
     * Central directory (CEN) header encrypt, decrypt flags field offset.
     */
    int CENFLG = 8;

    /**
     * Central directory (CEN) header compression method field offset.
     */
    int CENHOW = 10;

    /**
     * Central directory (CEN) header modification time field offset.
     */
    int CENTIM = 12;

    /**
     * Central directory (CEN) header uncompressed file crc-32 value field offset.
     */
    int CENCRC = 16;

    /**
     * Central directory (CEN) header compressed size field offset.
     */
    int CENSIZ = 20;

    /**
     * Central directory (CEN) header uncompressed size field offset.
     */
    int CENLEN = 24;

    /**
     * Central directory (CEN) header filename length field offset.
     */
    int CENNAM = 28;

    /**
     * Central directory (CEN) header extra field length field offset.
     */
    int CENEXT = 30;

    /**
     * Central directory (CEN) header comment length field offset.
     */
    int CENCOM = 32;

    /**
     * Central directory (CEN) header disk number start field offset.
     */
    int CENDSK = 34;

    /**
     * Central directory (CEN) header internal file attributes field offset.
     */
    int CENATT = 36;

    /**
     * Central directory (CEN) header external file attributes field offset.
     */
    int CENATX = 38;

    /**
     * Central directory (CEN) header LOC header offset field offset.
     */
    int CENOFF = 42;

    /**
     * End of central directory (END) header number of entries on this disk field offset.
     */
    int ENDSUB = 8;

    /**
     * End of central directory (END) header total number of entries field offset.
     */
    int ENDTOT = 10;

    /**
     * End of central directory (END) header central directory size in bytes field offset.
     */
    int ENDSIZ = 12;

    /**
     * End of central directory (END) header offset for the first CEN header field offset.
     */
    int ENDOFF = 16;

    /**
     * End of central directory (END) header zip file comment length field offset.
     */
    int ENDCOM = 20;
}
