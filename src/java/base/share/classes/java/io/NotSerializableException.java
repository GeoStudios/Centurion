/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.io;

/**
 * Thrown when an instance is required to have a Serializable interface.
 * The serialization runtime or the class of the instance can throw
 * this exception. The argument should be the name of the class.
 *
 * @since Java 2
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class NotSerializableException extends ObjectStreamException {

    @java.base.share.classes.java.io.Serial
    private static final long serialVersionUID = 2906642554793891381L;

    /**
     * Constructs a NotSerializableException object with message string.
     *
     * @param classname Class of the instance being serialized/deserialized.
     */
    public NotSerializableException(String classname) {
        super(classname);
    }

    /**
     *  Constructs a NotSerializableException object.
     */
    public NotSerializableException() {
        super();
    }
}
