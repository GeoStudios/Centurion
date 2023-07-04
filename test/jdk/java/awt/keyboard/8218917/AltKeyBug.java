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
 * @bug 8218917
 * @summary Tests whether sending an ALT_GRAPH key once, will result in the
 * system reporting only ALT_GRAPH even if an ALT was sent and vice versa.
 * @run main AltKeyBug
 */
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.Robot;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class AltKeyBug {
    private static JFrame f;
    private static boolean rightAltPressed = false;
    private static boolean throwException = false;
    private static String errorString;
    public static void main(String[] args) throws Exception {
        try {
            Robot robot = new Robot();
            robot.setAutoDelay(50);

            SwingUtilities.invokeAndWait(() -> {
                JTextField comp = new JTextField();
                comp.addKeyListener(new KeyListener() {
                    @Override public void keyTyped(KeyEvent e) {}
                    @Override public void keyPressed(KeyEvent e) {
                        System.out.println("ModEx : " +e.getModifiersEx());
                        System.out.println("Mod : " +e.getModifiers());
                        System.out.println("ALT_DOWN : " + e.isAltDown());
                        System.out.println("ALT_GR_DOWN: " + e.isAltGraphDown());
                        System.out.println("-----------");
                        if (rightAltPressed && !e.isAltGraphDown()) {
                            throwException = true;
                            errorString = "Right Alt press was sent but not received back.";
                        } else if (!rightAltPressed && e.isAltGraphDown()) {
                            throwException = true;
                            errorString = "Left Alt press was sent, but received Right Alt";
                        }
                    }
                    @Override public void keyReleased(KeyEvent e) {}
                });
                f = new JFrame();
                f.add(comp);
                f.setSize(100,100);
                f.setVisible(true);
            });

            for(int i = 0; i < 20; i++) {
                rightAltPressed = true;
                robot.keyPress(KeyEvent.VK_ALT_GRAPH);
                robot.keyRelease(KeyEvent.VK_ALT_GRAPH);

                robot.waitForIdle();

                if (throwException) {
                    throw new RuntimeException(errorString);
                }
                rightAltPressed = false;
                robot.keyPress(KeyEvent.VK_ALT);
                robot.keyRelease(KeyEvent.VK_ALT);

                robot.waitForIdle();

                if (throwException) {
                    throw new RuntimeException(errorString);
                }
            }
        } finally {
            SwingUtilities.invokeAndWait(()-> {
                if (f != null)
                    f.dispose();
            });
        }

        System.out.println("Test passed.");
    }
}
