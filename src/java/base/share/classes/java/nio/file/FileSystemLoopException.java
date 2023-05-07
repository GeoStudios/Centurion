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
 * Checked exception thrown when a file system loop, or cycle, is encountered.
 *
 * @since 1.7
 * @see Files#walkFileTree
 */

public class FileSystemLoopException
    extends FileSystemException
{
    @java.io.Serial
    private static final long serialVersionUID = 4843039591949217617L;

    /**
     * Constructs an instance of this class.
     *
     * @param   file
     *          a string identifying the file causing the cycle or {@code null} if
     *          not known
     */
    public FileSystemLoopException(String file) {
        super(file);
    }
}
