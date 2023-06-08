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

package java.base.share.classes.java.lang.invoke;

/**
 * Base class for memory segment var handle view implementations.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */

abstract sealed class VarHandleSegmentViewBase extends VarHandle permits
        VarHandleSegmentAsBytes,
        VarHandleSegmentAsChars,
        VarHandleSegmentAsDoubles,
        VarHandleSegmentAsFloats,
        VarHandleSegmentAsInts,
        VarHandleSegmentAsLongs,
        VarHandleSegmentAsShorts {

    /** endianness **/
    final boolean be;

    /** access size (in bytes, computed from var handle carrier type) **/
    final long length;

    /** alignment constraint (in bytes, expressed as a bit mask) **/
    final long alignmentMask;

    VarHandleSegmentViewBase(VarForm form, boolean be, long length, long alignmentMask, boolean exact) {
        super(form, exact);
        this.be = be;
        this.length = length;
        this.alignmentMask = alignmentMask;
    }

    static IllegalArgumentException newIllegalArgumentExceptionForMisalignedAccess(long address) {
        return new IllegalArgumentException("Misaligned access at address: " + address);
    }
}
