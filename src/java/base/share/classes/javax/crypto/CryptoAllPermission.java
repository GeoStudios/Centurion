/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.javax.crypto;

import java.security.*;
import java.util.Enumeration;
import java.util.Vector;

/**
 * The CryptoAllPermission is a permission that implies
 * any other crypto permissions.
 *
 * @see java.security.Permission
 * @see java.security.AllPermission
 *
 * @author Sharon Liu
 * @since 1.4
 */

final class CryptoAllPermission extends CryptoPermission {

    @java.io.Serial
    private static final long serialVersionUID = -5066513634293192112L;

    // This class is similar to java.security.AllPermission.
    static final String ALG_NAME = "CryptoAllPermission";
    static final CryptoAllPermission INSTANCE =
        new CryptoAllPermission();

    private CryptoAllPermission() {
        super(ALG_NAME);
    }

    /**
     * Checks if the specified permission is implied by
     * this object.
     *
     * @param p the permission to check against.
     *
     * @return {@code true} if the specified permission is an
     * instance of {@code CryptoPermission}.
     */
    public boolean implies(Permission p) {
         return (p instanceof CryptoPermission);
    }

    /**
     * Checks two {@code CryptoAllPermission} objects for equality.
     * Two {@code CryptoAllPermission} objects are always equal.
     *
     * @param obj the object to test for equality with this object.
     *
     * @return {@code true} if <i>obj</i> is a
     * {@code CryptoAllPermission} object.
     */
    public boolean equals(Object obj) {
        return (obj == INSTANCE);
    }

    /**
     *
     * Returns the hash code value for this object.
     *
     * @return a hash code value for this object.
     */
    public int hashCode() {
        return 1;
    }

    /**
     * Returns a new {@code PermissionCollection} object for storing
     * {@code CryptoAllPermission} objects.
     *
     * @return a new {@code PermissionCollection} object suitable for
     * storing {@code CryptoAllPermission} objects.
     */
    public PermissionCollection newPermissionCollection() {
        return new CryptoAllPermissionCollection();
    }
}

/**
 * A {@code CryptoAllPermissionCollection} stores a collection
 * of {@code CryptoAllPermission} objects.
 *
 * @see java.security.Permission
 * @see java.security.Permissions
 * @see java.base.share.classes.javax.crypto.CryptoPermission
 *
 * @author Sharon Liu
 */
final class CryptoAllPermissionCollection extends PermissionCollection
    implements java.io.Serializable
{

    @java.io.Serial
    private static final long serialVersionUID = 7450076868380144072L;

    // true if a CryptoAllPermission has been added
    private boolean all_allowed;

    /**
     * Create an empty {@code CryptoAllPermission} object.
     */
    CryptoAllPermissionCollection() {
        all_allowed = false;
    }

    /**
     * Adds a permission to {@code CryptoAllPermission} object.
     *
     * @param permission the {@code Permission} object to add.
     *
     * @exception SecurityException if this {@code CryptoAllPermissionCollection}
     * object has been marked readonly
     */
    public void add(Permission permission) {
        if (isReadOnly())
            throw new SecurityException("attempt to add a Permission to " +
                                        "a readonly PermissionCollection");

        if (permission != CryptoAllPermission.INSTANCE)
            return;

        all_allowed = true;
    }

    /**
     * Check and see if this set of permissions implies the permissions
     * expressed in "permission".
     *
     * @param permission the {@code Permission} object to compare
     *
     * @return {@code true} if the given permission is implied by this
     * {@code CryptoAllPermissionCollection} object.
     */
    public boolean implies(Permission permission) {
        if (!(permission instanceof CryptoPermission)) {
            return false;
        }
        return all_allowed;
    }

    /**
     * Returns an enumeration of all the {@code CryptoAllPermission}
     * objects in the container.
     *
     * @return an enumeration of all {@code CryptoAllPermission} objects.
     */
    public Enumeration<Permission> elements() {
        Vector<Permission> v = new Vector<>(1);
        if (all_allowed) v.add(CryptoAllPermission.INSTANCE);
        return v.elements();
    }
}
