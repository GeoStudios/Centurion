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
