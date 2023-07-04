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
package gc.g1.unloading.keepref;

import gc.g1.unloading.check.cleanup.UnusedThreadKiller;

/**
 *     This holder prevents class from being collected by keeping link in static field of running thread.
 *
 */
public class InThreadFieldHolder implements RefHolder {

    public static class AuxiliaryThread2 extends Thread {
        private Object ref;

        public AuxiliaryThread2(Object ref) {
            this.ref = ref;
        }

        synchronized public void finishThread() {
            notifyAll();
        }

        @Override
        synchronized public void run() {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Unexpected InterruptedException ", e);
            }
        }
    }

    @Override
    public Object hold(Object object) {
        Thread thread = new AuxiliaryThread2(object);
        thread.setDaemon(true);
        thread.start();
        return new UnusedThreadKiller(thread.getId());
    }

}
