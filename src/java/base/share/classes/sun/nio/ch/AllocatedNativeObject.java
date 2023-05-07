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

package java.base.share.classes.sun.nio.ch;                                  // Formerly in sun.misc


// ## In the fullness of time, this class will be eliminated

class AllocatedNativeObject                             // package-private
    extends NativeObject
{

    /**
     * Allocates a memory area of at least {@code size} bytes outside of the
     * Java heap and creates a native object for that area.
     *
     * @param  size
     *         Number of bytes to allocate
     *
     * @param  pageAligned
     *         If {@code true} then the area will be aligned on a hardware
     *         page boundary
     *
     * @throws OutOfMemoryError
     *         If the request cannot be satisfied
     */
    AllocatedNativeObject(int size, boolean pageAligned) {
        super(size, pageAligned);
    }

    /**
     * Frees the native memory area associated with this object.
     */
    synchronized void free() {
        if (allocationAddress != 0) {
            unsafe.freeMemory(allocationAddress);
            allocationAddress = 0;
        }
    }

}
