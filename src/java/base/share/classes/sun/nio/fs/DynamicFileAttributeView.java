/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.nio.fs;

import java.util.Map;
import java.io.IOException;

/**
 * Implemented by FileAttributeView implementations to support access to
 * attributes by names.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

interface DynamicFileAttributeView {
    /**
     * Sets/updates the value of an attribute.
     */
    void setAttribute(String attribute, Object value) throws IOException;

    /**
     * Reads a set of file attributes as a bulk operation.
     */
    Map<String,Object> readAttributes(String[] attributes) throws IOException;
}
