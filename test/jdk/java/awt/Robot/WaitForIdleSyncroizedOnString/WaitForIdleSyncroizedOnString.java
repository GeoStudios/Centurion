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

import java.awt.Robot;
import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

/**
 * @test
 * @key headful
 * @bug 8166673
 */
public final class WaitForIdleSyncroizedOnString {

    private static final String WAIT_LOCK = "Wait Lock";

    private static volatile boolean passed = true;

    public static void main(final String[] args) throws Exception {
        CountDownLatch go = new CountDownLatch(1);
        Robot r = new Robot();
        SwingUtilities.invokeLater(() -> System.out.println("some work"));
        Thread t = new Thread(() -> {
            synchronized (WAIT_LOCK) {
                go.countDown();
                try {
                    Thread.sleep(30000);
                    passed = false;
                } catch (InterruptedException e) {
                    System.out.println("e = " + e);
                }
            }
        });
        t.start();
        go.await();
        r.waitForIdle();
        t.interrupt();
        if (!passed) {
            throw new RuntimeException("Test failed");
        }
    }
}
