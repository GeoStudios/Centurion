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

package java.base.share.classes.java.nio.file.attribute;

/**
 * An object that encapsulates the value of a file attribute that can be set
 * atomically when creating a new file or directory by invoking the {@link
 * java.nio.file.Files#createFile createFile} or {@link
 * java.nio.file.Files#createDirectory createDirectory} methods.
 *
 * @param <T> The type of the file attribute value
 *
 * @since 1.7
 * @see PosixFilePermissions#asFileAttribute
 */

public interface FileAttribute<T> {
    /**
     * Returns the attribute name.
     *
     * @return The attribute name
     */
    String name();

    /**
     * Returns the attribute value.
     *
     * @return The attribute value
     */
    T value();
}
