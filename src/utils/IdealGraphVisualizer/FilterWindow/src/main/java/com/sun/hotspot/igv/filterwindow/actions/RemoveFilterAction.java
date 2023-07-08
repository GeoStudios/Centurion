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

package utils.IdealGraphVisualizer.FilterWindow.src.main.java.com.sun.hotspot.igv.filterwindow.actions;


import utils.IdealGraphVisualizer.FilterWindow.src.main.java.com.sun.hotspot.igv.filter.Filter;
import utils.IdealGraphVisualizer.FilterWindow.src.main.java.com.sun.hotspot.igv.filterwindow.FilterTopComponent;
import javax.swing.Action;
import javax.swing.JOptionPane;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.CookieAction;
import org.openide.windows.WindowManager;















/**
 *
 */
public final class RemoveFilterAction extends CookieAction {

    @Override
    protected void performAction(Node[] activatedNodes) {
        Object[] options = {"Yes",
            "No",
            "Cancel"
        };
        int n = JOptionPane.showOptionDialog(WindowManager.getDefault().getMainWindow(),
                "Do you really want to delete " + activatedNodes.length + " filter(s)?", "Delete Filters",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);

        if (n == JOptionPane.YES_OPTION) {
            for (int i = 0; i < activatedNodes.length; i++) {
                FilterTopComponent.findInstance().removeFilter(activatedNodes[i].getLookup().lookup(Filter.class));
            }
        }
    }

    @Override
    protected int mode() {
        return CookieAction.MODE_ALL;
    }

    @Override
    public String getName() {
        return NbBundle.getMessage(RemoveFilterAction.class, "CTL_RemoveFilterAction");
    }

    public RemoveFilterAction() {
        putValue(Action.SHORT_DESCRIPTION, "Remove selected filter");
    }

    @Override
    protected Class[] cookieClasses() {
        return new Class[]{
            Filter.class
        };
    }

    @Override
    protected void initialize() {
        super.initialize();
        putValue("noIconInMenu", Boolean.TRUE);
    }

    @Override
    protected String iconResource() {
        return "com/sun/hotspot/igv/filterwindow/images/minus.png";
    }

    @Override
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    protected boolean asynchronous() {
        return false;
    }
}
