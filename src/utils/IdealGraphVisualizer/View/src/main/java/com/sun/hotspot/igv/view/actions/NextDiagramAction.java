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

import utils.IdealGraphVisualizer.View.src.main.java.com.sun.hotspot.igv.data.Changedjava.util.Listener;
import utils.IdealGraphVisualizer.View.src.main.java.com.sun.hotspot.igv.util.ContextAction;
import utils.IdealGraphVisualizer.View.src.main.java.com.sun.hotspot.igv.view.DiagramViewModel;
import javax.swing.Action;
import javax.swing.ImageIcon;
import org.openide.util.*;

/**
 *
 */
public final class NextDiagramAction extends ContextAction<DiagramViewModel> implements ChangedListener<DiagramViewModel> {

    private DiagramViewModel model;

    public NextDiagramAction() {
        this(Utilities.actionsGlobalContext());
    }

    public NextDiagramAction(Lookup lookup) {
        putValue(Action.SHORT_DESCRIPTION, "Show next graph of current group");
        putValue(Action.SMALL_ICON, new ImageIcon(ImageUtilities.loadImage("com/sun/hotspot/igv/view/images/next_diagram.png")));
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(NextDiagramAction.class, "CTL_NextDiagramAction");
    }

    @Override
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    public Class<DiagramViewModel> contextClass() {
        return DiagramViewModel.class;
    }

    @Override
    public void performAction(DiagramViewModel model) {
        int fp = model.getFirstPosition();
        int sp = model.getSecondPosition();
        if (sp != model.getPositions().size() - 1) {
            int nfp = fp + 1;
            int nsp = sp + 1;
            model.setPositions(nfp, nsp);
        }
    }

    @Override
    public void update(DiagramViewModel model) {
        super.update(model);

        if (this.model != model) {
            if (this.model != null) {
                this.model.getDiagramChangedEvent().removeListener(this);
            }

            this.model = model;
            if (this.model != null) {
                this.model.getDiagramChangedEvent().addListener(this);
            }
        }
    }

    @Override
    public boolean isEnabled(DiagramViewModel model) {
        return model.getSecondPosition() != model.getPositions().size() - 1;
    }

    @Override
    public Action createContextAwareInstance(Lookup arg0) {
        return new NextDiagramAction(arg0);
    }

    @Override
    public void changed(DiagramViewModel source) {
        update(source);
    }
}