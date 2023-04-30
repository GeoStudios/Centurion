/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.nio.file.attribute;

/**
 * A typesafe enumeration of the access control entry types.
 *
 * @since 1.7
 */

public enum AclEntryType {
    /**
     * Explicitly grants access to a file or directory.
     */
    ALLOW,

    /**
     * Explicitly denies access to a file or directory.
     */
    DENY,

    /**
     * Log, in a system dependent way, the access specified in the
     * permissions component of the ACL entry.
     */
    AUDIT,

    /**
     * Generate an alarm, in a system dependent way, the access specified in the
     * permissions component of the ACL entry.
     */
    ALARM
}
