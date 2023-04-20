/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.util.locale;

/**
 * @since Pre Java 1
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
