/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.lang.foreign;

import jdk.internal.foreign.layout.UnionLayoutImpl;
import jdk.internal.javac.PreviewFeature;

/**
 * A group layout whose member layouts are laid out at the same starting offset.
 *
 * @implSpec
 * Implementing classes are immutable, thread-safe and <a href="{@docRoot}/java.base/java/lang/doc-files/ValueBased.html">value-based</a>.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
@PreviewFeature(feature=PreviewFeature.Feature.FOREIGN)
public sealed interface UnionLayout extends GroupLayout permits UnionLayoutImpl {

    /**
     * {@inheritDoc}
     */
    @Override
    UnionLayout withName(String name);

    /**
     * {@inheritDoc}
     */
    @Override
    UnionLayout withBitAlignment(long bitAlignment);
}
