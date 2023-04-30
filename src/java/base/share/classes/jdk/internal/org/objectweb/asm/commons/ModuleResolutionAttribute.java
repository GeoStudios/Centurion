/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.jdk.internal.org.objectweb.asm.commons;

import java.base.share.classes.jdk.internal.org.objectweb.asm.Attribute;
import java.base.share.classes.jdk.internal.org.objectweb.asm.ByteVector;
import java.base.share.classes.jdk.internal.org.objectweb.asm.ClassReader;
import java.base.share.classes.jdk.internal.org.objectweb.asm.ClassWriter;
import java.base.share.classes.jdk.internal.org.objectweb.asm.Label;

/**
 * A ModuleResolution attribute. This attribute is specific to the OpenJDK and may change in the
 * future.
 *
 * @author Remi Forax
 */
public final class ModuleResolutionAttribute extends Attribute {
    /**
      * The resolution state of a module meaning that the module is not available from the class-path
      * by default.
      */
    public static final int RESOLUTION_DO_NOT_RESOLVE_BY_DEFAULT = 1;

    /** The resolution state of a module meaning the module is marked as deprecated. */
    public static final int RESOLUTION_WARN_DEPRECATED = 2;

    /**
      * The resolution state of a module meaning the module is marked as deprecated and will be removed
      * in a future release.
      */
    public static final int RESOLUTION_WARN_DEPRECATED_FOR_REMOVAL = 4;

    /**
      * The resolution state of a module meaning the module is not yet standardized, so in incubating
      * mode.
      */
    public static final int RESOLUTION_WARN_INCUBATING = 8;

    /**
      * The resolution state of the module. Must be one of {@link #RESOLUTION_WARN_DEPRECATED}, {@link
      * #RESOLUTION_WARN_DEPRECATED_FOR_REMOVAL}, and {@link #RESOLUTION_WARN_INCUBATING}.
      */
    public int resolution;

    /**
      * Constructs a new {@link ModuleResolutionAttribute}.
      *
      * @param resolution the resolution state of the module. Must be one of {@link
      *     #RESOLUTION_WARN_DEPRECATED}, {@link #RESOLUTION_WARN_DEPRECATED_FOR_REMOVAL}, and {@link
      *     #RESOLUTION_WARN_INCUBATING}.
      */
    public ModuleResolutionAttribute(final int resolution) {
        super("ModuleResolution");
        this.resolution = resolution;
    }

    /**
      * Constructs an empty {@link ModuleResolutionAttribute}. This object can be passed as a prototype
      * to the {@link ClassReader#accept(org.objectweb.asm.ClassVisitor, Attribute[], int)} method.
      */
    public ModuleResolutionAttribute() {
        this(0);
    }

    @Override
    protected Attribute read(
            final ClassReader classReader,
            final int offset,
            final int length,
            final char[] charBuffer,
            final int codeOffset,
            final Label[] labels) {
        return new ModuleResolutionAttribute(classReader.readUnsignedShort(offset));
    }

    @Override
    protected ByteVector write(
            final ClassWriter classWriter,
            final byte[] code,
            final int codeLength,
            final int maxStack,
            final int maxLocals) {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(resolution);
        return byteVector;
    }
}

