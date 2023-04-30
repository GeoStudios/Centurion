/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.jdk.internal.access;

import java.io.ObjectInputFilter;

/**
 * Access to the alternative ObjectInputFilter.Config.createFilter2 for RMI.
 */
public interface JavaObjectInputFilterAccess {
    /**
     * Creates a filter from the pattern.
     */
    ObjectInputFilter createFilter2(String pattern);
}
