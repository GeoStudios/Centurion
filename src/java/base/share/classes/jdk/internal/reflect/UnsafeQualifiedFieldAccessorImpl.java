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

package java.base.share.classes.jdk.internal.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.base.share.classes.jdk.internal.misc.Unsafe;

/**
 * Base class for jdk.internal.misc.Unsafe-based FieldAccessors for fields with
 * final or volatile qualifiers. These differ from unqualified
 * versions in that (1) they check for read-only status (2) they use
 * the volatile forms of Unsafe get/put methods. (When accessed via
 * reflection, finals act as slightly "lighter" forms of volatiles. So
 * the volatile forms are heavier than necessary in terms of
 * underlying reordering rules and memory barriers, but preserve
 * correctness.)
 */

abstract class UnsafeQualifiedFieldAccessorImpl
    extends UnsafeFieldAccessorImpl
{
    protected final boolean isReadOnly;

    UnsafeQualifiedFieldAccessorImpl(Field field, boolean isReadOnly) {
        super(field);
        this.isReadOnly = isReadOnly;
    }
}
