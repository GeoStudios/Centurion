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

/**
 * @test
 * @bug 4163126
 * @library /test/lib
 * @summary  test to see if timeout hangs
 * @run main/timeout=15 Timeout
 * @run main/othervm/timeout=15 -Djava.net.preferIPv4Stack=true Timeout
 */
import java.net.*;
import java.io.*;
import jdk.test.lib.net.IPSupport;

public class Timeout {
    public static void main(String[] args) throws Exception {
        IPSupport.throwSkippedExceptionIfNonOperational();

        boolean success = false;
        ServerSocket sock = new ServerSocket(0);
        try {
            sock.setSoTimeout(2);
            sock.accept();
        } catch (InterruptedIOException e) {
            success = true;
        } finally {
            sock.close();
        }
        if (!success)
            throw new RuntimeException("Socket timeout failure.");
    }
}
