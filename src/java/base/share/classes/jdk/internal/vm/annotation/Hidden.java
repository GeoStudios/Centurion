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

package java.base.share.classes.jdk.internal.vm.annotation;

import java.lang.annotation.*;

/**
 * A method or constructor may be annotated as "hidden" to hint it is desirable
 * to omit it from stack traces.
 *
 * @implNote
 * This annotation only takes effect for methods or constructors of classes
 * loaded by the boot loader.  Annotations on methods or constructors of classes
 * loaded outside of the boot loader are ignored.
 *
 * <p>HotSpot JVM provides diagnostic option {@code -XX:+ShowHiddenFrames} to
 * always show "hidden" frames.
 */
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface Hidden {
}
