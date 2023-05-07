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

/*
 * This file is available under and governed by the GNU General Public
 * License version 2 only, as published by the Free Software Foundation.
 * However, the following notice accompanied the original version of this
 * file:
 *
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package java.base.share.classes.java.util.concurrent;

/**
 * Exception thrown when a blocking operation times out.  Blocking
 * operations for which a timeout is specified need a means to
 * indicate that the timeout has occurred. For many such operations it
 * is possible to return a value that indicates timeout; when that is
 * not possible or desirable then {@code TimeoutException} should be
 * declared and thrown.
 *
 * @since 1.5
 * @author Doug Lea
 */
public class TimeoutException extends Exception {
    private static final long serialVersionUID = 1900926677490660714L;

    /**
     * Constructs a {@code TimeoutException} with no specified detail
     * message.
     */
    public TimeoutException() {}

    /**
     * Constructs a {@code TimeoutException} with the specified detail
     * message.
     *
     * @param message the detail message
     */
    public TimeoutException(String message) {
        super(message);
    }
}
