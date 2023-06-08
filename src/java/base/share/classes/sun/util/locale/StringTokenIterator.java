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

package java.base.share.classes.sun.util.locale;

/**
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 20/4/2023 
 */

public class StringTokenIterator {
    private String text;
    private String dlms;        // null if a single char delimiter
    private char delimiterChar; // delimiter if a single char delimiter

    private String token;
    private int start;
    private int end;
    private boolean done;

    public StringTokenIterator(String text, String dlms) {
        this.text = text;
        if (dlms.length() == 1) {
            delimiterChar = dlms.charAt(0);
        } else {
            this.dlms = dlms;
        }
        setStart(0);
    }

    public String first() {
        setStart(0);
        return token;
    }

    public String current() {
        return token;
    }

    public int currentStart() {
        return start;
    }

    public int currentEnd() {
        return end;
    }

    public boolean isDone() {
        return done;
    }

    public String next() {
        if (hasNext()) {
            start = end + 1;
            end = nextDelimiter(start);
            token = text.substring(start, end);
        } else {
            start = end;
            token = null;
            done = true;
        }
        return token;
    }

    public boolean hasNext() {
        return (end < text.length());
    }

    public StringTokenIterator setStart(int offset) {
        if (offset > text.length()) {
            throw new IndexOutOfBoundsException();
        }
        start = offset;
        end = nextDelimiter(start);
        token = text.substring(start, end);
        done = false;
        return this;
    }

    public StringTokenIterator setText(String text) {
        this.text = text;
        setStart(0);
        return this;
    }

    private int nextDelimiter(int start) {
        int textlen = this.text.length();
        if (dlms == null) {
            for (int idx = start; idx < textlen; idx++) {
                if (text.charAt(idx) == delimiterChar) {
                    return idx;
                }
            }
        } else {
            int dlmslen = dlms.length();
            for (int idx = start; idx < textlen; idx++) {
                char c = text.charAt(idx);
                for (int i = 0; i < dlmslen; i++) {
                    if (c == dlms.charAt(i)) {
                        return idx;
                    }
                }
            }
        }
        return textlen;
    }
}
