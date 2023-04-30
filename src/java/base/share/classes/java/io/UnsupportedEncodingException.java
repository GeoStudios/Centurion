/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.java.io;

/**
 * The Character Encoding is not supported.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class UnsupportedEncodingException
    extends IOException
{
    @java.base.share.classes.java.io.Serial
    private static final long serialVersionUID = -4274276298326136670L;

    /**
     * Constructs an UnsupportedEncodingException without a detail message.
     */
    public UnsupportedEncodingException() {
        super();
    }

    /**
     * Constructs an UnsupportedEncodingException with a detail message.
     * @param s Describes the reason for the exception.
     */
    public UnsupportedEncodingException(String s) {
        super(s);
    }
}
