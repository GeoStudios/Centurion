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

package java.base.share.classes.java.nio.charset;

/**
 * Checked exception thrown when an input byte sequence is not legal for given
 * charset, or an input character sequence is not a legal sixteen-bit Unicode
 * sequence.
 *
 * @since 1.4
 */

public class MalformedInputException
    extends CharacterCodingException
{

    @java.io.Serial
    private static final long serialVersionUID = -3438823399834806194L;

    /**
     * The length of the input.
     */
    private int inputLength;

    /**
     * Constructs an {@code MalformedInputException} with the given
     * length.
     * @param inputLength the length of the input
     */
    public MalformedInputException(int inputLength) {
        this.inputLength = inputLength;
    }

    /**
     * Returns the length of the input.
     * @return the length of the input
     */
    public int getInputLength() {
        return inputLength;
    }

    /**
     * Returns the message.
     * @return the message
     */
    public String getMessage() {
        return "Input length = " + inputLength;
    }

}
