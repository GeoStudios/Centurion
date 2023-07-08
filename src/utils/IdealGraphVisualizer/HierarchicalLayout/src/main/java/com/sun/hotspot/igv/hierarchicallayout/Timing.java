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

package utils.IdealGraphVisualizer.HierarchicalLayout.src.main.java.com.sun.hotspot.igv.hierarchicallayout;

/**
 *
 */
public class Timing {

    private long lastValue;
    private long sum;
    private final String name;

    public Timing(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        long val = sum;
        if (lastValue != 0) {
            // Timer running
            long newValue = System.nanoTime();
            val += (newValue - lastValue);
        }
        return "Timing for " + name + " is: " + val / 1000000 + " ms";
    }

    public void print() {
        System.out.println(this);
    }

    public void start() {
        lastValue = System.nanoTime();
    }

    public void stop() {
        if (lastValue == 0) {
            throw new IllegalStateException("You must call start before stop");
        }
        long newValue = System.nanoTime();
        sum += newValue - lastValue;
        lastValue = 0;
    }
}
