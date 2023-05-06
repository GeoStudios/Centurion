/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang.ref;

/**
 * Final references, used to implement finalization
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
sealed class FinalReference<T> extends Reference<T> permits Finalizer {

    public FinalReference(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
    }

    /* May only be called when the reference is inactive, so no longer weak. */
    @Override
    public T get() {
        // Cannot call super.get() when active, as the GC could
        // deactivate immediately after the test.
        return getFromInactiveFinalReference();
    }

    /* May only be called when the reference is inactive, so no longer weak.
     * Clearing while active would discard the finalization request.
     */
    @Override
    public void clear() {
        clearInactiveFinalReference();
    }

    @Override
    public boolean enqueue() {
        throw new InternalError("should never reach here");
    }
}
