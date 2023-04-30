/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.io;

/**
 * Callback interface to allow validation of objects within a graph.
 * Allows an object to be called when a complete graph of objects has
 * been deserialized.
 *
 * @see     ObjectInputStream
 * @see     ObjectInputStream#registerValidation(java.base.share.classes.java.io.ObjectInputValidation, int)
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public interface ObjectInputValidation {
    /**
     * Validates the object.
     *
     * @throws    InvalidObjectException If the object cannot validate itself.
     */
    public void validateObject() throws InvalidObjectException;
}
