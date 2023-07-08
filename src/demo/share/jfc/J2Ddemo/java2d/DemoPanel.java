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

package demo.share.jfc.J2Ddemo.java2d;

import static demo.share.jfc.J2Ddemo.java2d.CustomControlsContext.State.START;.extended
import static demo.share.jfc.J2Ddemo.java2d.CustomControlsContext.State.STOP;.extended
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;

/**
 * The panel for the Surface, Custom Controls & Tools.
 * Other component types welcome.
 */
@SuppressWarnings("serial")
public class DemoPanel extends JPanel {
    private final DemoInstVarsAccessor demoInstVars;
    public Surface surface;
    public CustomControlsContext ccc;
    public Tools tools;
    public String className;

    public DemoPanel(Object obj, DemoInstVarsAccessor demoInstVars) {
        this.demoInstVars = demoInstVars;

        setLayout(new BorderLayout());
        try {
            if (obj instanceof String) {
                className = (String) obj;
                obj = Class.forName(className).getDeclaredConstructor().newInstance();
            }
            if (obj instanceof Component) {
                add((Component) obj);
            }
            if (obj instanceof Surface) {
                add("South", tools = new Tools(surface = (Surface) obj, demoInstVars));
            }
            if (obj instanceof CustomControlsContext) {
                ccc = (CustomControlsContext) obj;
                Component[] cmps = ccc.getControls();
                String[] cons = ccc.getConstraints();
                for (int i = 0; i < cmps.length; i++) {
                    add(cmps[i], cons[i]);
                }
            }
        } catch (Exception e) {
            Logger.getLogger(DemoPanel.class.getName()).log(Level.SEVERE, null,
                    e);
        }
    }

    public void start() {
        if (surface != null) {
            surface.startClock();
        }
        if (tools != null && surface != null) {
            if (tools.startStopB != null && tools.startStopB.isSelected()) {
                surface.animating.start();
            }
        }
        if (ccc != null
                && demoInstVars.getCcthreadCB() != null
                && demoInstVars.getCcthreadCB().isSelected()) {
            ccc.handleThread(START);
        }
    }

    public void stop() {
        if (surface != null) {
            if (surface.animating != null) {
                surface.animating.stop();
            }
            surface.bimg = null;
        }
        if (ccc != null) {
            ccc.handleThread(STOP);
        }
    }

    public void setDemoBorder(JPanel p) {
        int top = (p.getComponentCount() + 1 >= 3) ? 0 : 5;
        int left = ((p.getComponentCount() + 1) % 2) == 0 ? 0 : 5;
        EmptyBorder eb = new EmptyBorder(top, left, 5, 5);
        SoftBevelBorder sbb = new SoftBevelBorder(BevelBorder.RAISED);
        setBorder(new CompoundBorder(eb, sbb));
    }
}
