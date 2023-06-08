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
package java.base.share.classes.java.security.spec;

import java.util.Objects;

/**
 * This class is used to specify any algorithm parameters that are determined
 * by a standard name. This class also holds constants for standard parameter
 * set names. The names of these constants exactly match the corresponding
 * parameter set name. For example, NamedParameterSpec.X25519 represents the
 * parameter set identified by the string "X25519". These strings are defined
 * in the <a href=
 * "{@docRoot}/../specs/security/standard-names.html#parameterspec-names">
 *          Java Security Standard Algorithm Names Specification</a>.
 *
 * @since 11
 *
 */
public class NamedParameterSpec implements AlgorithmParameterSpec {

   /**
    * The X25519 parameters
    */
    public static final NamedParameterSpec X25519
        = new NamedParameterSpec("X25519");
   /**
    * The X448 parameters
    */
    public static final NamedParameterSpec X448
        = new NamedParameterSpec("X448");

    /**
     * The Ed25519 parameters
     *
     * @since 15
     */
    public static final NamedParameterSpec ED25519
        = new NamedParameterSpec("Ed25519");

    /**
     * The Ed448 parameters
     *
     * @since 15
     */
    public static final NamedParameterSpec ED448
        = new NamedParameterSpec("Ed448");

    private final String name;

    /**
     * Creates a parameter specification using a standard (or predefined)
     * name {@code stdName}. For the
     * list of supported names, please consult the documentation
     * of the provider whose implementation will be used.
     *
     * @param stdName the standard name of the algorithm parameters. See the
     *        ParameterSpec Names section in the
     *        <a href=
     *        "{@docRoot}/../specs/security/standard-names.html#parameterspec-names">
     *        Java Security Standard Algorithm Names Specification</a> for
     *        information about standard names.
     *
     * @throws NullPointerException if {@code stdName} is null.
     */
    public NamedParameterSpec(String stdName) {
        Objects.requireNonNull(stdName, "stdName must not be null");

        this.name = stdName;
    }

    /**
     * Returns the standard name that determines the algorithm parameters.
     * @return the standard name.
     */
    public String getName() {
        return name;
    }
}
