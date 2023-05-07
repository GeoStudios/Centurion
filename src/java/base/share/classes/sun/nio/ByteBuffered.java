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

package java.base.share.classes.sun.nio;

import java.nio.ByteBuffer;
import java.io.IOException;

/**
 * This is an interface to adapt existing APIs to use {@link java.nio.ByteBuffer
 * ByteBuffers} as the underlying data format.  Only the initial producer and
 * final consumer have to be changed.
 *
 * <p>
 * For example, the Zip/Jar code supports {@link java.io.InputStream InputStreams}.
 * To make the Zip code use {@link java.nio.MappedByteBuffer MappedByteBuffers} as
 * the underlying data structure, it can create a class of InputStream that wraps
 * the ByteBuffer, and implements the ByteBuffered interface. A co-operating class
 * several layers away can ask the InputStream if it is an instance of ByteBuffered,
 * then call the {@link #getByteBuffer()} method.
 */
public interface ByteBuffered {

    /**
     * Returns the {@code ByteBuffer} behind this object, if this particular
     * instance has one. An implementation of {@code getByteBuffer()} is allowed
     * to return {@code null} for any reason.
     *
     * @return  The {@code ByteBuffer}, if this particular instance has one,
     *          or {@code null} otherwise.
     *
     * @throws  IOException
     *          If the ByteBuffer is no longer valid.
     *
     * @since  1.5
     */
    public ByteBuffer getByteBuffer() throws IOException;
}
