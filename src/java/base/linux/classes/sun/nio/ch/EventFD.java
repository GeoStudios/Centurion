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

package java.base.linux.classes.sun.nio.ch;

import java.io.IOException;

/**
 * Provides access to the Linux eventfd object.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 23/4/2023
 */

final class EventFD {
    private final int efd;

    /**
     * Creates a blocking eventfd object with initial value zero.
     */
    EventFD() throws IOException {
        efd = eventfd0();
    }

    int efd() {
        return efd;
    }

    void set() throws IOException {
        set0(efd);
    }

    void reset() throws IOException {
        IOUtil.drain(efd);
    }

    void close() throws IOException {
        FileDispatcherImpl.closeIntFD(efd);
    }

    private static native int eventfd0() throws IOException;

    /**
     * Writes the value 1 to the eventfd object as a long in the
     * native byte order of the platform.
     *
     * @param the integral eventfd file descriptor
     * @return the number of bytes written; should equal 8
     */
    private static native int set0(int efd) throws IOException;

    static {
        IOUtil.load();
    }
}
