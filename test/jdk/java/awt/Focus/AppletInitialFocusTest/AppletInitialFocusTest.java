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

/*
  @test
  @key headful
  @bug 4041703 4096228 4025223 4260929
  @summary Ensures that appletviewer sets a reasonable default focus for an Applet on start
  @library ../../regtesthelpers
  @build   Util
  @run main AppletInitialFocusTest
*/

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Robot;
import test.java.awt.regtesthelpers.Util;

public class AppletInitialFocusTest extends Frame {

    Robot robot = Util.createRobot();
    Button button = new Button("Button");

    public static void main(final String[] args) throws Exception {
        AppletInitialFocusTest app = new AppletInitialFocusTest();
        app.init();
        app.start();
    }

    public void init() {
        setSize(200, 200);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        add(button);
        setVisible(true);
    }

    public void start() throws Exception {
        Thread thread = new Thread(new Runnable() {
                public void run() {
                    Util.waitTillShown(button);
                    robot.delay(1000); // delay the thread to let EDT to start dispatching focus events
                    Util.waitForIdle(robot);
                    if (!button.hasFocus()) {
                        throw new RuntimeException("Appletviewer doesn't set default focus correctly.");
                    }
                }
            });
        thread.start();
        thread.join();
    }
}
