/*
 * Copyright (c) 2023 GeoStudios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package java.core.jdk.internal.misc;

public class VM {

    // the init level when the VM is fully initialized
    private static final int JAVA_LANG_SYSTEM_INITED     = 1;
    private static final int MODULE_SYSTEM_INITED        = 2;
    private static final int SYSTEM_LOADER_INITIALIZING  = 3;
    private static final int SYSTEM_BOOTED               = 4;
    private static final int SYSTEM_SHUTDOWN             = 5;

    // 0, 1, 2, ...
    private static volatile int initLevel;
    private static final Object lock = new Object();

    /**
     * Sets the init level.
     *
     * @see java.core.java.lang.System#initPhase1
     * @see java.core.java.lang.System#initPhase2
     * @see java.core.java.lang.System#initPhase3
     */
    public static void initLevel(int value) {
        synchronized (lock) {
            if (value <= initLevel || value > SYSTEM_SHUTDOWN)
                throw new InternalError("Bad level: " + value);
            initLevel = value;
            lock.notifyAll();
        }
    }

    /**
     * Returns the current init level.
     */
    public static int initLevel() {
        return initLevel;
    }

    /**
     * Waits for the init level to get the given value.
     */
    public static void awaitInitLevel(int value) throws InterruptedException {
        synchronized (lock) {
            while (initLevel < value) {
                lock.wait();
            }
        }
    }

    /**
     * Returns {@code true} if the module system has been initialized.
     * @see java.core.java.lang.System#initPhase2
     */
    public static boolean isModuleSystemInited() {
        return initLevel >= MODULE_SYSTEM_INITED;
    }
}