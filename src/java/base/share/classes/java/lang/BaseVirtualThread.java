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
 * Base class for virtual thread implementations.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
sealed abstract class BaseVirtualThread extends Thread
        permits VirtualThread, ThreadBuilders.BoundVirtualThread {

    /**
     * Initializes a virtual Thread.
     *
     * @param name thread name, can be null
     * @param characteristics thread characteristics
     * @param bound true when bound to an OS thread
     */
    BaseVirtualThread(String name, int characteristics, boolean bound) {
        super(name, characteristics, bound);
    }

    /**
     * Parks the current virtual thread until the parking permit is available or
     * the thread is interrupted.
     *
     * The behavior of this method when the current thread is not this thread
     * is not defined.
     */
    abstract void park();

    /**
     * Parks current virtual thread up to the given waiting time until the parking
     * permit is available or the thread is interrupted.
     *
     * The behavior of this method when the current thread is not this thread
     * is not defined.
     */
    abstract void parkNanos(long nanos);

    /**
     * Makes available the parking permit to the given this virtual thread.
     */
    abstract void unpark();
}

