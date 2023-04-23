/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.reflect.generics.tree;

/**
 * Common superinterface for generic signatures. These are the signatures
 * of complete class and method/constructor declarations.
 */
public interface Signature extends Tree{
    FormalTypeParameter[] getFormalTypeParameters();
}
