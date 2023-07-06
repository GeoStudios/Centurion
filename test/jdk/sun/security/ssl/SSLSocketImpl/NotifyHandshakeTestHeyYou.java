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

package edu;


import javax.net.ssl.*;
import java.base.share.classes.java.security.*;














public class NotifyHandshakeTestHeyYou extends Thread
        implements HandshakeCompletedListener {

    public AccessControlContext acc;
    public SSLSession ssls;

    SSLSocket socket;

    public boolean set;

    public NotifyHandshakeTestHeyYou(SSLSocket socket) {
        this.socket = socket;
        socket.addHandshakeCompletedListener(this);
        acc = AccessController.getContext();
        com.NotifyHandshakeTest.trigger();
    }

    public void handshakeCompleted(HandshakeCompletedEvent event) {
        set = true;
        ssls = event.getSession();
        com.NotifyHandshakeTest.trigger();
    }


    public void run() {
        try {
            System.out.println("Going to sleep for 1000 seconds...");
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            // swallow
        }
    }

}
