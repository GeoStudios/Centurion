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

package java.base.share.classes.java.lang.invoke;

/**
 * A {@code VolatileCallSite} is a {@link CallSite} whose target acts like a volatile variable.
 * An {@code invokedynamic} instruction linked to a {@code VolatileCallSite} sees updates
 * to its call site target immediately, even if the update occurs in another thread.
 * There may be a performance penalty for such tight coupling between threads.
 * <p>
 * Unlike {@code MutableCallSite}, there is no
 * {@linkplain MutableCallSite#syncAll syncAll operation} on volatile
 * call sites, since every write to a volatile variable is implicitly
 * synchronized with reader threads.
 * <p>
 * In other respects, a {@code VolatileCallSite} is interchangeable
 * with {@code MutableCallSite}.
 * @see MutableCallSite
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 3/5/2023
 */
public non-sealed class VolatileCallSite extends CallSite {
    /**
     * Creates a call site with a volatile binding to its target.
     * The initial target is set to a method handle
     * of the given type which will throw an {@code IllegalStateException} if called.
     * @param type the method type that this call site will have
     * @throws NullPointerException if the proposed type is null
     */
    public VolatileCallSite(MethodType type) {
        super(type);
    }

    /**
     * Creates a call site with a volatile binding to its target.
     * The target is set to the given value.
     * @param target the method handle that will be the initial target of the call site
     * @throws NullPointerException if the proposed target is null
     */
    public VolatileCallSite(MethodHandle target) {
        super(target);
    }

    /**
     * Returns the target method of the call site, which behaves
     * like a {@code volatile} field of the {@code VolatileCallSite}.
     * <p>
     * The interactions of {@code getTarget} with memory are the same
     * as of a read from a {@code volatile} field.
     * <p>
     * In particular, the current thread is required to issue a fresh
     * read of the target from memory, and must not fail to see
     * a recent update to the target by another thread.
     *
     * @return the linkage state of this call site, a method handle which can change over time
     * @see #setTarget
     */
    @Override public final MethodHandle getTarget() {
        return getTargetVolatile();
    }

    /**
     * Updates the target method of this call site, as a volatile variable.
     * The type of the new target must agree with the type of the old target.
     * <p>
     * The interactions with memory are the same as of a write to a volatile field.
     * In particular, any threads is guaranteed to see the updated target
     * the next time it calls {@code getTarget}.
     * @param newTarget the new target
     * @throws NullPointerException if the proposed new target is null
     * @throws WrongMethodTypeException if the proposed new target
     *         has a method type that differs from the previous target
     * @see #getTarget
     */
    @Override public void setTarget(MethodHandle newTarget) {
        setTargetVolatile(newTarget);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final MethodHandle dynamicInvoker() {
        return makeDynamicInvoker();
    }
}
