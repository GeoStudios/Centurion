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

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.desktop.macosx.classes.com.apple.laf.JRSUIConstants.*;
import java.desktop.macosx.classes.com.apple.laf.AquaUtilControlSize.*;
import java.desktop.macosx.classes.com.apple.laf.AquaUtils.*;

public class AquaButtonCheckBoxUI extends AquaButtonLabeledUI {
    private static final RecyclableSingleton<AquaButtonCheckBoxUI> instance = new RecyclableSingletonFromDefaultConstructor<AquaButtonCheckBoxUI>(AquaButtonCheckBoxUI.class);
    private static final RecyclableSingleton<ImageIcon> sizingIcon = new RecyclableSingleton<ImageIcon>() {
        protected ImageIcon getInstance() {
            return new ImageIcon(AquaNativeResources.getRadioButtonSizerImage());
        }
    };

    public static ComponentUI createUI(final JComponent b) {
        return instance.get();
    }

    public static Icon getSizingCheckBoxIcon() {
        return sizingIcon.get();
    }

    public String getPropertyPrefix() {
        return "CheckBox" + ".";
    }

    protected AquaButtonBorder getPainter() {
        return new CheckBoxButtonBorder();
    }

    public static class CheckBoxButtonBorder extends LabeledButtonBorder {
        public CheckBoxButtonBorder() {
            super(new SizeDescriptor(new SizeVariant().replaceMargins("CheckBox.margin")));
            painter.state.set(Widget.BUTTON_CHECK_BOX);
        }

        public CheckBoxButtonBorder(final CheckBoxButtonBorder sizeDescriptor) {
            super(sizeDescriptor);
        }
    }
}