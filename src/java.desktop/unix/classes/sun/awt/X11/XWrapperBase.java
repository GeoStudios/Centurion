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

import java.desktop.unix.classes.sun.util.logging.PlatformLogger;

// This class serves as the base class for all the wrappers.

abstract class XWrapperBase {
    static final PlatformLogger log = PlatformLogger.getLogger("sun.awt.X11.wrappers");

    public String toString() {
        String ret = "";

        ret += getName() + " = " + getFieldsAsString();

        return ret;
    }

    String getFieldsAsString() {
        return "";
    }

    String getName() {
        return "XWrapperBase";
    }
    public void zero() {
        log.finest("Cleaning memory");
        if (getPData() != 0) {
            XlibWrapper.unsafe.setMemory(getPData(), getDataSize(), (byte)0);
        }
    }
    public abstract int getDataSize();
    String getWindow(long window) {
        XBaseWindow w = XToolkit.windowToXWindow(window);
        if (w == null) {
            return Long.toHexString(window);
        } else {
            return w.toString();
        }
    }
    public abstract long getPData();
    public XEvent clone() {
        long copy = XlibWrapper.unsafe.allocateMemory(getDataSize());
        XlibWrapper.unsafe.copyMemory(getPData(), copy, getDataSize());
        return new XEvent(copy);
    }
}