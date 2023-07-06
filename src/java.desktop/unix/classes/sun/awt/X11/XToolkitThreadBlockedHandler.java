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

package java.desktop.unix.classes.sun.awt.X11;

import java.desktop.unix.classes.sun.awt.datatransfer.ToolkitThreadBlockedHandler;

final class XToolkitThreadBlockedHandler implements
                                 ToolkitThreadBlockedHandler {
    private static final ToolkitThreadBlockedHandler priveleged_lock;
    static {
        priveleged_lock = new XToolkitThreadBlockedHandler();
    }
    private static final XToolkit tk = (XToolkit)java.awt.Toolkit.getDefaultToolkit();

    private XToolkitThreadBlockedHandler() {}
    static ToolkitThreadBlockedHandler getToolkitThreadBlockedHandler() {
        return priveleged_lock;
    }
    public void lock() {
        XToolkit.awtLock();
    }
    public void unlock() {
        XToolkit.awtUnlock();
    }
    public void enter() {
        tk.run(XToolkit.SECONDARY_LOOP);
    }
    public void exit() {
        XlibWrapper.ExitSecondaryLoop();
    }
}
