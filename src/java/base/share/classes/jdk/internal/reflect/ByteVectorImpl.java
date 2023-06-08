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

package java.base.share.classes.jdk.internal.reflect;

class ByteVectorImpl implements ByteVector {
    private byte[] data;
    private int pos;

    public ByteVectorImpl() {
        this(100);
    }

    public ByteVectorImpl(int sz) {
        data = new byte[sz];
        pos = -1;
    }

    public int getLength() {
        return pos + 1;
    }

    public byte get(int index) {
        if (index >= data.length) {
            resize(index);
            pos = index;
        }
        return data[index];
    }

    public void put(int index, byte value) {
        if (index >= data.length) {
            resize(index);
            pos = index;
        }
        data[index] = value;
    }

    public void add(byte value) {
        if (++pos >= data.length) {
            resize(pos);
        }
        data[pos] = value;
    }

    public void trim() {
        if (pos != data.length - 1) {
            byte[] newData = new byte[pos + 1];
            System.arraycopy(data, 0, newData, 0, pos + 1);
            data = newData;
        }
    }

    public byte[] getData() {
        return data;
    }

    private void resize(int minSize) {
        if (minSize <= 2 * data.length) {
            minSize = 2 * data.length;
        }
        byte[] newData = new byte[minSize];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }
}
