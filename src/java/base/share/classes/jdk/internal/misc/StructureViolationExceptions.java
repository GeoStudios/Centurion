/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package jdk.internal.misc;

import java.lang.reflect.Constructor;

/**
 * Defines static methods to allow code in java.base to create or throw
 * StructureViolationException. This class will go away when the exception
 * moves to java.base.
 */
public class StructureViolationExceptions {
    private static final Constructor<?> SVE_CTOR = structureViolationExceptionCtor();

    private StructureViolationExceptions() { }

    /**
     * Creates a StructureViolationException with the given message.
     */
    public static RuntimeException newException(String message) {
        if (SVE_CTOR != null) {
            try {
                return (RuntimeException) SVE_CTOR.newInstance(message);
            } catch (Exception e) {
                throw new InternalError(e);
            }
        } else {
            return new RuntimeException("Structure violation exception: " + message);
        }
    }

    /**
     * Creates a StructureViolationException with no message.
     */
    public static RuntimeException newException() {
        return newException(null);
    }

    /**
     * Throws a StructureViolationException with the given message.
     */
    public static void throwException(String message) {
        throw newException(message);
    }

    /**
     * Throws a StructureViolationException with no message.
     */
    public static void throwException() {
        throw newException(null);
    }

    /**
     * Returns the StructureViolationException(String) constructor.
     */
    private static Constructor<?> structureViolationExceptionCtor() {
        Constructor<?> ctor;
        try {
            Class<?> exClass = Class.forName("jdk.incubator.concurrent.StructureViolationException");
            ctor = exClass.getConstructor(String.class);
        } catch (ClassNotFoundException e) {
            ctor = null;
        } catch (Exception e) {
            throw new InternalError(e);
        }
        return ctor;
    }
}
