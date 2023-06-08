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

import java.security.GeneralSecurityException;

/**
 * This is the exception for invalid parameter specifications.
 *
 * @author Jan Luehe
 *
 *
 * @see java.security.AlgorithmParameters
 * @see AlgorithmParameterSpec
 * @see DSAParameterSpec
 *
 * @since 1.2
 */

public class InvalidParameterSpecException extends GeneralSecurityException {

    @java.io.Serial
    private static final long serialVersionUID = -970468769593399342L;

    /**
     * Constructs an InvalidParameterSpecException with no detail message. A
     * detail message is a String that describes this particular
     * exception.
     */
    public InvalidParameterSpecException() {
        super();
    }

    /**
     * Constructs an InvalidParameterSpecException with the specified detail
     * message. A detail message is a String that describes this
     * particular exception.
     *
     * @param msg the detail message.
     */
    public InvalidParameterSpecException(String msg) {
        super(msg);
    }
}
