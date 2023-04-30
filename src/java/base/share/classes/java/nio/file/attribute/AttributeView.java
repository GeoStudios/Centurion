/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.nio.file.attribute;

/**
 * An object that provides a read-only or updatable <em>view</em> of non-opaque
 * values associated with an object in a filesystem. This interface is extended
 * or implemented by specific attribute views that define the attributes
 * supported by the view. A specific attribute view will typically define
 * type-safe methods to read or update the attributes that it supports.
 *
 * @since 1.7
 */

public interface AttributeView {
    /**
     * Returns the name of the attribute view.
     *
     * @return the name of the attribute view
     */
    String name();
}
