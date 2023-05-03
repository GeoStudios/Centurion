/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.java.lang.foreign;

import java.base.share.classes.jdk.internal.foreign.MemorySessionImpl;
import java.base.share.classes.jdk.internal.javac.PreviewFeature;
import java.base.share.classes.jdk.internal.ref.CleanerFactory;

/**
 * A segment scope controls access to memory segments.
 * <p>
 * A memory segment can only be accessed while its scope is {@linkplain #isAlive() alive}. Moreover,
 * depending on how the segment scope has been obtained, access might additionally be
 * <a href="Arena.html#thread-confinement">restricted to specific threads</a>.
 * <p>
 * The simplest segment scope is the {@linkplain SegmentScope#global() global scope}. The global scope
 * is always alive. As a result, segments associated with the global scope are always accessible and their backing
 * regions of memory are never deallocated. Moreover, memory segments associated with the global scope
 * can be {@linkplain #isAccessibleBy(Thread) accessed} from any thread.
 * {@snippet lang = java:
 * MemorySegment segment = MemorySegment.allocateNative(100, SegmentScope.global());
 * ...
 * // segment is never deallocated!
 *}
 * <p>
 * Alternatively, clients can obtain an {@linkplain SegmentScope#auto() automatic scope}, that is a segment
 * scope that is managed, automatically, by the garbage collector. The regions of memory backing memory segments associated
 * with an automatic scope are deallocated at some unspecified time <em>after</em> they become
 * <a href="../../../java/lang/ref/package.html#reachability">unreachable</a>, as shown below:
 *
 * {@snippet lang = java:
 * MemorySegment segment = MemorySegment.allocateNative(100, SegmentScope.auto());
 * ...
 * segment = null; // the segment region becomes available for deallocation after this point
 *}
 * Memory segments associated with an automatic scope can also be {@linkplain #isAccessibleBy(Thread) accessed} from any thread.
 * <p>
 * Finally, clients can obtain a segment scope from an existing {@linkplain Arena arena}, the arena scope. The regions of memory
 * backing memory segments associated with an arena scope are deallocated when the arena is {@linkplain Arena#close() closed}.
 * When this happens, the arena scope becomes not {@linkplain #isAlive() alive} and subsequent access operations on segments
 * associated with the arena scope will fail {@link IllegalStateException}.
 *
 * {@snippet lang = java:
 * MemorySegment segment = null;
 * try (Arena arena = Arena.openConfined()) {
 *     segment = MemorySegment.allocateNative(100, arena.scope());
 *     ...
 * } // segment region deallocated here
 * segment.get(ValueLayout.JAVA_BYTE, 0); // throws IllegalStateException
 * }
 *
 * Which threads can {@link #isAccessibleBy(Thread) access} memory segments associated with an arena scope depends
 * on the arena kind. For instance, segments associated with the scope of a {@linkplain Arena#openConfined() confined arena}
 * can only be accessed by the thread that created the arena. Conversely, segments associated with the scope of
 * {@linkplain Arena#openConfined() shared arena} can be accessed by any thread.
 *
 * @implSpec
 * Implementations of this interface are thread-safe.
 *
 * @see Arena
 * @see MemorySegment
 *
 * @since Java 2
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
@PreviewFeature(feature =PreviewFeature.Feature.FOREIGN)
sealed public interface SegmentScope permits MemorySessionImpl {

    /**
     * Creates a new scope that is managed, automatically, by the garbage collector.
     * Segments associated with the returned scope can be
     * {@linkplain SegmentScope#isAccessibleBy(Thread) accessed} by any thread.
     *
     * @return a new scope that is managed, automatically, by the garbage collector.
     */
    static SegmentScope auto() {
        return MemorySessionImpl.createImplicit(CleanerFactory.cleaner());
    }

    /**
     * Obtains the global scope. Segments associated with the global scope can be
     * {@linkplain SegmentScope#isAccessibleBy(Thread) accessed} by any thread.
     *
     * @return the global scope.
     */
    static SegmentScope global() {
        return MemorySessionImpl.GLOBAL;
    }

    /**
     * {@return {@code true}, if this scope is alive}
     */
    boolean isAlive();

    /**
     * {@return {@code true} if the provided thread can access and/or associate segments with this scope}
     * @param thread the thread to be tested.
     */
    boolean isAccessibleBy(Thread thread);

    /**
     * Runs a critical action while this scope is kept alive.
     * @param action the action to be run.
     * @throws IllegalStateException if this scope is not {@linkplain SegmentScope#isAlive() alive}.
     * @throws WrongThreadException if this method is called from a thread {@code T},
     * such that {@code isAccessibleBy(T) == false}.
     */
    void whileAlive(Runnable action);

}
