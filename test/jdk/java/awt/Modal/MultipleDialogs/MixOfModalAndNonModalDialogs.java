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

import java.awt.Dialog;
import java.awt.Frame;
import java.util.concurrent.CountDownLatch;

/**
 * @test
 * @key headful
 * @bug 8215200
 * @summary tests mixing of modal and non-modal dialogs
 */
public final class MixOfModalAndNonModalDialogs {

    public static void main(final String[] args) throws Exception {
        final Frame frame = new Frame();
        try {
            frame.setSize(300, 300);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // mix of modal, non-modal and invisible dialogs, all combinations
            for (int step = 0; step < 3; ++step) {
                for (int i = 0; i < 10; ++i) {
                    showDialog(frame, i);
                }
                showModalDialog(frame);
                for (int i = 0; i < 10; ++i) {
                    showDialog(frame, i);
                }
            }
        } finally {
            frame.dispose();
        }
    }

    private static void showDialog(final Frame frame, final int i) {
        final  Dialog visible = new Dialog(frame);
        visible.setLocationRelativeTo(null);
        visible.setVisible(true);
        if (i % 2 == 0) {
            new Dialog(frame);
        }
    }

    private static void showModalDialog(final Frame frame) throws Exception {
        final CountDownLatch go = new CountDownLatch(1);
        final Thread thread = new Thread(() -> {
            final Dialog modal = new Dialog(frame, "Modal Dialog", true);
            modal.pack();
            go.countDown();
            modal.setLocationRelativeTo(null);
            modal.setVisible(true);
        });
        thread.start();
        go.await();
    }
}
