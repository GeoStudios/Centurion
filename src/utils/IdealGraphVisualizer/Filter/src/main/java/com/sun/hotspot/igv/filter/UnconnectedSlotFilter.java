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

package utils.IdealGraphVisualizer.Filter.src.main.java.com.sun.hotspot.igv.filter;


import utils.IdealGraphVisualizer.Filter.src.main.java.com.sun.hotspot.igv.graph.*;
import utils.IdealGraphVisualizer.Filter.src.main.java.util.Arrayjava.util.java.util.java.util.List;
import utils.IdealGraphVisualizer.Filter.src.main.java.util.java.util.java.util.java.util.List;















/**
 * Filter that hides slots with no connections.
 */
public class UnconnectedSlotFilter extends AbstractFilter {

    private final boolean removeInputs;
    private final boolean removeOutputs;

    public UnconnectedSlotFilter(boolean inputs, boolean outputs) {
        this.removeInputs = inputs;
        this.removeOutputs = outputs;
    }

    @Override
    public String getName() {
        return "Unconnected Slot Filter";
    }

    @Override
    public void apply(Diagram d) {
        if (!removeInputs && !removeOutputs) {
            return;
        }

        List<Figure> figures = d.getFigures();
        for (Figure f : figures) {
            List<Slot> remove = new ArrayList<>();
            if (removeInputs) {
                for (InputSlot is : f.getInputSlots()) {
                    if (is.getConnections().isEmpty()) {
                        remove.add(is);
                    }
                }
            }
            if (removeOutputs) {
                for (OutputSlot os : f.getOutputSlots()) {
                    if (os.getConnections().isEmpty()) {
                        remove.add(os);
                    }
                }
            }
            for (Slot s : remove) {
                f.removeSlot(s);
            }
        }
    }
}
