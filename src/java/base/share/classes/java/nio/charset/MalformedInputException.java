/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.nio.charset;


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
