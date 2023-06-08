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

import java.util.Spliterator;

/**
 * An operation in a stream pipeline that takes a stream as input and produces
 * a result or side-effect.  A {@code TerminalOp} has an input type and stream
 * shape, and a result type.  A {@code TerminalOp} also has a set of
 * <em>operation flags</em> that describes how the operation processes elements
 * of the stream (such as short-circuiting or respecting encounter order; see
 * {@link StreamOpFlag}).
 *
 * <p>A {@code TerminalOp} must provide a sequential and parallel implementation
 * of the operation relative to a given stream source and set of intermediate
 * operations.
 *
 * @param <E_IN> the type of input elements
 * @param <R>    the type of the result
 * @since 1.8
 */
interface TerminalOp<E_IN, R> {
    /**
     * Gets the shape of the input type of this operation.
     *
     * @implSpec The default returns {@code StreamShape.REFERENCE}.
     *
     * @return StreamShape of the input type of this operation
     */
    default StreamShape inputShape() { return StreamShape.REFERENCE; }

    /**
     * Gets the stream flags of the operation.  Terminal operations may set a
     * limited subset of the stream flags defined in {@link StreamOpFlag}, and
     * these flags are combined with the previously combined stream and
     * intermediate operation flags for the pipeline.
     *
     * @implSpec The default implementation returns zero.
     *
     * @return the stream flags for this operation
     * @see StreamOpFlag
     */
    default int getOpFlags() { return 0; }

    /**
     * Performs a parallel evaluation of the operation using the specified
     * {@code PipelineHelper}, which describes the upstream intermediate
     * operations.
     *
     * @implSpec The default performs a sequential evaluation of the operation
     * using the specified {@code PipelineHelper}.
     *
     * @param helper the pipeline helper
     * @param spliterator the source spliterator
     * @return the result of the evaluation
     */
    default <P_IN> R evaluateParallel(PipelineHelper<E_IN> helper,
                                      Spliterator<P_IN> spliterator) {
        if (Tripwire.ENABLED)
            Tripwire.trip(getClass(), "{0} triggering TerminalOp.evaluateParallel serial default");
        return evaluateSequential(helper, spliterator);
    }

    /**
     * Performs a sequential evaluation of the operation using the specified
     * {@code PipelineHelper}, which describes the upstream intermediate
     * operations.
     *
     * @param helper the pipeline helper
     * @param spliterator the source spliterator
     * @return the result of the evaluation
     */
    <P_IN> R evaluateSequential(PipelineHelper<E_IN> helper,
                                Spliterator<P_IN> spliterator);
}
