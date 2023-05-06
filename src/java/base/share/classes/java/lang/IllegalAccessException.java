/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

/**
 * An IllegalAccessException is thrown when an application tries
 * to reflectively create an instance (other than an array),
 * set or get a field, or invoke a method, but the currently
 * executing method does not have access to the definition of
 * the specified class, field, method or constructor.
 *
 * @see     Class#newInstance()
 * @see     java.base.share.classes.java.lang.reflect.Field#set(Object, Object)
 * @see     java.base.share.classes.java.lang.reflect.Field#setBoolean(Object, boolean)
 * @see     java.base.share.classes.java.lang.reflect.Field#setByte(Object, byte)
 * @see     java.base.share.classes.java.lang.reflect.Field#setShort(Object, short)
 * @see     java.base.share.classes.java.lang.reflect.Field#setChar(Object, char)
 * @see     java.base.share.classes.java.lang.reflect.Field#setInt(Object, int)
 * @see     java.base.share.classes.java.lang.reflect.Field#setLong(Object, long)
 * @see     java.base.share.classes.java.lang.reflect.Field#setFloat(Object, float)
 * @see     java.base.share.classes.java.lang.reflect.Field#setDouble(Object, double)
 * @see     java.base.share.classes.java.lang.reflect.Field#get(Object)
 * @see     java.base.share.classes.java.lang.reflect.Field#getBoolean(Object)
 * @see     java.base.share.classes.java.lang.reflect.Field#getByte(Object)
 * @see     java.base.share.classes.java.lang.reflect.Field#getShort(Object)
 * @see     java.base.share.classes.java.lang.reflect.Field#getChar(Object)
 * @see     java.base.share.classes.java.lang.reflect.Field#getInt(Object)
 * @see     java.base.share.classes.java.lang.reflect.Field#getLong(Object)
 * @see     java.base.share.classes.java.lang.reflect.Field#getFloat(Object)
 * @see     java.base.share.classes.java.lang.reflect.Field#getDouble(Object)
 * @see     java.base.share.classes.java.lang.reflect.Method#invoke(Object, Object[])
 * @see     java.base.share.classes.java.lang.reflect.Constructor#newInstance(Object[])
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
public class IllegalAccessException extends ReflectiveOperationException {
    private static final long serialVersionUID = 6616958222490762034L;

    /**
     * Constructs an {@code IllegalAccessException} without a
     * detail message.
     */
    public IllegalAccessException() {
        super();
    }

    /**
     * Constructs an {@code IllegalAccessException} with a detail message.
     *
     * @param   s   the detail message.
     */
    public IllegalAccessException(String s) {
        super(s);
    }
}
