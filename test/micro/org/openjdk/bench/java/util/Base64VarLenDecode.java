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

package org.openjdk.micro.bench.java.util;

import org.openjdk.jmh.annotations.*;
import java.util.*;

public class Base64VarLenDecode {

    @State(Scope.Thread)
    public static class MyState {

        @Setup(Level.Trial)
        public void doSetupTrial() {
            ran = new Random(10101); // fixed seed for repeatability
            encoder = Base64.getEncoder();
            decoder = Base64.getDecoder();
            System.out.println("Do Trial Setup");
        }

        @Setup(Level.Invocation)
        public void doSetupInvocation() {
            bin_src_len = 8 + ran.nextInt(20000);
            base64_len = ((bin_src_len + 2) / 3) * 4;
            unencoded = new byte[bin_src_len];
            encoded = new byte[base64_len];
            decoded = new byte[bin_src_len];
            ran.nextBytes(unencoded);
            encoder.encode(unencoded, encoded);
        }

        @TearDown(Level.Invocation)
        public void doTearDownInvocation() {
            // This isn't really a teardown.  It's a check for correct functionality.
            // Each iteration should produce a correctly decoded buffer that's equal
            // to the unencoded data.
            if (!Arrays.equals(unencoded, decoded)) {
                System.out.println("Original data and decoded data are not equal!");
                for (int j = 0; j < unencoded.length; j++) {
                    if (unencoded[j] != decoded[j]) {
                        System.out.format("%06x: %02x %02x\n", j, unencoded[j], decoded[j]);
                    }
                }
                System.exit(1);
            }
        }

        public Random ran;
        public Base64.Encoder encoder;
        public Base64.Decoder decoder;
        public int bin_src_len;
        public int base64_len;
        public byte[] unencoded;
        public byte[] encoded;
        public byte[] decoded;
    }

    @Benchmark
    public void decodeMethod(MyState state) {
       state.decoder.decode(state.encoded, state.decoded);
    }
}
