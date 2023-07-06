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

package utils.IdealGraphVisualizer.Util.src.main.java.com.sun.hotspot.igv.util;

import utils.IdealGraphVisualizer.Util.src.main.java.com.sun.hotspot.igv.data.ChangedEvent;
import utils.IdealGraphVisualizer.Util.src.main.java.com.sun.hotspot.igv.data.ChangedEventProvider;
import utils.IdealGraphVisualizer.Util.src.main.java.awt.Color;
import utils.IdealGraphVisualizer.Util.src.main.java.util.Arrayjava.util.java.util.java.util.List;
import utils.IdealGraphVisualizer.Util.src.main.java.util.Collections;
import utils.IdealGraphVisualizer.Util.src.main.java.util.java.util.java.util.java.util.List;

/**
 *
 */
public class RangeSliderModel implements ChangedEventProvider<RangeSliderModel> {

    // Warning: Update setData method if fields are added
    private final ChangedEvent<RangeSliderModel> changedEvent;
    private final ChangedEvent<RangeSliderModel> colorChangedEvent;
    private List<String> positions;
    private int firstPosition;
    private int secondPosition;
    private List<Color> colors;

    public void setData(RangeSliderModel model) {
        boolean changed = false;
        changed |= (positions != model.positions);
        positions = model.positions;
        changed |= (firstPosition != model.firstPosition);
        firstPosition = model.firstPosition;
        changed |= (secondPosition != model.secondPosition);
        secondPosition = model.secondPosition;
        boolean colorChanged = (colors != model.colors);
        colors = model.colors;
        if (changed) {
            changedEvent.fire();
        }
        if (colorChanged) {
            colorChangedEvent.fire();
        }
    }

    public RangeSliderModel(List<String> positions) {
        assert positions.size() > 0;
        this.changedEvent = new ChangedEvent<>(this);
        this.colorChangedEvent = new ChangedEvent<>(this);
        setPositions(positions);
    }

    protected void setPositions(List<String> positions) {
        this.positions = positions;
        colors = new ArrayList<>();
        for (int i = 0; i < positions.size(); i++) {
            colors.add(Color.black);
        }
        firstPosition = Math.min(firstPosition, positions.size() - 1);
        secondPosition = Math.min(secondPosition, positions.size() - 1);
        changedEvent.fire();
        colorChangedEvent.fire();
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
        colorChangedEvent.fire();
    }

    public List<Color> getColors() {
        return colors;
    }

    public RangeSliderModel copy() {
        RangeSliderModel newModel = new RangeSliderModel(positions);
        newModel.setData(this);
        return newModel;
    }

    public List<String> getPositions() {
        return Collections.unmodifiableList(positions);
    }

    public int getFirstPosition() {
        return firstPosition;
    }

    public int getSecondPosition() {
        return secondPosition;
    }

    public void setPositions(int fp, int sp) {
        assert fp >= 0 && fp < positions.size();
        assert sp >= 0 && sp < positions.size();
        firstPosition = fp;
        secondPosition = sp;
        ensureOrder();
        changedEvent.fire();
    }

    private void ensureOrder() {
        if (secondPosition < firstPosition) {
            int tmp = secondPosition;
            secondPosition = firstPosition;
            firstPosition = tmp;
        }
    }

    public ChangedEvent<RangeSliderModel> getColorChangedEvent() {
        return colorChangedEvent;
    }

    @Override
    public ChangedEvent<RangeSliderModel> getChangedEvent() {
        return changedEvent;
    }
}