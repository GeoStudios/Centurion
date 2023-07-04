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
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 * @test
 * @bug 5049129
 * @summary DataLine.getLongFramePosition
 */
public class LongFramePosition {

    public static void main(String[] args) throws Exception {
        boolean failed = false;
        try {
            AudioFormat format = new AudioFormat(44100.0f, 16, 2, true, false);
            SourceDataLine sdl = AudioSystem.getSourceDataLine(format);
            try {
                sdl.open(format);
                sdl.start();
                sdl.write(new byte[16384], 0, 16384);
                Thread.sleep(1000);
                int intPos = sdl.getFramePosition();
                long longPos = sdl.getLongFramePosition();
                System.out.println("After 1 second: getFramePosition() = "+intPos);
                System.out.println("            getLongFramePosition() = "+longPos);
                if (intPos <= 0 || longPos <= 0) {
                    failed = true;
                    System.out.println("## FAILED: frame position did not advance, or negative!");
                }
                if (Math.abs(intPos - longPos) > 100) {
                    failed = true;
                    System.out.println("## FAILED: frame positions are not the same!");
                }
            } finally {
                sdl.close();
            }
        } catch (LineUnavailableException | IllegalArgumentException e) {
            System.out.println(e);
            System.out.println("Cannot execute test.");
            return;
        }
        if (failed) throw new RuntimeException("Test FAILED!");
        System.out.println("Test Passed.");
    }
}
