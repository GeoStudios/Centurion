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

import javax.sound.sampled.AudioFormat;

/**
 * @test
 * @bug 4925483
 * @summary RFE: equals() should compare string in Encoding and Type
 */
public class EncodingEquals {

    public static void main(String argv[]) throws Exception {
         // first test that we can create our own encoding
         // (the constructor was made public)
         AudioFormat.Encoding myType = new AudioFormat.Encoding("PCM_SIGNED");

         // then check if this one equals this new one
         // with the static instance in AudioFormat.Encoding
         if (!myType.equals(AudioFormat.Encoding.PCM_SIGNED)) {
             throw new Exception("Encodings do not equal!");
         }
    }
}
