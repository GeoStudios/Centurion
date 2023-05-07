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

import java.base.share.classes.java.lang.foreign.MemoryLayout;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.LongBinaryOperator;
import java.util.stream.Collectors;

/**
 * A compound layout that aggregates multiple <em>member layouts</em>. There are two ways in which member layouts
 * can be combined: if member layouts are laid out one after the other, the resulting group layout is said to be a <em>struct</em>
 * (see {@link MemoryLayout#structLayout(MemoryLayout...)}); conversely, if all member layouts are laid out at the same starting offset,
 * the resulting group layout is said to be a <em>union</em> (see {@link MemoryLayout#unionLayout(MemoryLayout...)}).
 *
 * @implSpec
 * This class is immutable, thread-safe and <a href="{@docRoot}/java.base/java/lang/doc-files/ValueBased.html">value-based</a>.
 *
 * @since 19
 */
public sealed abstract class AbstractGroupLayout<L extends AbstractGroupLayout<L> & MemoryLayout>
        extends AbstractLayout<L>
        permits StructLayoutImpl, UnionLayoutImpl {

    private final Kind kind;
    private final List<MemoryLayout> elements;

    AbstractGroupLayout(Kind kind, List<MemoryLayout> elements) {
        this(kind, elements, kind.alignof(elements), Optional.empty());
    }

    AbstractGroupLayout(Kind kind, List<MemoryLayout> elements, long bitAlignment, Optional<String> name) {
        super(kind.sizeof(elements), bitAlignment, name); // Subclassing creates toctou problems here
        this.kind = kind;
        this.elements = List.copyOf(elements);
    }

    /**
     * Returns the member layouts associated with this group.
     *
     * @apiNote the order in which member layouts are returned is the same order in which member layouts have
     * been passed to one of the group layout factory methods (see {@link MemoryLayout#structLayout(MemoryLayout...)},
     * {@link MemoryLayout#unionLayout(MemoryLayout...)}).
     *
     * @return the member layouts associated with this group.
     */
    public final List<MemoryLayout> memberLayouts() {
        return elements; // "elements" are already unmodifiable.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        return decorateLayoutString(elements.stream()
                .map(Object::toString)
                .collect(Collectors.joining(kind.delimTag, "[", "]")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!super.equals(other)) {
            return false;
        }
        return other instanceof AbstractGroupLayout<?> otherGroup &&
                kind == otherGroup.kind &&
                elements.equals(otherGroup.elements);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        return Objects.hash(super.hashCode(), kind, elements);
    }

    @Override
    public final boolean hasNaturalAlignment() {
        return bitAlignment() == kind.alignof(elements);
    }

    /**
     * The group kind.
     */
    enum Kind {
        /**
         * A 'struct' kind.
         */
        STRUCT("", Math::addExact),
        /**
         * A 'union' kind.
         */
        UNION("|", Math::max);

        final String delimTag;
        final LongBinaryOperator sizeOp;

        Kind(String delimTag, LongBinaryOperator sizeOp) {
            this.delimTag = delimTag;
            this.sizeOp = sizeOp;
        }

        long sizeof(List<MemoryLayout> elems) {
            long size = 0;
            for (MemoryLayout elem : elems) {
                size = sizeOp.applyAsLong(size, elem.bitSize());
            }
            return size;
        }

        long alignof(List<MemoryLayout> elems) {
            return elems.stream()
                    .mapToLong(MemoryLayout::bitAlignment)
                    .max() // max alignment in case we have member layouts
                    .orElse(1); // or minimal alignment if no member layout is given
        }
    }
}
