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

package java.desktop.share.classes.com.sun.java.swing.plaf.motif;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import java.desktop.share.classes.com.sun.awt.AppContext;

/**
 * MotifCheckBox implementation
 *
 */
public class MotifCheckBoxUI extends MotifRadioButtonUI {

    private static final Object MOTIF_CHECK_BOX_UI_KEY = new Object();

    private static final String propertyPrefix = "CheckBox" + ".";

    private boolean defaults_initialized = false;

    // ********************************
    //         Create PLAF
    // ********************************
    public static ComponentUI createUI(JComponent c) {
        AppContext appContext = AppContext.getAppContext();
        MotifCheckBoxUI motifCheckBoxUI =
                (MotifCheckBoxUI) appContext.get(MOTIF_CHECK_BOX_UI_KEY);
        if (motifCheckBoxUI == null) {
            motifCheckBoxUI = new MotifCheckBoxUI();
            appContext.put(MOTIF_CHECK_BOX_UI_KEY, motifCheckBoxUI);
        }
        return motifCheckBoxUI;
    }

    public String getPropertyPrefix() {
        return propertyPrefix;
    }

    // ********************************
    //          Defaults
    // ********************************
    public void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        if(!defaults_initialized) {
            icon = UIManager.getIcon(getPropertyPrefix() + "icon");
            defaults_initialized = true;
        }
    }

    protected void uninstallDefaults(AbstractButton b) {
        super.uninstallDefaults(b);
        defaults_initialized = false;
    }
}
