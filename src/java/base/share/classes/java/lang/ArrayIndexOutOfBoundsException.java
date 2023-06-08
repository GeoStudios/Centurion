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
 * Thrown to indicate that an array has been accessed with an illegal index. The
 * index is either negative or greater than or equal to the size of the array.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

public class ArrayIndexOutOfBoundsException extends IndexOutOfBoundsException {
    @java.io.Serial
    private static final long serialVersionUID = -5116101128118950844L;

    /**
     * Constructs an {@code ArrayIndexOutOfBoundsException} with no detail
     * message.
     */
    public ArrayIndexOutOfBoundsException() {
        super();
    }

    /**
     * Constructs an {@code ArrayIndexOutOfBoundsException} class with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public ArrayIndexOutOfBoundsException(String s) {
        super(s);
    }

    /**
     * Constructs a new {@code ArrayIndexOutOfBoundsException} class with an
     * argument indicating the illegal index.
     *
     * <p>The index is included in this exception's detail message.  The
     * exact presentation format of the detail message is unspecified.
     *
     * @param index the illegal index.
     */
    public ArrayIndexOutOfBoundsException(int index) {
        super("Array index out of range: " + index);
    }
}
