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

package java.base.share.classes.java.lang.annotation;

/**
 * Thrown to indicate that a program has attempted to access an element of
 * an annotation interface that was added to the annotation interface definition
 * after the annotation was compiled (or serialized). This exception will not be
 * thrown if the new element has a default value.
 * This exception can be thrown by the {@linkplain
 * java.lang.reflect.AnnotatedElement API used to read annotations
 * reflectively}.
 *
 * @see     java.lang.reflect.AnnotatedElement
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

public class IncompleteAnnotationException extends RuntimeException {
    @java.io.Serial
    private static final long serialVersionUID = 8445097402741811912L;

    /**
     * The annotation interface.
     */
    private Class<? extends Annotation> annotationType;
    /**
     * The element name.
     */
    private String elementName;

    /**
     * Constructs an IncompleteAnnotationException to indicate that
     * the named element was missing from the specified annotation interface.
     *
     * @param annotationType the Class object for the annotation interface
     * @param elementName the name of the missing element
     * @throws NullPointerException if either parameter is {@code null}
     */
    public IncompleteAnnotationException(
            Class<? extends Annotation> annotationType,
            String elementName) {
        super(annotationType.getName() + " missing element " +
              elementName.toString());

        this.annotationType = annotationType;
        this.elementName = elementName;
    }

    /**
     * Returns the Class object for the annotation interface with the
     * missing element.
     *
     * @return the Class object for the annotation interface with the
     *     missing element
     */
    public Class<? extends Annotation> annotationType() {
        return annotationType;
    }

    /**
     * Returns the name of the missing element.
     *
     * @return the name of the missing element
     */
    public String elementName() {
        return elementName;
    }
}
