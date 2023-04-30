/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.jdk.internal.foreign.layout;

import jdk.internal.foreign.Utils;
import jdk.internal.vm.annotation.ForceInline;
import jdk.internal.vm.annotation.Stable;

import java.lang.foreign.GroupLayout;
import java.lang.foreign.MemoryLayout;
import java.lang.foreign.SequenceLayout;
import java.lang.foreign.StructLayout;
import java.lang.foreign.UnionLayout;
import java.lang.foreign.ValueLayout;
import java.util.Objects;
import java.util.Optional;

public abstract sealed class AbstractLayout<L extends AbstractLayout<L> & MemoryLayout>
        permits AbstractGroupLayout, PaddingLayoutImpl, SequenceLayoutImpl, ValueLayouts.AbstractValueLayout {

    private final long bitSize;
    private final long bitAlignment;
    private final Optional<String> name;
    @Stable
    private long byteSize;

    AbstractLayout(long bitSize, long bitAlignment, Optional<String> name) {
        this.bitSize = bitSize;
        this.bitAlignment = bitAlignment;
        this.name = name;
    }

    public final L withName(String name) {
        Objects.requireNonNull(name);
        return dup(bitAlignment, Optional.of(name));
    }

    public final Optional<String> name() {
        return name;
    }

    public final L withBitAlignment(long bitAlignment) {
        checkAlignment(bitAlignment);
        return dup(bitAlignment, name);
    }

    public final long bitAlignment() {
        return bitAlignment;
    }

    @ForceInline
    public final long byteSize() {
        if (byteSize == 0) {
            byteSize = Utils.bitsToBytesOrThrow(bitSize(),
                    () -> new UnsupportedOperationException("Cannot compute byte size; bit size is not a multiple of 8"));
        }
        return byteSize;
    }

    public final long bitSize() {
        return bitSize;
    }

    public boolean hasNaturalAlignment() {
        return bitSize == bitAlignment;
    }

    // the following methods have to copy the same Javadoc as in MemoryLayout, or subclasses will just show
    // the Object methods javadoc

    /**
     * {@return the hash code value for this layout}
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, bitSize, bitAlignment);
    }

    /**
     * Compares the specified object with this layout for equality. Returns {@code true} if and only if the specified
     * object is also a layout, and it is equal to this layout. Two layouts are considered equal if they are of
     * the same kind, have the same size, name and alignment constraints. Furthermore, depending on the layout kind, additional
     * conditions must be satisfied:
     * <ul>
     *     <li>two value layouts are considered equal if they have the same {@linkplain ValueLayout#order() order},
     *     and {@linkplain ValueLayout#carrier() carrier}</li>
     *     <li>two sequence layouts are considered equal if they have the same element count (see {@link SequenceLayout#elementCount()}), and
     *     if their element layouts (see {@link SequenceLayout#elementLayout()}) are also equal</li>
     *     <li>two group layouts are considered equal if they are of the same type (see {@link StructLayout},
     *     {@link UnionLayout}) and if their member layouts (see {@link GroupLayout#memberLayouts()}) are also equal</li>
     * </ul>
     *
     * @param other the object to be compared for equality with this layout.
     * @return {@code true} if the specified object is equal to this layout.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        return other instanceof AbstractLayout<?> otherLayout &&
                name.equals(otherLayout.name) &&
                bitSize == otherLayout.bitSize &&
                bitAlignment == otherLayout.bitAlignment;
    }

    /**
     * {@return the string representation of this layout}
     */
    public abstract String toString();

    abstract L dup(long alignment, Optional<String> name);

    String decorateLayoutString(String s) {
        if (name().isPresent()) {
            s = String.format("%s(%s)", s, name().get());
        }
        if (!hasNaturalAlignment()) {
            s = bitAlignment + "%" + s;
        }
        return s;
    }

    private static void checkAlignment(long alignmentBitCount) {
        if (((alignmentBitCount & (alignmentBitCount - 1)) != 0L) || //alignment must be a power of two
                (alignmentBitCount < 8)) { //alignment must be greater than 8
            throw new IllegalArgumentException("Invalid alignment: " + alignmentBitCount);
        }
    }


}
