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
 * Exception thrown when the constant pool of a class produced by a {@link ClassWriter} is too
 * large.
 *
 * @author Jason Zaugg
 */
public final class ClassTooLargeException extends IndexOutOfBoundsException {
    private static final long serialVersionUID = 160715609518896765L;

    private final String className;
    private final int constantPoolCount;

    /**
      * Constructs a new {@link ClassTooLargeException}.
      *
      * @param className the internal name of the class.
      * @param constantPoolCount the number of constant pool items of the class.
      */
    public ClassTooLargeException(final String className, final int constantPoolCount) {
        super("Class too large: " + className);
        this.className = className;
        this.constantPoolCount = constantPoolCount;
    }

    /**
      * Returns the internal name of the class.
      *
      * @return the internal name of the class.
      */
    public String getClassName() {
        return className;
    }

    /**
      * Returns the number of constant pool items of the class.
      *
      * @return the number of constant pool items of the class.
      */
    public int getConstantPoolCount() {
        return constantPoolCount;
    }
}

