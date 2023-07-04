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

/* @test
 * @bug 6524172
 * @summary Invoking wakeup on closed Selector can throw NPE if close resets interrupt status
 */

import java.io.IOException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.Selector;

public class WakeupAfterClose {

    public static void main(String[] args) throws Exception {
        final Selector sel = Selector.open();

        Runnable r = new Runnable() {
            public void run() {
                try {
                    sel.select();
                } catch (IOException x) {
                    x.printStackTrace();
                } catch (ClosedSelectorException y) {
                    System.err.println
                        ("Caught expected ClosedSelectorException");
                }
            }
        };

        // start thread to block in Selector
        Thread t = new Thread(r);
        t.start();

        // give thread time to start
        Thread.sleep(1000);

        // interrupt, close, and wakeup is the magic sequence to provoke the NPE
        t.interrupt();
        sel.close();
        sel.wakeup();
    }
}
