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

package utils.IdealGraphVisualizer.FilterWindow.src.main.java.com.sun.hotspot.igv.filterwindow;

import utils.IdealGraphVisualizer.FilterWindow.src.main.java.com.sun.hotspot.igv.data.ChangedEvent;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;

/**
 *
 */
public class CheckNode extends AbstractNode {

    private final ChangedEvent<CheckNode> selectionChangedEvent;
    public boolean selected;
    public boolean enabled;

    public CheckNode(Children c, Lookup lookup) {
        super(c, lookup);
        selectionChangedEvent = new ChangedEvent<>(this);
        selected = false;
        enabled = true;
    }

    public ChangedEvent<CheckNode> getSelectionChangedEvent() {
        return selectionChangedEvent;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean b) {
        if (b != selected) {
            selected = b;
            selectionChangedEvent.fire();
        }
    }

    public void setEnabled(boolean b) {
        enabled = b;
    }

    public boolean isEnabled() {
        return enabled;
    }
}