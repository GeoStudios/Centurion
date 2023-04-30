/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
