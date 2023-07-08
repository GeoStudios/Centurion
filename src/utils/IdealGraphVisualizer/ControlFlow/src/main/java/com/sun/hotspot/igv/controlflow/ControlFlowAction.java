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

package utils.IdealGraphVisualizer.ControlFlow.src.main.java.com.sun.hotspot.igv.controlflow;


import utils.IdealGraphVisualizer.ControlFlow.src.main.java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;















/**
 *
 */
public class ControlFlowAction extends AbstractAction {

    public ControlFlowAction() {
        super(NbBundle.getMessage(ControlFlowAction.class, "CTL_ControlFlowAction"));
    }

    public void actionPerformed(ActionEvent evt) {
        TopComponent win = ControlFlowTopComponent.findInstance();
        win.open();
        win.requestActive();
    }
}
