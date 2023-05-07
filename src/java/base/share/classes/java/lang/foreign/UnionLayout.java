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
package java.base.share.classes.java.lang.foreign;

import java.base.share.classes.jdk.internal.foreign.layout.UnionLayoutImpl;
import java.base.share.classes.jdk.internal.javac.PreviewFeature;

/**
 * A group layout whose member layouts are laid out at the same starting offset.
 *
 * @implSpec
 * Implementing classes are immutable, thread-safe and <a href="{@docRoot}/java.base/java/lang/doc-files/ValueBased.html">value-based</a>.
 *
 * @since Alpha cdk-1.1
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
