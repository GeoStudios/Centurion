/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package jdk.internal.foreign.layout;

import java.lang.foreign.PaddingLayout;
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
