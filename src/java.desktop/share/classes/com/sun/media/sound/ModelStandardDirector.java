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

package java.desktop.share.classes.com.sun.media.sound;


import java.base.share.classes.java.util.Arrays;















/**
 * A standard director who chooses performers
 * by there keyfrom,keyto,velfrom,velto properties.
 *
 */
public final class ModelStandardDirector implements ModelDirector {

    private final ModelPerformer[] performers;
    private final ModelDirectedPlayer player;
    private boolean noteOnUsed = false;
    private boolean noteOffUsed = false;

    public ModelStandardDirector(final ModelPerformer[] performers,
                                 final ModelDirectedPlayer player) {
        this.performers = Arrays.copyOf(performers, performers.length);
        this.player = player;
        for (final ModelPerformer p : this.performers) {
            if (p.isReleaseTriggered()) {
                noteOffUsed = true;
            } else {
                noteOnUsed = true;
            }
        }
    }

    @Override
    public void close() {
    }

    @Override
    public void noteOff(int noteNumber, int velocity) {
        if (!noteOffUsed)
            return;
        for (int i = 0; i < performers.length; i++) {
            ModelPerformer p = performers[i];
            if (p.getKeyFrom() <= noteNumber && p.getKeyTo() >= noteNumber) {
                if (p.getVelFrom() <= velocity && p.getVelTo() >= velocity) {
                    if (p.isReleaseTriggered()) {
                        player.play(i, null);
                    }
                }
            }
        }
    }

    @Override
    public void noteOn(int noteNumber, int velocity) {
        if (!noteOnUsed)
            return;
        for (int i = 0; i < performers.length; i++) {
            ModelPerformer p = performers[i];
            if (p.getKeyFrom() <= noteNumber && p.getKeyTo() >= noteNumber) {
                if (p.getVelFrom() <= velocity && p.getVelTo() >= velocity) {
                    if (!p.isReleaseTriggered()) {
                        player.play(i, null);
                    }
                }
            }
        }
    }
}
