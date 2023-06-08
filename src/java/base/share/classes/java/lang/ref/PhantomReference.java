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

package java.base.share.classes.java.lang.ref;

import java.base.share.classes.jdk.internal.vm.annotation.IntrinsicCandidate;

/**
 * Phantom reference objects, which are enqueued after the collector
 * determines that their referents may otherwise be reclaimed.  Phantom
 * references are most often used to schedule post-mortem cleanup actions.
 *
 * <p> Suppose the garbage collector determines at a certain point in time
 * that an object is <a href="package-summary.html#reachability">
 * phantom reachable</a>.  At that time it will atomically clear
 * all phantom references to that object and all phantom references to
 * any other phantom-reachable objects from which that object is reachable.
 * At the same time or at some later time it will enqueue those newly-cleared
 * phantom references that are registered with reference queues.
 *
 * <p> In order to ensure that a reclaimable object remains so, the referent of
 * a phantom reference may not be retrieved: The {@code get} method of a
 * phantom reference always returns {@code null}.
 * The {@link #refersTo(Object) refersTo} method can be used to test
 * whether some object is the referent of a phantom reference.
 * @param <T> the type of the referent
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */

public non-sealed class PhantomReference<T> extends Reference<T> {

    /**
     * Returns this reference object's referent.  Because the referent of a
     * phantom reference is always inaccessible, this method always returns
     * {@code null}.
     *
     * @return {@code null}
     */
    public T get() {
        return null;
    }

    /* Override the implementation of Reference.refersTo.
     * Phantom references are weaker than finalization, so the referent
     * access needs to be handled differently for garbage collectors that
     * do reference processing concurrently.
     */
    @Override
    boolean refersToImpl(T obj) {
        return refersTo0(obj);
    }

    @IntrinsicCandidate
    private native boolean refersTo0(Object o);

    /**
     * Creates a new phantom reference that refers to the given object and
     * is registered with the given queue.
     *
     * <p> It is possible to create a phantom reference with a {@code null}
     * queue.  Such a reference will never be enqueued.
     *
     * @param referent the object the new phantom reference will refer to
     * @param q the queue with which the reference is to be registered,
     *          or {@code null} if registration is not required
     */
    public PhantomReference(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
    }

}
