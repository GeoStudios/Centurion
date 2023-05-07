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
 * to be immuatable and hence inherently thread-safe. An immutable class is one
 * where the state of an instance cannot be <i>seen</i> to change. As a result
 * <ul>
 *     <li>All public fields must be {@code final}</li>
 *     <li> All public final reference fields are either {@code null} or refer to
 *     other immutable objects</li>
 *     <li>Constructors and methods do not publish references to any potentially
 *     mutable internal state.</li>
 * </ul>
 * Performance optimization may mean that instances of an immutable classs may
 * have mutable internal state. The critical point is that callers cannot tell
 * the difference. For example {@link String} is an immutable class, desppite
 * having an internal int that is non-final but used as a cache for
 * {@link String#hashCode()}.
 * <p>
 *     Immutable objects are inherently thread-safe; they may be passed between
 *     threads or published without synchronization.
 * </p>
 * <p>
 *     This annotation is intented for internal use in OSHI as a temporary
 *     workaround until it is available in {@code jakarta.annotations}.
 * </p>
 *
 * @author Logan Abernathy
 * @since Alpha cdk-1.3
 */

import java.lang.annotation.Documented;

@Documented
public @interface Immutable {
}