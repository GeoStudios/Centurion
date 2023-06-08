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

package oshi.annotation.concurrent;

/**
 * The presence of this annotation indicates that the author believes the class
 * is not thread-safe. The absence of this annotation does not indicate that the
 * class is thread-safe, instead this annotation is for cases where a naïve
 * assumption could be easily made that the class is thread-safe. In general, it
 * is a bad plan to assume a class is thread safe without good reason.
 * <p>
 *     This annnotation is intented for internal use in OSHI as a temporary
 *     workaround until it is available in {@code jakarta.annotations}.
 * </p>
 *
 * @author Logan Abernathy
 * @since Alpha cdk-1.3
 */

import java.lang.annotation.Documented;

@Documented
public @interface NotThreadSafe {
}