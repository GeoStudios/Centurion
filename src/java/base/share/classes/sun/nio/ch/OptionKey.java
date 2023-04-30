/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.nio.ch;

/**
 * Represents the level/name of a socket option
 */

class OptionKey {
    private int level;
    private int name;

    OptionKey(int level, int name) {
        this.level = level;
        this.name = name;
    }

    int level() {
        return level;
    }

    int name() {
        return name;
    }
}
