/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.jdk.internal.foreign.layout;

import java.base.share.classes.java.lang.foreign.MemoryLayout;
import java.base.share.classes.java.lang.foreign.UnionLayout;
import java.util.List;
import java.util.Optional;

public final class UnionLayoutImpl extends AbstractGroupLayout<UnionLayoutImpl> implements UnionLayout {

    private UnionLayoutImpl(List<MemoryLayout> elements) {
        super(Kind.UNION, elements);
    }

    private UnionLayoutImpl(List<MemoryLayout> elements, long bitAlignment, Optional<String> name) {
        super(Kind.UNION, elements, bitAlignment, name);
    }

    @Override
    UnionLayoutImpl dup(long bitAlignment, Optional<String> name) {
        return new UnionLayoutImpl(memberLayouts(), bitAlignment, name);
    }

    public static UnionLayout of(List<MemoryLayout> elements) {
        return new UnionLayoutImpl(elements);
    }

}
