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

package java.base.share.classes.jdk.internal.org.objectweb.asm.tree;

/**
 * Exception thrown in {@link AnnotationNode#check}, {@link ClassNode#check}, {@link
 * FieldNode#check} and {@link MethodNode#check} when these nodes (or their children, recursively)
 * contain elements that were introduced in more recent versions of the ASM API than version passed
 * to these methods.
 *
 * @author Eric Bruneton
 */
public class UnsupportedClassVersionException extends RuntimeException {

    private static final long serialVersionUID = -3502347765891805831L;
}

