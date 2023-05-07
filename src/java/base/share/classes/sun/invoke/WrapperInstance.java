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

package java.base.share.classes.sun.invoke;

import java.lang.invoke.MethodHandle;

/**
 * Private API used inside of java.lang.invoke.MethodHandles.
 * Interface implemented by every object which is produced by
 * {@link java.lang.invoke.MethodHandleProxies#asInterfaceInstance MethodHandleProxies.asInterfaceInstance}.
 * The methods of this interface allow a caller to recover the parameters
 * to {@code asInstance}.
 * This allows applications to repeatedly convert between method handles
 * and SAM objects, without the risk of creating unbounded delegation chains.
 */
public interface WrapperInstance {
    /** Produce or recover a target method handle which is behaviorally
     *  equivalent to the SAM method of this object.
     */
    public MethodHandle getWrapperInstanceTarget();
    /** Recover the SAM type for which this object was created.
     */
    public Class<?> getWrapperInstanceType();
}

