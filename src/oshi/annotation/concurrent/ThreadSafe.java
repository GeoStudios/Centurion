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
 * to be thread-safe. As such, there should be no sequence of accessing the
 * public methods or fields that could put an instance of this class into an
 * invalid state, irrespective of any rearrangement of those operations by the
 * Java Runtime and without introducing any requirements for synchronization or
 * coordination by the caller/accessor.
 * <p>
 * This annotation is intended for internal use in OSHI as a temporary
 * workaround until it is available in {@code jakarta.annotations}.
 *
 * @author Logan Abernathy
 * @since Alpha cdk-1.3
 */

import java.lang.annotation.Documented;

@Documented
public @interface ThreadSafe {
}