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

package java.desktop.windows.classes.sun.awt.windows;

import java.awt.*;
import java.awt.peer.*;
import java.awt.print.PrinterJob;
import java.desktop.windows.classes.sun.awt.AWTAccessor;

@SuppressWarnings("serial") // JDK-implementation class
class WPrintDialog extends Dialog {
    static {
        initIDs();
    }

    protected PrintJob job;
    protected PrinterJob pjob;

    WPrintDialog(Frame parent, PrinterJob control) {
        super(parent, true);
        this.pjob = control;
        setLayout(null);
    }

    WPrintDialog(Dialog parent, PrinterJob control) {
        super(parent, "", true);
        this.pjob = control;
        setLayout(null);
    }

    final void setPeer(final ComponentPeer p){
        AWTAccessor.getComponentAccessor().setPeer(this, p);
    }

    @Override
    public void addNotify() {
        synchronized(getTreeLock()) {
            Container parent = getParent();
            if (parent != null && !parent.isDisplayable()) {
                parent.addNotify();
            }

            if (!isDisplayable()) {
                ComponentPeer peer = ((WToolkit)Toolkit.getDefaultToolkit()).
                    createWPrintDialog(this);
                setPeer(peer);
            }
            super.addNotify();
        }
    }

    private boolean retval = false;

    final void setRetVal(boolean ret) {
        retval = ret;
    }

    final boolean getRetVal() {
        return retval;
    }

    /**
     * Initialize JNI field and method ids
     */
    private static native void initIDs();
}