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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JLabel;

import sun.java2d.SunGraphicsEnvironment;

/**
 * @test
 * @bug 8041654
 * @key headful
 * @modules java.desktop/sun.java2d
 * @run main/othervm -Xmx80m DisplayListenerLeak
 */
public final class DisplayListenerLeak {

    private static JFrame frame;
    private volatile static boolean failed = false;

    private static void createAndShowGUI() {
        Thread.currentThread().setUncaughtExceptionHandler((t, e) -> {
            e.printStackTrace();
            failed = true;
        });
        frame = new JFrame();
        JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(600, 400));
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(final String[] args) throws Exception {
        GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        if (!(ge instanceof SunGraphicsEnvironment)) {
            return;
        }
        EventQueue.invokeAndWait(() -> createAndShowGUI());
        SunGraphicsEnvironment sge = (SunGraphicsEnvironment) ge;
        final long startTime = System.nanoTime();
        while (!failed) {
            if (System.nanoTime() - startTime > 60_000_000_000L) {
                break;
            }
            System.gc(); // clear all weak references
            EventQueue.invokeAndWait(() -> {
                frame.setSize(frame.getHeight(), frame.getWidth());
                frame.pack();
            });
            EventQueue.invokeAndWait(sge::displayChanged);
        }
        EventQueue.invokeAndWait(frame::dispose);
        if (failed) {
            throw new RuntimeException();
        }
    }
}
