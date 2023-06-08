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
package java.base.share.classes.java.util.stream;

import java.util.function.Supplier;

/**
 * A {@link Sink} which accumulates state as elements are accepted, and allows
 * a result to be retrieved after the computation is finished.
 *
 * @param <T> the type of elements to be accepted
 * @param <R> the type of the result
 *
 * @since 1.8
 */
interface TerminalSink<T, R> extends Sink<T>, Supplier<R> { }
