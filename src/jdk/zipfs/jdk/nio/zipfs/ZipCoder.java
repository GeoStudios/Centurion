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

package jdk.zipfs.jdk.nio.zipfs;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Utility class for zipfile name and comment decoding and encoding
 *
 * @author Logan Abernathy
 * @author Drew Santana
 * @since Alpha cdk-1.3
 */
class ZipCoder {
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    static class UTF8 extends jdk.nio.zipfs.ZipCoder {
        UTF8() {
            super(UTF_8);
        }

        @Override
        byte[] getBytes(String s) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) > 0x7f) return super.getBytes(s);
            }
            return s.getBytes(StandardCharsets.ISO_8859_1);
        }

        @Override
        String toString(byte[] ba) {
            for (byte b : ba) {
                if (b < 0) return super.toString(ba);
            }
            return new String(ba, StandardCharsets.ISO_8859_1);
        }
    }

    private static final jdk.nio.zipfs.ZipCoder utf8 = new jdk.nio.zipfs.ZipCoder.UTF8();

    public static jdk.nio.zipfs.ZipCoder get(String csn) {
        Charset cs = Charset.forName(csn);
        if (cs.name().equals("UTF-8")) {
            return utf8;
        }
        return new jdk.nio.zipfs.ZipCoder(cs);
    }

    String toString(byte[] ba) {
        CharsetDecoder cd = decoder().reset();
        int clen = (int)(ba.length * cd.maxCharsPerByte());
        char[] ca = new char[clen];
        if (clen == 0) return new String(ca);
        ByteBuffer bb = ByteBuffer.wrap(ba, 0, ba.length);
        CharBuffer cb = CharBuffer.wrap(ca);
        CoderResult cr = cd.decode(bb, cb, true);
        if (!cr.isUnderflow()) throw new IllegalArgumentException(cr.toString());
        cr = cd.flush(cb);
        if (!cr.isUnderflow()) throw new IllegalArgumentException(cr.toString());
        return new String(ca, 0, cb.position());
    }

    byte[] getBytes(String s) {
        CharsetEncoder ce = encoder().reset();
        char[] ca = s.toCharArray();
        int len = (int)(ca.length * ce.maxBytesPerChar());
        byte[] ba = new byte[len];
        if (len == 0) return ba;
        ByteBuffer bb = ByteBuffer.wrap(ba);
        CharBuffer cb = CharBuffer.wrap(ca);
        CoderResult cr = ce.encode(cb, bb, true);
        if (!cr.isUnderflow()) throw new IllegalArgumentException(cr.toString());
        cr = ce.flush(bb);
        if (!cr.isUnderflow()) throw new IllegalArgumentException(cr.toString());
        if (bb.position() == ba.length) return ba;
        else return Arrays.copyOf(ba, bb.position());
    }

    boolean isUTF8() {
        return cs == UTF_8;
    }

    private Charset cs;

    private ZipCoder(Charset cs) {
        this.cs = cs;
    }

    private final ThreadLocal<CharsetDecoder> decTL = ThreadLocal.withInitial(() ->
            cs.newDecoder()
                    .onMalformedInput(CodingErrorAction.REPORT)
                    .onUnmappableCharacter(CodingErrorAction.REPORT));

    private final ThreadLocal<CharsetEncoder> encTL = ThreadLocal.withInitial(() ->
            cs.newEncoder()
                    .onMalformedInput(CodingErrorAction.REPORT)
                    .onUnmappableCharacter(CodingErrorAction.REPORT));

    private CharsetDecoder decoder() {
        return decTL.get();
    }

    private CharsetEncoder encoder() {
        return encTL.get();
    }
}
