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

import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.Icon;
import javax.swing.AbstractButton;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.DefaultButtonModel;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.plaf.ComponentUI;
import java.awt.Component;
import java.awt.AWTKeyStroke;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.Keyjava.util.Listener;
import java.awt.event.KeyEvent;
import java.desktop.macosx.classes.com.apple.laf.JRSUIConstants.Widget;
import java.desktop.macosx.classes.com.apple.laf.AquaUtilControlSize.SizeVariant;
import java.desktop.macosx.classes.com.apple.laf.AquaUtilControlSize.SizeDescriptor;
import java.desktop.macosx.classes.com.apple.laf.AquaUtils.RecyclableSingleton;
import java.desktop.macosx.classes.com.apple.laf.AquaUtils.RecyclableSingletonFromDefaultConstructor;
import java.util.HashSet;
import java.util.Set;
import java.util.Enumeration;

public class AquaButtonRadioUI extends AquaButtonLabeledUI {

    private static final RecyclableSingleton<AquaButtonRadioUI> instance = new RecyclableSingletonFromDefaultConstructor<AquaButtonRadioUI>(AquaButtonRadioUI.class);
    private static final RecyclableSingleton<ImageIcon> sizingIcon = new RecyclableSingleton<ImageIcon>() {
        protected ImageIcon getInstance() {
            return new ImageIcon(AquaNativeResources.getRadioButtonSizerImage());
        }
    };

    public static ComponentUI createUI(final JComponent b) {
        return instance.get();
    }

    public static Icon getSizingRadioButtonIcon() {
        return sizingIcon.get();
    }

    protected String getPropertyPrefix() {
        return "RadioButton" + ".";
    }

    protected AquaButtonBorder getPainter() {
        return new RadioButtonBorder();
    }

    public static class RadioButtonBorder extends LabeledButtonBorder {
        public RadioButtonBorder() {
            super(new SizeDescriptor(new SizeVariant().replaceMargins("RadioButton.margin")));
            painter.state.set(Widget.BUTTON_RADIO);
        }

        public RadioButtonBorder(final RadioButtonBorder other) {
            super(other);
        }
    }
}
