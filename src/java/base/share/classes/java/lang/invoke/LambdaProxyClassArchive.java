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
package java.base.share.classes.java.lang.invoke;

import java.base.share.classes.jdk.internal.loader.BuiltinClassLoader;
import java.base.share.classes.jdk.internal.misc.CDS;

final class LambdaProxyClassArchive {
    /**
     * Check if the class is loaded by a built-in class loader.
     */
    static boolean loadedByBuiltinLoader(Class<?> cls) {
        ClassLoader cl = cls.getClassLoader();
        return (cl == null || (cl instanceof BuiltinClassLoader)) ? true : false;
    }

    private static native void addToArchive(Class<?> caller,
                                            String interfaceMethodName,
                                            MethodType factoryType,
                                            MethodType interfaceMethodType,
                                            MemberName implementationMember,
                                            MethodType dynamicMethodType,
                                            Class<?> lambdaProxyClass);

    private static native Class<?> findFromArchive(Class<?> caller,
                                                   String interfaceMethodName,
                                                   MethodType factoryType,
                                                   MethodType interfaceMethodType,
                                                   MemberName implementationMember,
                                                   MethodType dynamicMethodType);

    /**
     * Registers the lambdaProxyClass into CDS archive.
     * The VM will store the lambdaProxyClass into a hash table
     * using the first six arguments as the key.
     *
     * CDS only archives lambda proxy class if it's not serializable
     * and no marker interfaces and no additional bridges, and if it is
     * loaded by a built-in class loader.
     */
    static boolean register(Class<?> caller,
                            String interfaceMethodName,
                            MethodType factoryType,
                            MethodType interfaceMethodType,
                            MethodHandle implementation,
                            MethodType dynamicMethodType,
                            boolean isSerializable,
                            Class<?>[] altInterfaces,
                            MethodType[] altMethods,
                            Class<?> lambdaProxyClass) {
        if (!CDS.isDumpingArchive())
            throw new IllegalStateException("should only register lambda proxy class at dump time");

        if (loadedByBuiltinLoader(caller) &&
            !isSerializable && altInterfaces.length == 0 && altMethods.length == 0) {
            addToArchive(caller, interfaceMethodName, factoryType, interfaceMethodType,
                         implementation.internalMemberName(), dynamicMethodType,
                         lambdaProxyClass);
            return true;
        }
        return false;
    }

    /**
     * Lookup a lambda proxy class from the CDS archive using the first
     * six arguments as the key.
     *
     * CDS only archives lambda proxy class if it's not serializable
     * and no marker interfaces and no additional bridges, and if it is
     * loaded by a built-in class loader.
     */
    static Class<?> find(Class<?> caller,
                         String interfaceMethodName,
                         MethodType factoryType,
                         MethodType interfaceMethodType,
                         MethodHandle implementation,
                         MethodType dynamicMethodType,
                         boolean isSerializable,
                         Class<?>[] altInterfaces,
                         MethodType[] altMethods) {
        if (CDS.isDumpingArchive())
            throw new IllegalStateException("cannot load class from CDS archive at dump time");

        if (!loadedByBuiltinLoader(caller) ||
            !CDS.isSharingEnabled() || isSerializable || altInterfaces.length > 0 || altMethods.length > 0)
            return null;

        return findFromArchive(caller, interfaceMethodName, factoryType, interfaceMethodType,
                               implementation.internalMemberName(), dynamicMethodType);
    }
}
