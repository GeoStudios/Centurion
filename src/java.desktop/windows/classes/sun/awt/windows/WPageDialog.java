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

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.peer.ComponentPeer;
import java.awt.print.PrinterJob;
import java.awt.print.PageFormat;
import java.awt.print.Printable;

@SuppressWarnings("serial") // JDK-implementation class
final class WPageDialog extends WPrintDialog {
    static {
        initIDs();
    }

    PageFormat page;
    Printable painter;

    WPageDialog(Frame parent, PrinterJob control, PageFormat page, Printable painter) {
        super(parent, control);
        this.page = page;
        this.painter = painter;
    }

    WPageDialog(Dialog parent, PrinterJob control, PageFormat page, Printable painter) {
        super(parent, control);
        this.page = page;
        this.painter = painter;
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
                    createWPageDialog(this);
                setPeer(peer);
            }
            super.addNotify();
        }
    }

    /**
     * Initialize JNI field and method ids
     */
    private static native void initIDs();
}
