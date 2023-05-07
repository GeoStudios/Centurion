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

package java.base.share.classes.sun.nio.ch;                                    // Formerly in sun.misc

import java.nio.ByteOrder;
import java.base.share.classes.jdk.internal.misc.Unsafe;


// ## In the fullness of time, this class will be eliminated

/**
 * Proxies for objects that reside in native memory.
 */

class NativeObject {                                    // package-private

    protected static final Unsafe unsafe = Unsafe.getUnsafe();

    // Native allocation address;
    // may be smaller than the base address due to page-size rounding
    //
    protected long allocationAddress;

    // Native base address
    //
    private final long address;

    /**
     * Creates a new native object that is based at the given native address.
     */
    NativeObject(long address) {
        this.allocationAddress = address;
        this.address = address;
    }

    /**
     * Creates a new native object allocated at the given native address but
     * whose base is at the additional offset.
     */
    NativeObject(long address, long offset) {
        this.allocationAddress = address;
        this.address = address + offset;
    }

    // Invoked only by AllocatedNativeObject
    //
    protected NativeObject(int size, boolean pageAligned) {
        if (!pageAligned) {
            this.allocationAddress = unsafe.allocateMemory(size);
            this.address = this.allocationAddress;
        } else {
            int ps = pageSize();
            long a = unsafe.allocateMemory(size + ps);
            this.allocationAddress = a;
            this.address = a + ps - (a & (ps - 1));
        }
    }

    /**
     * Returns the native base address of this native object.
     *
     * @return The native base address
     */
    long address() {
        return address;
    }

    long allocationAddress() {
        return allocationAddress;
    }

    /**
     * Creates a new native object starting at the given offset from the base
     * of this native object.
     *
     * @param  offset
     *         The offset from the base of this native object that is to be
     *         the base of the new native object
     *
     * @return The newly created native object
     */
    NativeObject subObject(int offset) {
        return new NativeObject(offset + address);
    }

    /**
     * Reads an address from this native object at the given offset and
     * constructs a native object using that address.
     *
     * @param  offset
     *         The offset of the address to be read.  Note that the size of an
     *         address is implementation-dependent.
     *
     * @return The native object created using the address read from the
     *         given offset
     */
    NativeObject getObject(int offset) {
        long newAddress = 0L;
        switch (addressSize()) {
            case 8:
                newAddress = unsafe.getLong(offset + address);
                break;
            case 4:
                newAddress = unsafe.getInt(offset + address) & 0x00000000FFFFFFFF;
                break;
            default:
                throw new InternalError("Address size not supported");
        }

        return new NativeObject(newAddress);
    }

    /**
     * Writes the base address of the given native object at the given offset
     * of this native object.
     *
     * @param  offset
     *         The offset at which the address is to be written.  Note that the
     *         size of an address is implementation-dependent.
     *
     * @param  ob
     *         The native object whose address is to be written
     */
    void putObject(int offset, NativeObject ob) {
        switch (addressSize()) {
            case 8:
                putLong(offset, ob.address);
                break;
            case 4:
                putInt(offset, (int)(ob.address & 0x00000000FFFFFFFF));
                break;
            default:
                throw new InternalError("Address size not supported");
        }
    }


    /* -- Value accessors: No range checking! -- */

    /**
     * Reads a byte starting at the given offset from base of this native
     * object.
     *
     * @param  offset
     *         The offset at which to read the byte
     *
     * @return The byte value read
     */
    final byte getByte(int offset) {
        return unsafe.getByte(offset + address);
    }

    /**
     * Writes a byte at the specified offset from this native object's
     * base address.
     *
     * @param  offset
     *         The offset at which to write the byte
     *
     * @param  value
     *         The byte value to be written
     */
    final void putByte(int offset, byte value) {
        unsafe.putByte(offset + address,  value);
    }

    /**
     * Reads a short starting at the given offset from base of this native
     * object.
     *
     * @param  offset
     *         The offset at which to read the short
     *
     * @return The short value read
     */
    final short getShort(int offset) {
        return unsafe.getShort(offset + address);
    }

    /**
     * Writes a short at the specified offset from this native object's
     * base address.
     *
     * @param  offset
     *         The offset at which to write the short
     *
     * @param  value
     *         The short value to be written
     */
    final void putShort(int offset, short value) {
        unsafe.putShort(offset + address,  value);
    }

