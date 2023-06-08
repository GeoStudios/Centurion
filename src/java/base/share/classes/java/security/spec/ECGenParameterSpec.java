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

/**
 * This immutable class specifies the set of parameters used for
 * generating elliptic curve (EC) domain parameters.
 *
 * @see AlgorithmParameterSpec
 *
 * @author Valerie Peng
 *
 * @since 1.5
 */
public class ECGenParameterSpec extends NamedParameterSpec {

    /**
     * Creates a parameter specification for EC parameter
     * generation using a standard (or predefined) name
     * {@code stdName} in order to generate the corresponding
     * (precomputed) elliptic curve domain parameters. For the
     * list of supported names, please consult the documentation
     * of the provider whose implementation will be used.
     *
     * @param stdName the standard name of the to-be-generated EC
     *        domain parameters. See the ParameterSpec Names section in the
     *        <a href=
     *        "{@docRoot}/../specs/security/standard-names.html#parameterspec-names">
     *        Java Security Standard Algorithm Names Specification</a> for
     *        information about standard names.
     * @throws NullPointerException if {@code stdName} is null.
     */
    public ECGenParameterSpec(String stdName) {
        super(stdName);
    }
}

