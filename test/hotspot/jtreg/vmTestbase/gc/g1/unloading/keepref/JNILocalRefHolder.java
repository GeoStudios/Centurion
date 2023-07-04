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

import gc.g1.unloading.check.cleanup.CleanupAction;
import gc.g1.unloading.loading.LibLoader;

/**
 * This holder keeps reference through JNI local reference.
 */
public class JNILocalRefHolder implements RefHolder {

    static {
        //Force loading library
        new LibLoader().hashCode();
    }

    // We use this field to transfer object into native JNI call. Idea is to avoid transferring link through method
    // arguments.
    private Object objectToKeep;

    private native void holdWithJNILocalReference(Object syncObject);

    @Override
    public Object hold(Object object) {
        objectToKeep = object;
        final Object syncObject = new Object();
        Thread keepingThread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (syncObject) {
                    holdWithJNILocalReference(syncObject);
                }
            }
        });
        keepingThread.setDaemon(true);
        keepingThread.start();
        return new CleanupAction() {
            @Override
            public void cleanup() throws Exception {
                synchronized (syncObject) {
                    syncObject.notify();
                }
            }
        };
    }


}
