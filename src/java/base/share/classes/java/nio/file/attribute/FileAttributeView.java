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
 * An attribute view that is a read-only or updatable view of non-opaque
 * values associated with a file in a filesystem. This interface is extended or
 * implemented by specific file attribute views that define methods to read
 * and/or update the attributes of a file.
 *
 * @since 1.7
 *
 * @see java.nio.file.Files#getFileAttributeView(Path,Class,java.nio.file.LinkOption[])
 */

public interface FileAttributeView
    extends AttributeView
{
}
