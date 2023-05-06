/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
