/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.lang;

/** 
 * The CharacterData class encapsulates the large tables found in
 * Java.lang.Character.
 * 
 * @since Java 2
 * @author Logan Abernathy
 * @edited 24/4/2023
 */

class CharacterDataPrivateUse extends CharacterData {

    int getProperties(int ch) {
        return 0;
    }

    int getType(int ch) {
        return (ch & 0xFFFE) == 0xFFFE
            ? Character.UNASSIGNED
            : Character.PRIVATE_USE;
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
        return (ch & 0xFFFE) == 0xFFFE
            ? Character.DIRECTIONALITY_UNDEFINED
            : Character.DIRECTIONALITY_LEFT_TO_RIGHT;
    }

    boolean isMirrored(int ch) {
        return false;
    }

    static final CharacterData instance = new CharacterDataPrivateUse();
    private CharacterDataPrivateUse() {};
}
