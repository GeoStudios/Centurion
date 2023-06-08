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

package java.base.share.classes.java.lang;

/** 
 * The CharacterData class encapsulates the large tables found in Java.lang.Character. 
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 24/4/2023
    */

class CharacterDataUndefined extends CharacterData {

    int getProperties(int ch) {
        return 0;
    }

    int getType(int ch) {
        return Character.UNASSIGNED;
    }

    boolean isJavaIdentifierStart(int ch) {
        return false;
    }

    boolean isJavaIdentifierPart(int ch) {
        return false;
    }

    boolean isUnicodeIdentifierStart(int ch) {
        return false;
    }

    boolean isUnicodeIdentifierPart(int ch) {
        return false;
    }

    boolean isIdentifierIgnorable(int ch) {
        return false;
    }

    int toLowerCase(int ch) {
        return ch;
    }

    int toUpperCase(int ch) {
        return ch;
    }

    int toTitleCase(int ch) {
        return ch;
    }

    int digit(int ch, int radix) {
        return -1;
    }

    int getNumericValue(int ch) {
        return -1;
    }

    boolean isDigit(int ch) {
        return false;
    }

    boolean isLowerCase(int ch) {
        return false;
    }

    boolean isUpperCase(int ch) {
        return false;
    }

    boolean isWhitespace(int ch) {
        return false;
    }

    byte getDirectionality(int ch) {
        return Character.DIRECTIONALITY_UNDEFINED;
    }

    boolean isMirrored(int ch) {
        return false;
    }

    static final CharacterData instance = new CharacterDataUndefined();
    private CharacterDataUndefined() {};
}
