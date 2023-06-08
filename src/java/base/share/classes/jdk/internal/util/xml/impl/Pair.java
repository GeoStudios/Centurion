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

package java.base.share.classes.jdk.internal.util.xml.impl;

/**
 * A name with value pair.
 *
 * This class keeps name with value pair with additional information and
 * supports pair chaining.
 */
public class Pair {

    /** The pair name. */
    public String name;
    /** The pair value. */
    public String value;
    /** The pair numeric value. */
    public int num;
    /** The characters of name. */
    public char[] chars;
    /** The pair identifier. */
    public int id;
    /** The list of associated pairs. */
    public Pair list;
    /** The next pair in a chain. */
    public Pair next;

    /**
     * Creates a qualified name string from qualified name.
     *
     * @return The qualified name string.
     */
    public String qname() {
        return new String(chars, 1, chars.length - 1);
    }

    /**
     * Creates a local name string from qualified name.
     *
     * @return The local name string.
     */
    public String local() {
        if (chars[0] != 0) {
            return new String(chars, chars[0] + 1, chars.length - chars[0] - 1);
        }
        return new String(chars, 1, chars.length - 1);
    }

    /**
     * Creates a prefix string from qualified name.
     *
     * @return The prefix string.
     */
    public String pref() {
        if (chars[0] != 0) {
            return new String(chars, 1, chars[0] - 1);
        }
        return "";
    }

    /**
     * Compares two qualified name prefixes.
     *
     * @param qname A qualified name.
     * @return true if prefixes are equal.
     */
    public boolean eqpref(char[] qname) {
        if (chars[0] == qname[0]) {
            char len = chars[0];
            for (char i = 1; i < len; i += 1) {
                if (chars[i] != qname[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Compares two qualified names.
     *
     * @param qname A qualified name.
     * @return true if qualified names are equal.
     */
    public boolean eqname(char[] qname) {
        char len = (char) chars.length;
        if (len == qname.length) {
            for (char i = 0; i < len; i += 1) {
                if (chars[i] != qname[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
