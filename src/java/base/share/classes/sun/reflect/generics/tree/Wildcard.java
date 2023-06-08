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

package java.base.share.classes.sun.reflect.generics.tree;

import java.base.share.classes.sun.reflect.generics.visitor.TypeTreeVisitor;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

public class Wildcard implements TypeArgument {
    private final FieldTypeSignature[] upperBounds;
    private final FieldTypeSignature[] lowerBounds;

    private Wildcard(FieldTypeSignature[] ubs, FieldTypeSignature[] lbs) {
        upperBounds = ubs;
        lowerBounds = lbs;
    }

    private static final FieldTypeSignature[] emptyBounds = new FieldTypeSignature[0];

    public static Wildcard make(FieldTypeSignature[] ubs,
                                FieldTypeSignature[] lbs) {
        return new Wildcard(ubs, lbs);
    }

    public FieldTypeSignature[] getUpperBounds() {
        return upperBounds;
    }

    public FieldTypeSignature[] getLowerBounds() {
        if (lowerBounds.length == 1 &&
            lowerBounds[0] == BottomSignature.make())
            return emptyBounds;
        else
            return lowerBounds;
    }

    public void accept(TypeTreeVisitor<?> v){v.visitWildcard(this);}
}
