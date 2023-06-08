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

class Extension {
    private final char key;
    private String value, id;

    protected Extension(char key) {
        this.key = key;
    }

    Extension(char key, String value) {
        this.key = key;
        setValue(value);
    }

    protected void setValue(String value) {
        this.value = value;
        this.id = key + LanguageTag.SEP + value;
    }

    public char getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getID() {
        return id;
    }

    public String toString() {
        return getID();
    }
}
