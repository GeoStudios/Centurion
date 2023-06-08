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
package java.base.share.classes.jdk.internal.foreign.layout;

import java.base.share.classes.java.lang.foreign.PaddingLayout;
import java.util.Objects;
import java.util.Optional;

public final class PaddingLayoutImpl extends AbstractLayout<PaddingLayoutImpl> implements PaddingLayout {

    private PaddingLayoutImpl(long bitSize) {
        this(bitSize, 1, Optional.empty());
    }

    private PaddingLayoutImpl(long bitSize, long bitAlignment, Optional<String> name) {
        super(bitSize, bitAlignment, name);
    }

    @Override
    public String toString() {
        return decorateLayoutString("x" + bitSize());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!super.equals(other)) {
            return false;
        }
        if (!(other instanceof PaddingLayoutImpl p)) {
            return false;
        }
        return bitSize() == p.bitSize();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bitSize());
    }

    @Override
    PaddingLayoutImpl dup(long bitAlignment, Optional<String> name) {
        return new PaddingLayoutImpl(bitSize(), bitAlignment, name);
    }

    @Override
    public boolean hasNaturalAlignment() {
        return true;
    }

    public static PaddingLayout of(long bitSize) {
        return new PaddingLayoutImpl(bitSize);
    }

}
