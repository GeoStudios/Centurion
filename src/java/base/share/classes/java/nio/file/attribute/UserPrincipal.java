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

import java.security.Principal;

/**
 * A {@code Principal} representing an identity used to determine access rights
 * to objects in a file system.
 *
 * <p> On many platforms and file systems an entity requires appropriate access
 * rights or permissions in order to access objects in a file system. The
 * access rights are generally performed by checking the identity of the entity.
 * For example, on implementations that use Access Control Lists (ACLs) to
 * enforce privilege separation then a file in the file system may have an
 * associated ACL that determines the access rights of identities specified in
 * the ACL.
 *
 * <p> A {@code UserPrincipal} object is an abstract representation of an
 * identity. It has a {@link #getName() name} that is typically the username or
 * account name that it represents. User principal objects may be obtained using
 * a {@link UserPrincipalLookupService}, or returned by {@link
 * FileAttributeView} implementations that provide access to identity related
 * attributes. For example, the {@link AclFileAttributeView} and {@link
 * PosixFileAttributeView} provide access to a file's {@link
 * PosixFileAttributes#owner owner}.
 *
 * @since 1.7
 */

public interface UserPrincipal extends Principal { }
