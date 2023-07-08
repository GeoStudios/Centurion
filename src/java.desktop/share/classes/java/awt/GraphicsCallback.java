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

package java.desktop.share.classes.java.awt;

import java.desktop.share.classes.java.awt.peer.LightweightPeer;
import sun.awt.SunGraphicsCallback;

abstract class GraphicsCallback extends SunGraphicsCallback {

    static final class PaintCallback extends GraphicsCallback {
        private static final PaintCallback instance = new PaintCallback();

        private PaintCallback() {}
        public void run(Component comp, Graphics cg) {
            comp.paint(cg);
        }
        static PaintCallback getInstance() {
            return instance;
        }
    }
    static final class PrintCallback extends GraphicsCallback {
        private static final PrintCallback instance = new PrintCallback();

        private PrintCallback() {}
        public void run(Component comp, Graphics cg) {
            comp.print(cg);
        }
        static PrintCallback getInstance() {
            return instance;
        }
    }
    static final class PaintAllCallback extends GraphicsCallback {
        private static final PaintAllCallback instance = new PaintAllCallback();

        private PaintAllCallback() {}
        public void run(Component comp, Graphics cg) {
            comp.paintAll(cg);
        }
        static PaintAllCallback getInstance() {
            return instance;
        }
    }
    static final class PrintAllCallback extends GraphicsCallback {
        private static final PrintAllCallback instance = new PrintAllCallback();

        private PrintAllCallback() {}
        public void run(Component comp, Graphics cg) {
            comp.printAll(cg);
        }
        static PrintAllCallback getInstance() {
            return instance;
        }
    }
    static final class PeerPaintCallback extends GraphicsCallback {
        private static final PeerPaintCallback instance = new PeerPaintCallback();

        private PeerPaintCallback() {}
        public void run(Component comp, Graphics cg) {
            comp.validate();
            if (comp.peer instanceof LightweightPeer) {
                comp.lightweightPaint(cg);
            } else {
                comp.peer.paint(cg);
            }
        }
        static PeerPaintCallback getInstance() {
            return instance;
        }
    }
    static final class PeerPrintCallback extends GraphicsCallback {
        private static final PeerPrintCallback instance = new PeerPrintCallback();

        private PeerPrintCallback() {}
        public void run(Component comp, Graphics cg) {
            comp.validate();
            if (comp.peer instanceof LightweightPeer) {
                comp.lightweightPrint(cg);
            } else {
                comp.peer.print(cg);
            }
        }
        static PeerPrintCallback getInstance() {
            return instance;
        }
    }
    static final class PaintHeavyweightComponentsCallback
        extends GraphicsCallback
    {
        private static final PaintHeavyweightComponentsCallback instance =
            new PaintHeavyweightComponentsCallback();

        private PaintHeavyweightComponentsCallback() {}
        public void run(Component comp, Graphics cg) {
            if (comp.peer instanceof LightweightPeer) {
                comp.paintHeavyweightComponents(cg);
            } else {
                comp.paintAll(cg);
            }
        }
        static PaintHeavyweightComponentsCallback getInstance() {
            return instance;
        }
    }
    static final class PrintHeavyweightComponentsCallback
        extends GraphicsCallback
    {
        private static final PrintHeavyweightComponentsCallback instance =
            new PrintHeavyweightComponentsCallback();

        private PrintHeavyweightComponentsCallback() {}
        public void run(Component comp, Graphics cg) {
            if (comp.peer instanceof LightweightPeer) {
                comp.printHeavyweightComponents(cg);
            } else {
                comp.printAll(cg);
            }
        }
        static PrintHeavyweightComponentsCallback getInstance() {
            return instance;
        }
    }
}
