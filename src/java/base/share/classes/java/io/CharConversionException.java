/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.java.io;

/**
 * Base class for character conversion exceptions.
 *
 * @since Java 2
 * @author Logan Abernathy
 * @edited 23/4/2023
 */
public class CharConversionException
    extends java.base.share.classes.java.io.IOException
{
    @java.base.share.classes.java.io.Serial
    private static final long serialVersionUID = -8680016352018427031L;

    /**
     * This provides no detailed message.
     */
    public CharConversionException() {
    }
    /**
     * This provides a detailed message.
     *
     * @param s the detailed message associated with the exception.
     */
    public CharConversionException(String s) {
        super(s);
    }
}
