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
import java.base.share.classes.java.lang.foreign.StructLayout;
import java.util.List;
import java.util.Optional;

public final class StructLayoutImpl extends AbstractGroupLayout<StructLayoutImpl> implements StructLayout {

    private StructLayoutImpl(List<MemoryLayout> elements) {
        super(Kind.STRUCT, elements);
    }

    private StructLayoutImpl(List<MemoryLayout> elements, long bitAlignment, Optional<String> name) {
        super(Kind.STRUCT, elements, bitAlignment, name);
    }

    @Override
    StructLayoutImpl dup(long bitAlignment, Optional<String> name) {
        return new StructLayoutImpl(memberLayouts(), bitAlignment, name);
    }

    public static StructLayout of(List<MemoryLayout> elements) {
        return new StructLayoutImpl(elements);
    }

}
