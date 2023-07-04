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

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;

/**
 * @test
 * @bug 5001943
 * @summary Sequencer.startRecording throws unexpected NPE
 */
public class SeqStartRecording {

    public static void main(String argv[]) {
        Sequencer seq = null;
        try {
            seq = MidiSystem.getSequencer();
            seq.open();
        } catch (final MidiUnavailableException ignored) {
            // the test is not applicable
            return;
        }
        try {
            seq.startRecording();
            System.out.println("Test passed.");
        } catch (NullPointerException npe) {
            System.out.println("Caught NPE: "+npe);
            npe.printStackTrace();
            throw new RuntimeException("Test FAILED!");
        } catch (Exception e) {
            System.out.println("Unexpected Exception: "+e);
            e.printStackTrace();
            System.out.println("Test NOT failed.");
        } finally {
            seq.close();
        }
    }
}
