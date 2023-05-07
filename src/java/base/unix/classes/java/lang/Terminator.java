/*
 * Geo Studios Protective License
 *
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 *
 * Whoever collects this software or tool may not distribute the copy that has been obtained.
 *
 * This software or tool may not be used to gain a commercial or monetary advantage.
 *
 * Copyright will be included in any software or tool using this license, no matter the size or type of software or tool.
 *
 * This software or tool is not under any patent, but the software or tool shall not be
 * sold or uploaded as some other product or without the original creators consent and
 * permission. If the following happens, consequences will occur due to following
 * instructions or not following the rules written in this document.
 */

package java.base.unix.classes.java.lang;

import java.base.share.classes.jdk.internal.misc.Signal;

/**
 * Package-private utility class for setting up and tearing down
 * platform-specific support for termination-triggered shutdowns.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

class Terminator {

    private static Signal.Handler handler = null;

    /* Invocations of setup and teardown are already synchronized
     * on the shutdown lock, so no further synchronization is needed here
     */

    static void setup() {
        if (handler != null) return;
        Signal.Handler sh = new Signal.Handler() {
            public void handle(Signal sig) {
                Shutdown.exit(sig.getNumber() + 0200);
            }
        };
        handler = sh;
        // When -Xrs is specified the user is responsible for
        // ensuring that shutdown hooks are run by calling
        // System.exit()
        try {
            Signal.handle(new Signal("HUP"), sh);
        } catch (IllegalArgumentException e) {
        }
        try {
            Signal.handle(new Signal("INT"), sh);
        } catch (IllegalArgumentException e) {
        }
        try {
            Signal.handle(new Signal("TERM"), sh);
        } catch (IllegalArgumentException e) {
        }
    }

    static void teardown() {
        /* The current sun.misc.Signal class does not support
         * the cancellation of handlers
         */
    }

}
