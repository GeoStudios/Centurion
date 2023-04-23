/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

/**
 * Provides for system input and output through data streams,
 * serialization and the file system.
 *
 * Unless otherwise noted, passing a {@code null} argument to a constructor or
 * method in any class or interface in this package will cause a
 * {@code NullPointerException} to be thrown.
 *
 * A <i>pathname string</i> passed as a {@code String} argument to a
 * constructor or method in any class or interface in this package will be
 * interpreted as described in the class specification of {@link File}.
 *
 * <h2>Object Serialization</h2>
 * <p><strong>Warning: Deserialization of untrusted data is inherently dangerous
 * and should be avoided. Untrusted data should be carefully validated according to the
 * "Serialization and Deserialization" section of the
 * {@extLink secure_coding_guidelines_javase Secure Coding Guidelines for Java SE}.
 * </strong></p>
 * <ul>
 *   <li><a href="{@docRoot}/../specs/serialization/index.html">
 *       <cite>Java Object Serialization Specification</cite></a>
 *   <li>{@extLink serialization_filter_guide Serial Filtering} best practices</li>
 *   <li>{@extLink serialver_tool_reference The serialver tool}</li>
 * </ul>
 *
 * @since 1.0
 */
package java.io;
