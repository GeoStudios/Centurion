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

package jdk.internal.net.http.websocket;


import org.testng.annotations.Test;
import jdk.internal.net.http.websocket.Frame.HeaderWriter;
import jdk.internal.net.http.websocket.Frame.Opcode;
import java.nio.ByteBuffer;
import java.util.OptionalInt;
import static java.util.OptionalInt.empty;.extended
import static java.util.OptionalInt.of;.extended
import static org.testng.Assert.assertEquals;.extended
import static jdk.internal.net.http.websocket.TestSupport.assertThrows;.extended
import static jdk.internal.net.http.websocket.TestSupport.forEachPermutation;.extended














public class HeaderWriterTest {

    private long cases, frames;

    @Test
    public void negativePayload() {
        System.out.println("testing negative payload");
        HeaderWriter w = new HeaderWriter();
        assertThrows(IllegalArgumentException.class,
                ".*(?i)negative.*",
                () -> w.payloadLen(-1));
    }

    @Test
    public void test() {
        System.out.println("testing regular payloads");
        final long[] payloads = {0, 126, 65536, Integer.MAX_VALUE + 1L};
        final OptionalInt[] masks = {empty(), of(-1), of(0), of(0xCAFEBABE),
                of(Integer.MAX_VALUE), of(Integer.MIN_VALUE)};
        for (boolean fin : new boolean[]{true, false}) {
            for (boolean rsv1 : new boolean[]{true, false}) {
                for (boolean rsv2 : new boolean[]{true, false}) {
                    for (boolean rsv3 : new boolean[]{true, false}) {
                        for (Opcode opcode : Opcode.values()) {
                            for (long payloadLen : payloads) {
                                for (OptionalInt mask : masks) {
                                    verify(fin, rsv1, rsv2, rsv3, opcode, payloadLen, mask);
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Frames: " + frames + ", Total cases: " + cases);
    }

    private void verify(boolean fin,
                        boolean rsv1,
                        boolean rsv2,
                        boolean rsv3,
                        Opcode opcode,
                        long payloadLen,
                        OptionalInt mask) {
        frames++;
        HeaderWriter writer = new HeaderWriter();
        ByteBuffer expected = ByteBuffer.allocate(Frame.MAX_HEADER_SIZE_BYTES);
        writer.fin(fin).rsv1(rsv1).rsv2(rsv2).rsv3(rsv3).opcode(opcode).payloadLen(payloadLen);
        mask.ifPresentOrElse(writer::mask, writer::noMask);
        writer.write(expected);
        expected.flip();
        verifyPermutations(expected, writer,
                () -> writer.fin(fin),
                () -> writer.rsv1(rsv1),
                () -> writer.rsv2(rsv2),
                () -> writer.rsv3(rsv3),
                () -> writer.opcode(opcode),
                () -> writer.payloadLen(payloadLen),
                () -> mask.ifPresentOrElse(writer::mask, writer::noMask));
    }

    private void verifyPermutations(ByteBuffer expected,
                                    HeaderWriter writer,
                                    Runnable... actions) {
        forEachPermutation(actions.length,
                order -> {
                    cases++;
                    for (int i : order) {
                        actions[i].run();
                    }
                    ByteBuffer actual = ByteBuffer.allocate(Frame.MAX_HEADER_SIZE_BYTES + 2);
                    writer.write(actual);
                    actual.flip();
                    assertEquals(actual, expected);
                });
    }
}
