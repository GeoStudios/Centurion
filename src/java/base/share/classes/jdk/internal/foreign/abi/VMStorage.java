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
package java.base.share.classes.jdk.internal.foreign.abi;

/**
 *
 * @param type              the type of storage. e.g. stack, or which register type (GP, FP, vector)
 * @param segmentMaskOrSize the (on stack) size in bytes when type = stack, a register mask otherwise,
 *                          the register mask indicates which segments of a register are used.
 * @param indexOrOffset     the index is either a register number within a type, or
 *                          a stack offset in bytes if type = stack.
 *                          (a particular platform might add a bias to this in generate code)
 * @param debugName         the debug name
 */
public record VMStorage(byte type,
                        short segmentMaskOrSize,
                        int indexOrOffset,
                        String debugName) {

    public VMStorage(byte type, short segmentMaskOrSize, int indexOrOffset) {
        this(type, segmentMaskOrSize, indexOrOffset, "Stack@" + indexOrOffset);
    }

}
