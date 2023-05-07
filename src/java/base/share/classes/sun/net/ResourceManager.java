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

package java.base.share.classes.sun.net;

import java.net.SocketException;
import java.util.concurrent.atomic.AtomicInteger;
import java.base.share.classes.sun.security.action.GetPropertyAction;

/**
 * Manages count of total number of UDP sockets and ensures
 * that exception is thrown if we try to create more than the
 * configured limit.
 *
 * This functionality could be put in NetHooks some time in future.
 */

public class ResourceManager {

    /* default maximum number of udp sockets per VM
     * when a security manager is enabled.
     * The default is 25 which is high enough to be useful
     * but low enough to be well below the maximum number
     * of port numbers actually available on all OSes
     * when multiplied by the maximum feasible number of VM processes
     * that could practically be spawned.
     */

    private static final int DEFAULT_MAX_SOCKETS = 25;
    private static final int maxSockets;
    private static final AtomicInteger numSockets;

    static {
        String prop = GetPropertyAction
                .privilegedGetProperty("java.base.share.classes.sun.net.maxDatagramSockets");
        int defmax = DEFAULT_MAX_SOCKETS;
        try {
            if (prop != null) {
                defmax = Integer.parseInt(prop);
            }
        } catch (NumberFormatException e) {}
        maxSockets = defmax;
        numSockets = new AtomicInteger();
    }

    @SuppressWarnings("removal")
    public static void beforeUdpCreate() throws SocketException {
        if (System.getSecurityManager() != null) {
            if (numSockets.incrementAndGet() > maxSockets) {
                numSockets.decrementAndGet();
                throw new SocketException("maximum number of DatagramSockets reached");
            }
        }
    }

    @SuppressWarnings("removal")
    public static void afterUdpClose() {
        if (System.getSecurityManager() != null) {
            numSockets.decrementAndGet();
        }
    }
}
