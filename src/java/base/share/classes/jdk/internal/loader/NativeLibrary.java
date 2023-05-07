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

package java.base.share.classes.jdk.internal.loader;

/**
 * NativeLibrary represents a loaded native library instance.
 */
public abstract class NativeLibrary {
    public abstract String name();

    /**
     * Finds the address of the entry of the given name.  Returns 0
     * if not found.
     *
     * @param name the name of the symbol to be found
     */
    public abstract long find(String name);

    /**
     * Finds the address of the entry of the given name.
     *
     * @param name the name of the symbol to be found
     * @throws NoSuchMethodException if the named entry is not found.
     */
    public final long lookup(String name) throws NoSuchMethodException {
        long addr = find(name);
        if (0 == addr) {
            throw new NoSuchMethodException("Cannot find symbol " + name + " in library " + name());
        }
        return addr;
    }

    /*
     * Returns the address of the named symbol defined in the library of
     * the given handle.  Returns 0 if not found.
     */
    static native long findEntry0(long handle, String name);
}
