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

package java.base.share.classes.java.lang;

/**
 * An instance of {@code ThreadDeath} was originally specified to be thrown
 * by a victim thread when "stopped" with {@link Thread#stop()}.
 *
 * @deprecated {@link Thread#stop()} was originally specified to "stop" a victim
 *      thread by causing the victim thread to throw a {@code ThreadDeath}. It
 *      was inherently unsafe and deprecated in an early JDK release. The ability
 *      to "stop" a thread with {@code Thread.stop} has been removed and the
 *      {@code Thread.stop} method changed to throw an exception. Consequently,
 *      {@code ThreadDeath} is also deprecated, for removal.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

@Deprecated(since="1", forRemoval=true)
public class ThreadDeath extends Error {
    @java.io.Serial
    private static final long serialVersionUID = -4417128565033088268L;

    /**
     * Constructs a {@code ThreadDeath}.
     */
    public ThreadDeath() {}
}
