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
import java.base.share.classes.java.lang.foreign.SequenceLayout;
import java.util.Objects;
import java.util.Optional;

public final class SequenceLayoutImpl extends AbstractLayout<SequenceLayoutImpl> implements SequenceLayout {

    private final long elemCount;
    private final MemoryLayout elementLayout;

    private SequenceLayoutImpl(long elemCount, MemoryLayout elementLayout) {
        this(elemCount, elementLayout, elementLayout.bitAlignment(), Optional.empty());
    }

    private SequenceLayoutImpl(long elemCount, MemoryLayout elementLayout, long bitAlignment, Optional<String> name) {
        super(Math.multiplyExact(elemCount, elementLayout.bitSize()), bitAlignment, name);
        this.elemCount = elemCount;
        this.elementLayout = elementLayout;
    }

    /**
     * {@return the element layout associated with this sequence layout}
     */
    public MemoryLayout elementLayout() {
        return elementLayout;
    }

    /**
     * {@return the element count of this sequence layout}
     */
    public long elementCount() {
        return elemCount;
    }

    /**
     * Returns a sequence layout with the same element layout, alignment constraints and name as this sequence layout,
     * but with the specified element count.
     *
     * @param elementCount the new element count.
     * @return a sequence layout with the given element count.
     * @throws IllegalArgumentException if {@code elementCount < 0}.
     */
    public SequenceLayout withElementCount(long elementCount) {
        MemoryLayoutUtil.checkSize(elementCount, true);
        return new SequenceLayoutImpl(elementCount, elementLayout, bitAlignment(), name());
    }

    /**
     * Re-arrange the elements in this sequence layout into a multi-dimensional sequence layout.
     * The resulting layout is a sequence layout where element layouts in the flattened projection of this
     * sequence layout (see {@link #flatten()}) are re-arranged into one or more nested sequence layouts
     * according to the provided element counts. This transformation preserves the layout size;
     * that is, multiplying the provided element counts must yield the same element count
     * as the flattened projection of this sequence layout.
     * <p>
     * For instance, given a sequence layout of the kind:
     * {@snippet lang = java:
     * var seq = MemoryLayout.sequenceLayout(4, MemoryLayout.sequenceLayout(3, ValueLayout.JAVA_INT));
     *}
     * calling {@code seq.reshape(2, 6)} will yield the following sequence layout:
     * {@snippet lang = java:
     * var reshapeSeq = MemoryLayout.sequenceLayout(2, MemoryLayout.sequenceLayout(6, ValueLayout.JAVA_INT));
     *}
     * <p>
     * If one of the provided element count is the special value {@code -1}, then the element
     * count in that position will be inferred from the remaining element counts and the
     * element count of the flattened projection of this layout. For instance, a layout equivalent to
     * the above {@code reshapeSeq} can also be computed in the following ways:
     * {@snippet lang = java:
     * var reshapeSeqImplicit1 = seq.reshape(-1, 6);
     * var reshapeSeqImplicit2 = seq.reshape(2, -1);
     *}
     *
     * @param elementCounts an array of element counts, of which at most one can be {@code -1}.
     * @return a sequence layout where element layouts in the flattened projection of this
     * sequence layout (see {@link #flatten()}) are re-arranged into one or more nested sequence layouts.
     * @throws IllegalArgumentException if two or more element counts are set to {@code -1}, or if one
     *                                  or more element count is {@code <= 0} (but other than {@code -1}) or, if, after any required inference,
     *                                  multiplying the element counts does not yield the same element count as the flattened projection of this
     *                                  sequence layout.
     */
    public SequenceLayout reshape(long... elementCounts) {
        Objects.requireNonNull(elementCounts);
        if (elementCounts.length == 0) {
            throw new IllegalArgumentException();
        }
        SequenceLayout flat = flatten();
        long expectedCount = flat.elementCount();

        long actualCount = 1;
        int inferPosition = -1;
        for (int i = 0; i < elementCounts.length; i++) {
            if (elementCounts[i] == -1) {
                if (inferPosition == -1) {
                    inferPosition = i;
                } else {
                    throw new IllegalArgumentException("Too many unspecified element counts");
                }
            } else if (elementCounts[i] <= 0) {
                throw new IllegalArgumentException("Invalid element count: " + elementCounts[i]);
            } else {
                actualCount = elementCounts[i] * actualCount;
            }
        }

        // infer an unspecified element count (if any)
        if (inferPosition != -1) {
            long inferredCount = expectedCount / actualCount;
            elementCounts[inferPosition] = inferredCount;
            actualCount = actualCount * inferredCount;
        }

        if (actualCount != expectedCount) {
            throw new IllegalArgumentException("Element counts do not match expected size: " + expectedCount);
        }

        MemoryLayout res = flat.elementLayout();
        for (int i = elementCounts.length - 1; i >= 0; i--) {
            res = MemoryLayout.sequenceLayout(elementCounts[i], res);
        }
        return (SequenceLayoutImpl) res;
    }

    /**
     * Returns a flattened sequence layout. The element layout of the returned sequence layout
     * is the first non-sequence element layout found by recursively traversing the element layouts of this sequence layout.
     * This transformation preserves the layout size; nested sequence layout in this sequence layout will
     * be dropped and their element counts will be incorporated into that of the returned sequence layout.
     * For instance, given a sequence layout of the kind:
     * {@snippet lang = java:
     * var seq = MemoryLayout.sequenceLayout(4, MemoryLayout.sequenceLayout(3, ValueLayout.JAVA_INT));
     *}
     * calling {@code seq.flatten()} will yield the following sequence layout:
     * {@snippet lang = java:
     * var flattenedSeq = MemoryLayout.sequenceLayout(12, ValueLayout.JAVA_INT);
     *}
     *
     * @return a sequence layout with the same size as this layout (but, possibly, with different
     * element count), whose element layout is not a sequence layout.
     */
    public SequenceLayout flatten() {
        long count = elementCount();
        MemoryLayout elemLayout = elementLayout();
        while (elemLayout instanceof SequenceLayoutImpl elemSeq) {
            count = count * elemSeq.elementCount();
            elemLayout = elemSeq.elementLayout();
        }
        return MemoryLayout.sequenceLayout(count, elemLayout);
    }

    @Override
    public String toString() {
        return decorateLayoutString(String.format("[%s:%s]",
                elemCount, elementLayout));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!super.equals(other)) {
            return false;
        }
        return other instanceof SequenceLayoutImpl otherSeq &&
                elemCount == otherSeq.elemCount &&
                elementLayout.equals(otherSeq.elementLayout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), elemCount, elementLayout);
    }

    @Override
    SequenceLayoutImpl dup(long bitAlignment, Optional<String> name) {
        return new SequenceLayoutImpl(elementCount(), elementLayout, bitAlignment, name);
    }

    @Override
    public boolean hasNaturalAlignment() {
        return bitAlignment() == elementLayout.bitAlignment();
    }

    public static SequenceLayout of(long elementCount, MemoryLayout elementLayout) {
        return new SequenceLayoutImpl(elementCount, elementLayout);
    }

}
