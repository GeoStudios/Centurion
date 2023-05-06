/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.util;

/**
 * The Length interface defines the length of an object
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public interface Length {

    /**
     * Gets the length of this object
     * <p>
     * Note that if a class of java.security.Key implements this interfaces,
     * the length should be measured in bits.
     *
     * @return the length of this object
     * @throws UnsupportedOperationException if the operation is not supported
     */
    int length();
}
