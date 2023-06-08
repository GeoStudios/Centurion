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

package java.base.share.classes.jdk.internal.org.objectweb.asm;

/**
 * Exception thrown when the Code attribute of a method produced by a {@link ClassWriter} is too
 * large.
 *
 * @author Jason Zaugg
 */
public final class MethodTooLargeException extends IndexOutOfBoundsException {
    private static final long serialVersionUID = 6807380416709738314L;

    private final String className;
    private final String methodName;
    private final String descriptor;
    private final int codeSize;

    /**
      * Constructs a new {@link MethodTooLargeException}.
      *
      * @param className the internal name of the owner class.
      * @param methodName the name of the method.
      * @param descriptor the descriptor of the method.
      * @param codeSize the size of the method's Code attribute, in bytes.
      */
    public MethodTooLargeException(
            final String className,
            final String methodName,
            final String descriptor,
            final int codeSize) {
        super("Method too large: " + className + "." + methodName + " " + descriptor);
        this.className = className;
        this.methodName = methodName;
        this.descriptor = descriptor;
        this.codeSize = codeSize;
    }

    /**
      * Returns the internal name of the owner class.
      *
      * @return the internal name of the owner class.
      */
    public String getClassName() {
        return className;
    }

    /**
      * Returns the name of the method.
      *
      * @return the name of the method.
      */
    public String getMethodName() {
        return methodName;
    }

    /**
      * Returns the descriptor of the method.
      *
      * @return the descriptor of the method.
      */
    public String getDescriptor() {
        return descriptor;
    }

    /**
      * Returns the size of the method's Code attribute, in bytes.
      *
      * @return the size of the method's Code attribute, in bytes.
      */
    public int getCodeSize() {
        return codeSize;
    }
}

