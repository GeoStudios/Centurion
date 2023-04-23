/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.security;

/**
 * A marker interface for parameters used in various {@code SecureRandom}
 * methods.
 * <p>
 * Some {@code SecureRandom} implementations might require additional
 * operational parameters. Objects of classes which implement this interface
 * can be passed to those implementations that support them.
 *
 * @see DrbgParameters
 * @since 9
 */
public interface SecureRandomParameters {
}
