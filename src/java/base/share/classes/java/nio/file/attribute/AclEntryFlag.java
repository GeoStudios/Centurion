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
 * Defines the flags for used by the flags component of an ACL {@link AclEntry
 * entry}.
 *
 * <p> In this release, this class does not define flags related to {@link
 * AclEntryType#AUDIT} and {@link AclEntryType#ALARM} entry types.
 *
 * @since 1.7
 */

public enum AclEntryFlag {

    /**
     * Can be placed on a directory and indicates that the ACL entry should be
     * added to each new non-directory file created.
     */
    FILE_INHERIT,

    /**
     * Can be placed on a directory and indicates that the ACL entry should be
     * added to each new directory created.
     */
    DIRECTORY_INHERIT,

    /**
     * Can be placed on a directory to indicate that the ACL entry should not
     * be placed on the newly created directory which is inheritable by
     * subdirectories of the created directory.
     */
    NO_PROPAGATE_INHERIT,

    /**
     * Can be placed on a directory but does not apply to the directory,
     * only to newly created files/directories as specified by the
     * {@link #FILE_INHERIT} and {@link #DIRECTORY_INHERIT} flags.
     */
    INHERIT_ONLY;
}
