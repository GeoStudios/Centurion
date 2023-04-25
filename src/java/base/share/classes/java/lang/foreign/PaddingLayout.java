/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.lang.foreign;

import jdk.internal.foreign.layout.PaddingLayoutImpl;
import jdk.internal.javac.PreviewFeature;

/**
 * A padding layout. A padding layout specifies the size of extra space which is typically not accessed by applications,
 * and is typically used for aligning member layouts around word boundaries.
 *
 * @implSpec
 * Implementing classes are immutable, thread-safe and <a href="{@docRoot}/java.base/java/lang/doc-files/ValueBased.html">value-based</a>.
 *
 * @since Pre Java 1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
@PreviewFeature(feature=PreviewFeature.Feature.FOREIGN)
public sealed interface PaddingLayout extends MemoryLayout permits PaddingLayoutImpl {

    /**
     * {@inheritDoc}
     */
    @Override
    PaddingLayout withName(String name);

    /**
     * {@inheritDoc}
     */
    @Override
    PaddingLayout withBitAlignment(long bitAlignment);
}
