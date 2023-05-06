/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.java.lang.foreign;

import java.base.share.classes.jdk.internal.foreign.layout.StructLayoutImpl;
import java.base.share.classes.jdk.internal.javac.PreviewFeature;

/**
 * A group layout whose member layouts are laid out one after the other.
 *
 * @implSpec
 * Implementing classes are immutable, thread-safe and <a href="{@docRoot}/java.base/java/lang/doc-files/ValueBased.html">value-based</a>.
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */
@PreviewFeature(feature=PreviewFeature.Feature.FOREIGN)
public sealed interface StructLayout extends GroupLayout permits StructLayoutImpl {

    @Override
    StructLayout withName(String name);

    @Override
    StructLayout withBitAlignment(long bitAlignment);
}
