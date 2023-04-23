/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.annotation;

/**
 * An instance of this class is stored in an AnnotationInvocationHandler's
 * "memberValues" map in lieu of a value for an annotation member that
 * cannot be returned due to some exceptional condition (typically some
 * form of illegal evolution of the annotation class).  The ExceptionProxy
 * instance describes the exception that the dynamic proxy should throw if
 * it is queried for this member.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public abstract class ExceptionProxy implements java.io.Serializable {
    @java.io.Serial
    private static final long serialVersionUID = 7241930048386631401L;
    protected abstract RuntimeException generateException();
}
