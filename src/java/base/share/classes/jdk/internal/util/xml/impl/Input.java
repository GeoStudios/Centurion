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

import java.io.Reader;

/**
 * A parsed entity input state.
 *
 * This class represents a parsed entity input state. The parser uses
 * an instance of this class to manage input.
 */

public class Input {

    /** The entity public identifier or null. */
    public String pubid;
    /** The entity system identifier or null. */
    public String sysid;
    /** The encoding from XML declaration or null */
    public String xmlenc;
    /** The XML version from XML declaration or 0x0000 */
    public char xmlver;
    /** The entity reader. */
    public Reader src;
    /** The character buffer. */
    public char[] chars;
    /** The length of the character buffer. */
    public int chLen;
    /** The index of the next character to read. */
    public int chIdx;
    /** The next input in a chain. */
    public Input next;

    /**
     * Constructor.
     *
     * @param buffsize The input buffer size.
     */
    public Input(int buffsize) {
        chars = new char[buffsize];
        chLen = chars.length;
    }

    /**
     * Constructor.
     *
     * @param buff The input buffer.
     */
    public Input(char[] buff) {
        chars = buff;
        chLen = chars.length;
    }

    /**
     * Constructor.
     */
    public Input() {
    }
}
