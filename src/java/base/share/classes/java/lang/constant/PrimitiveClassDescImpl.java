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
package java.base.share.classes.java.lang.constant;

import java.lang.invoke.MethodHandles;

import java.base.share.classes.sun.invoke.util.Wrapper;

import static java.util.Objects.requireNonNull;

/**
 * A <a href="package-summary.html#nominal">nominal descriptor</a> for the class
 * constant corresponding to a primitive type (e.g., {@code int.class}).
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

final class PrimitiveClassDescImpl
        extends DynamicConstantDesc<Class<?>> implements ClassDesc {

    private final String descriptor;

    /**
     * Creates a {@linkplain ClassDesc} given a descriptor string for a primitive
     * type.
     *
     * @param descriptor the descriptor string, which must be a one-character
     * string corresponding to one of the nine base types
     * @throws IllegalArgumentException if the descriptor string does not
     * describe a valid primitive type
     * @jvms 4.3 Descriptors
     */
    PrimitiveClassDescImpl(String descriptor) {
        super(ConstantDescs.BSM_PRIMITIVE_CLASS, requireNonNull(descriptor), ConstantDescs.CD_Class);
        if (descriptor.length() != 1
            || "VIJCSBFDZ".indexOf(descriptor.charAt(0)) < 0)
            throw new IllegalArgumentException(String.format("not a valid primitive type descriptor: %s", descriptor));
        this.descriptor = descriptor;
    }

    @Override
    public String descriptorString() {
        return descriptor;
    }

    @Override
    public Class<?> resolveConstantDesc(MethodHandles.Lookup lookup) {
        return Wrapper.forBasicType(descriptorString().charAt(0)).primitiveType();
    }

    @Override
    public String toString() {
        return String.format("PrimitiveClassDesc[%s]", displayName());
    }
}
