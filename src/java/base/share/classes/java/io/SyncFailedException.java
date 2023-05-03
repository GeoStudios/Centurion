/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.io;

/**
 * Signals that a sync operation has failed.
 *
 * @see     java.base.share.classes.java.io.FileDescriptor#sync
 * @see     java.base.share.classes.java.io.IOException
 * @since Java 2
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class SyncFailedException extends IOException {
    @java.base.share.classes.java.io.Serial
    private static final long serialVersionUID = -2353342684412443330L;

    /**
     * Constructs an SyncFailedException with a detail message.
     * A detail message is a String that describes this particular exception.
     *
     * @param desc  a String describing the exception.
     */
    public SyncFailedException(String desc) {
        super(desc);
    }
}