    /**
     * Reads a char starting at the given offset from base of this native
     * object.
     *
     * @param  offset
     *         The offset at which to read the char
     *
     * @return The char value read
     */
    final char getChar(int offset) {
        return unsafe.getChar(offset + address);
    }

    /**
     * Writes a char at the specified offset from this native object's
     * base address.
     *
     * @param  offset
     *         The offset at which to write the char
     *
     * @param  value
     *         The char value to be written
     */
    final void putChar(int offset, char value) {
        unsafe.putChar(offset + address,  value);
    }

    /**
     * Reads an int starting at the given offset from base of this native
     * object.
     *
     * @param  offset
     *         The offset at which to read the int
     *
     * @return The int value read
     */
    final int getInt(int offset) {
        return unsafe.getInt(offset + address);
    }

    /**
     * Writes an int at the specified offset from this native object's
     * base address.
     *
     * @param  offset
     *         The offset at which to write the int
     *
     * @param  value
     *         The int value to be written
     */
    final void putInt(int offset, int value) {
        unsafe.putInt(offset + address, value);
    }

    /**
     * Reads a long starting at the given offset from base of this native
     * object.
     *
     * @param  offset
     *         The offset at which to read the long
     *
     * @return The long value read
     */
    final long getLong(int offset) {
        return unsafe.getLong(offset + address);
    }

    /**
     * Writes a long at the specified offset from this native object's
     * base address.
     *
     * @param  offset
     *         The offset at which to write the long
     *
     * @param  value
     *         The long value to be written
     */
    final void putLong(int offset, long value) {
        unsafe.putLong(offset + address, value);
    }

    /**
     * Reads a float starting at the given offset from base of this native
     * object.
     *
     * @param  offset
     *         The offset at which to read the float
     *
     * @return The float value read
     */
    final float getFloat(int offset) {
        return unsafe.getFloat(offset + address);
    }

    /**
     * Writes a float at the specified offset from this native object's
     * base address.
     *
     * @param  offset
     *         The offset at which to write the float
     *
     * @param  value
     *         The float value to be written
     */
    final void putFloat(int offset, float value) {
        unsafe.putFloat(offset + address, value);
    }

    /**
     * Reads a double starting at the given offset from base of this native
     * object.
     *
     * @param  offset
     *         The offset at which to read the double
     *
     * @return The double value read
     */
    final double getDouble(int offset) {
        return unsafe.getDouble(offset + address);
    }

    /**
     * Writes a double at the specified offset from this native object's
     * base address.
     *
     * @param  offset
     *         The offset at which to write the double
     *
     * @param  value
     *         The double value to be written
     */
    final void putDouble(int offset, double value) {
        unsafe.putDouble(offset + address, value);
    }

    /**
     * Returns the native architecture's address size in bytes.
     *
     * @return The address size of the native architecture
     */
    static int addressSize() {
        return unsafe.addressSize();
    }

    // Cache for byte order
    private static ByteOrder byteOrder = null;

    /**
     * Returns the byte order of the underlying hardware.
     *
     * @return  An instance of {@link java.nio.ByteOrder}
     */
    static ByteOrder byteOrder() {
        if (byteOrder != null)
            return byteOrder;
        long a = unsafe.allocateMemory(8);
        try {
            unsafe.putLong(a, 0x0102030405060708L);
            byte b = unsafe.getByte(a);
            switch (b) {
            case 0x01: byteOrder = ByteOrder.BIG_ENDIAN;     break;
            case 0x08: byteOrder = ByteOrder.LITTLE_ENDIAN;  break;
            default:
                assert false;
            }
        } finally {
            unsafe.freeMemory(a);
        }
        return byteOrder;
    }

    /**
     * Cache for page size.
     * Lazily initialized via a data race; safe because ints are atomic.
     */
    private static int pageSize = -1;

    /**
     * Returns the page size of the underlying hardware.
     *
     * @return  The page size, in bytes
     */
    static int pageSize() {
        int value = pageSize;
        if (value == -1)
            pageSize = value = unsafe.pageSize();
        return value;
    }

}
