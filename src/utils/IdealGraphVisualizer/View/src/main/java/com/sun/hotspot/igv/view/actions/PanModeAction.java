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

package utils.IdealGraphVisualizer.View.src.main.java.com.sun.hotspot.igv.view.actions;

import utils.IdealGraphVisualizer.View.src.main.java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import org.openide.util.ImageUtilities;

public class PanModeAction extends AbstractAction {

    private boolean state;

    public PanModeAction() {
        putValue(AbstractAction.SMALL_ICON, new ImageIcon(ImageUtilities.loadImage(iconResource())));
        putValue(SELECTED_KEY, false);
        putValue(Action.SHORT_DESCRIPTION, "Panning mode");
    }

    public boolean isSelected() {
        return (Boolean)getValue(SELECTED_KEY);
    }

    public void setSelected(boolean b) {
        if (isSelected() != b) {
            this.putValue(SELECTED_KEY, b);
        }
    }

    protected String iconResource() {
        return "com/sun/hotspot/igv/view/images/pan_mode.png";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
