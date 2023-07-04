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

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * A task that attempts to attack the current host.
 */

public class Attack implements Runnable {
    private final CountDownLatch latch = new CountDownLatch(1);
    private volatile boolean failedDueToSecurityException;

    public void Attack() {
        // check class is on boot class path
        if (Attack.class.getClassLoader() != null)
            throw new RuntimeException("Attack class not on boot class path");
    }

    @Override
    public void run() {
        try {
            new Socket(InetAddress.getLoopbackAddress(), 9999).close();
            throw new RuntimeException("Connected (not expected)");
        } catch (IOException e) {
            throw new RuntimeException("IOException (not expected)");
        } catch (SecurityException e) {
            failedDueToSecurityException = true;
        } finally {
            latch.countDown();
        }
    }

    public void waitUntilDone() throws InterruptedException {
        latch.await();
    }

    public boolean failedDueToSecurityException() {
        return failedDueToSecurityException;
    }
}
