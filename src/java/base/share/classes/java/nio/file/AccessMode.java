/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.nio.file;

/**
 * Defines access modes used to test the accessibility of a file.
 *
 * @since 1.7
 */

public enum AccessMode {
    /**
     * Test read access.
     */
    READ,
    /**
     * Test write access.
     */
    WRITE,
    /**
     * Test execute access.
     */
    EXECUTE;
}
