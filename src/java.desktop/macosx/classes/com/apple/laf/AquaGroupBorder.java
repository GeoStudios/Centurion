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

package java.desktop.macosx.classes.com.apple.laf;


import java.awt.*;
import javax.swing.border.Border;
import java.desktop.macosx.classes.com.apple.laf.JRSUIConstants.Widget;
import java.desktop.macosx.classes.com.apple.laf.AquaUtilControlSize.*;
import java.desktop.macosx.classes.com.apple.laf.AquaUtils.RecyclableSingletonFromDefaultConstructor;















public abstract class AquaGroupBorder extends AquaBorder {
    private static final RecyclableSingletonFromDefaultConstructor<? extends Border> tabbedPaneGroupBorder = new RecyclableSingletonFromDefaultConstructor<TabbedPane>(TabbedPane.class);
    public static Border getTabbedPaneGroupBorder() {
        return tabbedPaneGroupBorder.get();
    }

    private static final RecyclableSingletonFromDefaultConstructor<? extends Border> titleBorderGroupBorder = new RecyclableSingletonFromDefaultConstructor<Titled>(Titled.class);
    public static Border getBorderForTitledBorder() {
        return titleBorderGroupBorder.get();
    }

    private static final RecyclableSingletonFromDefaultConstructor<? extends Border> titlelessGroupBorder = new RecyclableSingletonFromDefaultConstructor<Titleless>(Titleless.class);
    public static Border getTitlelessBorder() {
        return titlelessGroupBorder.get();
    }

    protected AquaGroupBorder(final SizeVariant sizeVariant) {
        super(new SizeDescriptor(sizeVariant));
        painter.state.set(Widget.FRAME_GROUP_BOX);
    }

    public void paintBorder(final Component c, final Graphics g, int x, int y, int width, int height) {
        // sg2d.setColor(Color.MAGENTA);
        // sg2d.drawRect(x, y, width - 1, height - 1);

        final Insets internalInsets = sizeVariant.insets;
        x += internalInsets.left;
        y += internalInsets.top;
        width -= (internalInsets.left + internalInsets.right);
        height -= (internalInsets.top + internalInsets.bottom);

        painter.paint(g, c, x, y, width, height);
        // sg2d.setColor(Color.ORANGE);
        // sg2d.drawRect(x, y, width, height);
    }

    protected static class TabbedPane extends AquaGroupBorder {
        public TabbedPane() {
            super(new SizeVariant().alterMargins(8, 12, 8, 12).alterInsets(5, 5, 7, 5));
        }
    }

    protected static class Titled extends AquaGroupBorder {
        public Titled() {
            super(new SizeVariant().alterMargins(16, 20, 16, 20).alterInsets(16, 5, 4, 5));
        }
    }

    protected static class Titleless extends AquaGroupBorder {
        public Titleless() {
            super(new SizeVariant().alterMargins(8, 12, 8, 12).alterInsets(3, 5, 1, 5));
        }
    }
}
