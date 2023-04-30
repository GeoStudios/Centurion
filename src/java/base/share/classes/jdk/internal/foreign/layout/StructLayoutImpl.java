/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.jdk.internal.foreign.layout;

import java.lang.foreign.MemoryLayout;
import java.lang.foreign.StructLayout;
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
