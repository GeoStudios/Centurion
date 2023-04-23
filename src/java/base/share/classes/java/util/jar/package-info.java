/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

/**
 * Provides classes for reading and writing the JAR (Java ARchive)
 * file format, which is based on the standard ZIP file format with an
 * optional manifest file.  The manifest stores meta-information about
 * the JAR file contents and is also used for signing JAR files.
 *
 * <h2>Package Specification</h2>
 *
 * The {@code java.util.jar} package is based on the following
 * specifications:
 *
 * <ul>
 *   <li><b>Info-ZIP file format</b> - The JAR format is based on the Info-ZIP
 *       file format. See
 *       <a href="../zip/package-summary.html#package-description">java.util.zip
 *       package description.</a> <p>
 *       In JAR files, all file names must be encoded in the UTF-8 encoding.
 *   <li><a href="{@docRoot}/../specs/jar/jar.html">
 *       Manifest and Signature Specification</a> - The manifest format specification.
 * </ul>
 *
 * @since 1.2
 */
package java.util.jar;
