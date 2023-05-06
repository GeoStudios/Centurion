/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.windows.classes.java.lang;

import java.base.share.classes.jdk.internal.misc.Signal;

/**
 * Package-private utility class for setting up and tearing down
 * platform-specific support for termination-triggered shutdowns.
 *
 * @since Alpha cdk-1.0
 * @author Logan Abernathy
 * @edited 18/4/2023
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
