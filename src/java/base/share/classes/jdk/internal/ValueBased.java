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
package java.base.share.classes.jdk.internal;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * Indicates the API declaration in question is associated with a Value Based class.
 * References to <a href="../lang/doc-files/ValueBased.html">value-based classes</a>
 * should produce warnings about behavior that is inconsistent with value based semantics.
 *
 * Note this internal annotation is handled specially by the javac compiler.
 * To work properly with {@code --release older-release}, it requires special
 * handling in {@code make/langtools/src/classes/build/tools/symbolgenerator/CreateSymbols.java}
 * and {@code src/jdk.compiler/share/classes/com/sun/tools/javac/jvm/ClassReader.java}.
 *
 * @since 16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={TYPE})
public @interface ValueBased {
}

