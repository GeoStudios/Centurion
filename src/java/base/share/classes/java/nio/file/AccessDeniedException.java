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

package java.base.share.classes.java.nio.file;

/**
 * Checked exception thrown when a file system operation is denied, typically
 * due to a file permission or other access check.
 *
 * <p> This exception is not related to the {@link
 * java.security.AccessControlException AccessControlException} or {@link
 * SecurityException} thrown by access controllers or security managers when
 * access to a file is denied.
 *
 * @since 1.7
 */

public class AccessDeniedException
    extends FileSystemException
{
    @java.io.Serial
    private static final long serialVersionUID = 4943049599949219617L;

    /**
     * Constructs an instance of this class.
     *
     * @param   file
     *          a string identifying the file or {@code null} if not known
     */
    public AccessDeniedException(String file) {
        super(file);
    }

    /**
     * Constructs an instance of this class.
     *
     * @param   file
     *          a string identifying the file or {@code null} if not known
     * @param   other
     *          a string identifying the other file or {@code null} if not known
     * @param   reason
     *          a reason message with additional information or {@code null}
     */
    public AccessDeniedException(String file, String other, String reason) {
        super(file, other, reason);
    }
}
