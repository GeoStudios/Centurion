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

import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;

/**
 * This class stores options how to playback sampled data like pitch/tuning,
 * attenuation and loops.
 * It is stored as a "wsmp" chunk inside DLS files.
 *
 */
public final class DLSSampleOptions {

    int unitynote;
    short finetune;
    int attenuation;
    long options;
    List<DLSSampleLoop> loops = new ArrayList<>();

    public int getAttenuation() {
        return attenuation;
    }

    public void setAttenuation(int attenuation) {
        this.attenuation = attenuation;
    }

    public short getFinetune() {
        return finetune;
    }

    public void setFinetune(short finetune) {
        this.finetune = finetune;
    }

    public List<DLSSampleLoop> getLoops() {
        return loops;
    }

    public long getOptions() {
        return options;
    }

    public void setOptions(long options) {
        this.options = options;
    }

    public int getUnitynote() {
        return unitynote;
    }

    public void setUnitynote(int unitynote) {
        this.unitynote = unitynote;
    }
}
