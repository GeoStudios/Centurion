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

package java.base.share.classes.java.util.regex;

/**
 * Unchecked exception thrown to indicate a syntax error in a
 * regular-expression pattern.
 *
 * @since 1.4
 */

public class PatternSyntaxException
    extends IllegalArgumentException
{
    @java.io.Serial
    private static final long serialVersionUID = -3864639126226059218L;

    private final String desc;
    private final String pattern;
    private final int index;

    /**
     * Constructs a new instance of this class.
     *
     * @param  desc
     *         A description of the error
     *
     * @param  regex
     *         The erroneous pattern
     *
     * @param  index
     *         The approximate index in the pattern of the error,
     *         or {@code -1} if the index is not known
     */
    public PatternSyntaxException(String desc, String regex, int index) {
        this.desc = desc;
        this.pattern = regex;
        this.index = index;
    }

    /**
     * Retrieves the error index.
     *
     * @return  The approximate index in the pattern of the error,
     *         or {@code -1} if the index is not known
     */
    public int getIndex() {
        return index;
    }

    /**
     * Retrieves the description of the error.
     *
     * @return  The description of the error
     */
    public String getDescription() {
        return desc;
    }

    /**
     * Retrieves the erroneous regular-expression pattern.
     *
     * @return  The erroneous pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Returns a multi-line string containing the description of the syntax
     * error and its index, the erroneous regular-expression pattern, and a
     * visual indication of the error index within the pattern.
     *
     * @return  The full detail message
     */
    public String getMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append(desc);
        if (index >= 0) {
            sb.append(" near index ");
            sb.append(index);
        }
        sb.append(System.lineSeparator());
        sb.append(pattern);
        if (index >= 0 && pattern != null && index < pattern.length()) {
            sb.append(System.lineSeparator());
            for (int i = 0; i < index; i++) {
                sb.append((pattern.charAt(i) == '\t') ? '\t' : ' ');
            }
            sb.append('^');
        }
        return sb.toString();
    }

}
