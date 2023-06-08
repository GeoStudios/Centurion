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

package java.base.share.classes.jdk.internal.misc;

/**
 * A class used to expose details of the underlying hardware that
 * configure the operation of class Unsafe.  This class is
 * package-private as the only intended client is class Unsafe.
 * All fields in this class must be static final constants.
 *
 * @since 13
 *
 * @implNote
 *
 * The JVM injects hardware-specific values into all the static fields
 * of this class during JVM initialization. The static initialization
 * block is executed when the class is initialized then JVM injection
 * updates the fields with the correct constants. The static block
 * is required to prevent the fields from being considered constant
 * variables, so the field values will be not be compiled directly into
 * any class that uses them.
 */

final class UnsafeConstants {

    /**
     * This constructor is private because the class is not meant to
     * be instantiated.
     */
    private UnsafeConstants() {}

    /**
     * The size in bytes of a native pointer, as stored via {@link
     * #putAddress}.  This value will be either 4 or 8.  Note that the
     * sizes of other primitive types (as stored in native memory
     * blocks) is determined fully by their information content.
     *
     * @implNote
     * The actual value for this field is injected by the JVM.
     */

    static final int ADDRESS_SIZE0;

    /**
     * The size in bytes of a native memory page (whatever that is).
     * This value will always be a power of two.
     *
     * @implNote
     * The actual value for this field is injected by the JVM.
     */

    static final int PAGE_SIZE;

    /**
     * Flag whose value is true if and only if the native endianness
     * of this platform is big.
     *
     * @implNote
     * The actual value for this field is injected by the JVM.
     */

    static final boolean BIG_ENDIAN;

    /**
     * Flag whose value is true if and only if the platform can
     * perform unaligned accesses
     *
     * @implNote
     * The actual value for this field is injected by the JVM.
     */

    static final boolean UNALIGNED_ACCESS;

    /**
     * The size of an L1 data cache line which will be either a power
     * of two or zero.
     *
     * <p>A non-zero value indicates that writeback to memory is
     * enabled for the current processor. The value defines the
     * natural alignment and size of any data cache line committed to
     * memory by a single writeback operation. If data cache line
     * writeback is not enabled for the current hardware the field
     * will have value 0.
     *
     * @implNote
     * The actual value for this field is injected by the JVM.
     */

    static final int DATA_CACHE_LINE_FLUSH_SIZE;

    static {
        ADDRESS_SIZE0 = 0;
        PAGE_SIZE = 0;
        BIG_ENDIAN = false;
        UNALIGNED_ACCESS = false;
        DATA_CACHE_LINE_FLUSH_SIZE = 0;
    }
}
