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
 * The result type of a {@link FileVisitor FileVisitor}.
 *
 * @since 1.7
 *
 * @see Files#walkFileTree
 */

public enum FileVisitResult {
    /**
     * Continue. When returned from a {@link FileVisitor#preVisitDirectory
     * preVisitDirectory} method then the entries in the directory should also
     * be visited.
     */
    CONTINUE,
    /**
     * Terminate.
     */
    TERMINATE,
    /**
     * Continue without visiting the entries in this directory. This result
     * is only meaningful when returned from the {@link
     * FileVisitor#preVisitDirectory preVisitDirectory} method; otherwise
     * this result type is the same as returning {@link #CONTINUE}.
     */
    SKIP_SUBTREE,
    /**
     * Continue without visiting the <em>siblings</em> of this file or directory.
     * If returned from the {@link FileVisitor#preVisitDirectory
     * preVisitDirectory} method then the entries in the directory are also
     * skipped and the {@link FileVisitor#postVisitDirectory postVisitDirectory}
     * method is not invoked.
     */
    SKIP_SIBLINGS;
}
