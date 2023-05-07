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

package java.base.share.classes.jdk.internal.vm;

public final class StackChunk {
    public static void init() {}

    private StackChunk parent;
    private int size;    // in words
    private int sp;      // in words
    private int argsize; // bottom stack-passed arguments, in words

    // The stack itself is appended here by the VM, as well as some injected fields

    public StackChunk parent() { return parent; }
    public boolean isEmpty()   { return sp >= (size - argsize); }
}
