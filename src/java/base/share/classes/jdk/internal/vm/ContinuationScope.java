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

package java.base.share.classes.jdk.internal.vm;

import java.util.Objects;

/**
 * A Continuation scope.
 */
public class ContinuationScope {
    final String name;

    /**
     * Constructs a new scope.
     * @param name The scope's name
     */
    public ContinuationScope(String name) {
        this.name = Objects.requireNonNull(name);
    }

    /**
     * A constructor providing no name is available to subclasses.
     */
    protected ContinuationScope() {
        this.name = getClass().getName();
    }

    /**
     * Returns this scope's name.
     * @return this scope's name
     */
    public final String getName() {
        return name;
    }

    @Override
    public final String toString() {
        return name;
    }

    @Override
    public final int hashCode() {
        return super.hashCode();
    }

    @Override
    public final boolean equals(Object obj) {
        return super.equals(obj);
    }
}
