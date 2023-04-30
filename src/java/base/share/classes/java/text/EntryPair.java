/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.java.text;

/**
 * This is used for building contracting character tables.  entryName
 * is the contracting character name and value is its collation
 * order.
 */
final class EntryPair
{
    public String entryName;
    public int value;
    public boolean fwd;

    public EntryPair(String name, int value) {
        this(name, value, true);
    }
    public EntryPair(String name, int value, boolean fwd) {
        this.entryName = name;
        this.value = value;
        this.fwd = fwd;
    }
}
