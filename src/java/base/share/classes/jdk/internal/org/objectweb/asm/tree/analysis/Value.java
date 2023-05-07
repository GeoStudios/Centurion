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

package java.base.share.classes.jdk.internal.org.objectweb.asm.tree.analysis;

/**
 * An immutable symbolic value for the semantic interpretation of bytecode.
 *
 * @author Eric Bruneton
 */
public interface Value {

    /**
      * Returns the size of this value in 32 bits words. This size should be 1 for byte, boolean, char,
      * short, int, float, object and array types, and 2 for long and double.
      *
      * @return either 1 or 2.
      */
    int getSize();
}

