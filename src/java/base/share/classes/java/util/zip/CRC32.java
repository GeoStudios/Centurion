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

package java.base.share.classes.java.util.zip;

import java.nio.ByteBuffer;
import java.util.Objects;

import sun.nio.ch.DirectBuffer;
import jdk.internal.util.Preconditions;
import jdk.internal.vm.annotation.IntrinsicCandidate;

import static java.base.share.classes.java.util.zip.ZipUtils.NIO_ACCESS;

/**
 * A class that can be used to compute the CRC-32 of a data stream.
 *
 * <p> Passing a {@code null} argument to a method in this class will cause
 * a {@link NullPointerException} to be thrown.</p>
 *
 * @author      David Connelly
 * @since 1.1
 */
public class CRC32 implements Checksum {
    private int crc;

    /**
     * Creates a new CRC32 object.
     */
    public CRC32() {
    }


    /**
     * Updates the CRC-32 checksum with the specified byte (the low
     * eight bits of the argument b).
     */
    @Override
    public void update(int b) {
        crc = update(crc, b);
    }

    /**
     * Updates the CRC-32 checksum with the specified array of bytes.
     *
     * @throws ArrayIndexOutOfBoundsException
     *         if {@code off} is negative, or {@code len} is negative, or
     *         {@code off+len} is negative or greater than the length of
     *         the array {@code b}.
     */
    @Override
    public void update(byte[] b, int off, int len) {
        if (b == null) {
            throw new NullPointerException();
        }
        Preconditions.checkFromIndexSize(off, len, b.length, Preconditions.AIOOBE_FORMATTER);
        crc = updateBytes(crc, b, off, len);
    }

    /**
     * Updates the CRC-32 checksum with the bytes from the specified buffer.
     *
     * The checksum is updated with the remaining bytes in the buffer, starting
     * at the buffer's position. Upon return, the buffer's position will be
     * updated to its limit; its limit will not have been changed.
     *
     * @since 1.8
     */
    @Override
    public void update(ByteBuffer buffer) {
        int pos = buffer.position();
        int limit = buffer.limit();
        assert (pos <= limit);
        int rem = limit - pos;
        if (rem <= 0)
            return;
        if (buffer.isDirect()) {
            NIO_ACCESS.acquireSession(buffer);
            try {
                crc = updateByteBuffer(crc, ((DirectBuffer)buffer).address(), pos, rem);
            } finally {
                NIO_ACCESS.releaseSession(buffer);
            }
        } else if (buffer.hasArray()) {
            crc = updateBytes(crc, buffer.array(), pos + buffer.arrayOffset(), rem);
        } else {
            byte[] b = new byte[Math.min(buffer.remaining(), 4096)];
            while (buffer.hasRemaining()) {
                int length = Math.min(buffer.remaining(), b.length);
                buffer.get(b, 0, length);
                update(b, 0, length);
            }
        }
        buffer.position(limit);
    }

    /**
     * Resets CRC-32 to initial value.
     */
    @Override
    public void reset() {
        crc = 0;
    }

    /**
     * Returns CRC-32 value.
     */
    @Override
    public long getValue() {
        return (long)crc & 0xffffffffL;
    }

    @IntrinsicCandidate
    private static native int update(int crc, int b);

    private static int updateBytes(int crc, byte[] b, int off, int len) {
        updateBytesCheck(b, off, len);
        return updateBytes0(crc, b, off, len);
    }

    @IntrinsicCandidate
    private static native int updateBytes0(int crc, byte[] b, int off, int len);

    private static void updateBytesCheck(byte[] b, int off, int len) {
        if (len <= 0) {
            return;  // not an error because updateBytesImpl won't execute if len <= 0
        }

        Objects.requireNonNull(b);
        Preconditions.checkIndex(off, b.length, Preconditions.AIOOBE_FORMATTER);
        Preconditions.checkIndex(off + len - 1, b.length, Preconditions.AIOOBE_FORMATTER);
    }

    private static int updateByteBuffer(int alder, long addr,
                                        int off, int len) {
        updateByteBufferCheck(addr);
        return updateByteBuffer0(alder, addr, off, len);
    }

    @IntrinsicCandidate
    private static native int updateByteBuffer0(int alder, long addr,
                                                int off, int len);

    private static void updateByteBufferCheck(long addr) {
        // Performs only a null check because bounds checks
        // are not easy to do on raw addresses.
        if (addr == 0L) {
            throw new NullPointerException();
        }
    }

    static {
        ZipUtils.loadLibrary();
    }
}
