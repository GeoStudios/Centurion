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

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 * @test
 * @bug 8147407
 */
public final class RecognizeWaveExtensible {

    private static byte[] data = {
            82, 73, 70, 70, 72, 0, 0, 0, 87, 65, 86, 69, 102, 109, 116, 32, 40,
            0, 0, 0, -2, -1, 1, 0, 64, 31, 0, 0, 0, 125, 0, 0, 4, 0, 32, 0, 22,
            0, 32, 0, 4, 0, 0, 0, 1, 0, 0, 0, 0, 0, 16, 0, -128, 0, 0, -86, 0,
            56, -101, 113, 102, 97, 99, 116, 4, 0, 0, 0, 0, 0, 0, 0, 100, 97,
            116, 97, 0, 0, 0, 0
    };

    public static void main(final String[] args) throws Exception {
        final InputStream is = new ByteArrayInputStream(data);
        final AudioFileFormat aff = AudioSystem.getAudioFileFormat(is);
        System.out.println("AudioFileFormat: " + aff);
        try (AudioInputStream ais = AudioSystem.getAudioInputStream(is)) {
            System.out.println("AudioFormat: " + ais.getFormat());
        }
        System.out.println("new String(data) = " + new String(data));
    }
}
