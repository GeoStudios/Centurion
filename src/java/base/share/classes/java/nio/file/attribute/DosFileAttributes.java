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
 * File attributes associated with a file in a file system that supports
 * legacy "DOS" attributes.
 *
 * <p> <b>Usage Example:</b>
 * <pre>
 *    Path file = ...
 *    DosFileAttributes attrs = Files.readAttributes(file, DosFileAttributes.class);
 * </pre>
 *
 * @since 1.7
 */

public interface DosFileAttributes
    extends BasicFileAttributes
{
    /**
     * Returns the value of the read-only attribute.
     *
     * <p> This attribute is often used as a simple access control mechanism
     * to prevent files from being deleted or updated. Whether the file system
     * or platform does any enforcement to prevent <em>read-only</em> files
     * from being updated is implementation specific.
     *
     * @return  the value of the read-only attribute
     */
    boolean isReadOnly();

    /**
     * Returns the value of the hidden attribute.
     *
     * <p> This attribute is often used to indicate if the file is visible to
     * users.
     *
     * @return  the value of the hidden attribute
     */
    boolean isHidden();

    /**
     * Returns the value of the archive attribute.
     *
     * <p> This attribute is typically used by backup programs.
     *
     * @return  the value of the archive attribute
     */
    boolean isArchive();

    /**
     * Returns the value of the system attribute.
     *
     * <p> This attribute is often used to indicate that the file is a component
     * of the operating system.
     *
     * @return  the value of the system attribute
     */
    boolean isSystem();
}
