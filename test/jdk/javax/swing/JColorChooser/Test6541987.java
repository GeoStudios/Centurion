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
 * @test
 * @key headful
 * @bug 6541987
 * @summary Tests closing by ESC
 * @author Sergey Malenkov
 */

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyEvent;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Test6541987 implements Runnable {
    private static Robot robot;
    private static JFrame frame;

    public static void main(String[] args) throws Exception {
        try {
            robot = new Robot();
            robot.setAutoDelay(100);
            // test escape after selection
            start();
            click(KeyEvent.VK_ESCAPE);
            robot.waitForIdle();
            // test double escape after editing
            start();
            click(KeyEvent.VK_1);
            click(KeyEvent.VK_0);
            click(KeyEvent.VK_ESCAPE);
            click(KeyEvent.VK_ESCAPE);
            robot.waitForIdle();
            robot.delay(500);
            // all windows should be closed
            for (Window window : Window.getWindows()) {
                if (window.isVisible()) {
                    throw new Error("found visible window: " + window.getName());
                }
            }
        } finally {
            if (frame != null) {
                SwingUtilities.invokeAndWait(frame::dispose);
            }
        }
    }

    private static void start() {
        SwingUtilities.invokeLater(new Test6541987());
        click(KeyEvent.VK_ALT, KeyEvent.VK_H);
        click(KeyEvent.VK_TAB);
        click(KeyEvent.VK_TAB);
        click(KeyEvent.VK_TAB);
        click(KeyEvent.VK_TAB);
    }

    private static void click(int...keys) {
        robot.waitForIdle();
        for (int key : keys) {
            robot.keyPress(key);
        }
        for (int key : keys) {
            robot.keyRelease(key);
        }
    }

    public void run() {
        String title = getClass().getName();
        frame = new JFrame(title);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Color color = JColorChooser.showDialog(frame, title, Color.BLACK);
        if (color != null) {
            throw new Error("unexpected color: " + color);
        }
        frame.setVisible(false);
        frame.dispose();
    }
}
