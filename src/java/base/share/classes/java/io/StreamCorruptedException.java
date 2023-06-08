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

package java.base.share.classes.java.io;

/**
 * Thrown when control information that was read from an object stream
 * violates internal consistency checks.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class StreamCorruptedException extends ObjectStreamException {

    @java.base.share.classes.java.io.Serial
    private static final long serialVersionUID = 8983558202217591746L;

    /**
     * Create a StreamCorruptedException and list a reason why thrown.
     *
     * @param reason  String describing the reason for the exception.
     */
    public StreamCorruptedException(String reason) {
        super(reason);
    }

    /**
     * Create a StreamCorruptedException and list no reason why thrown.
     */
    public StreamCorruptedException() {
        super();
    }
}
