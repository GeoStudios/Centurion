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

package ca.weblite.objc;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface RuntmeArm64Extensions extends Library {
    public static RuntmeArm64Extensions INSTANCE = (RuntmeArm64Extensions) Native.loadLibrary("objc.A", RuntmeArm64Extensions.class);
    public double objc_msgSend(Pointer theReceiver, Pointer theSelector);
    public double objc_msgSend(Pointer theReceiver, Pointer theSelector,Object arg);
    public double objc_msgSend(Pointer theReceiver, Pointer theSelector,Object arg, Object arg2);
    public double objc_msgSend(Pointer theReceiver, Pointer theSelector,Object arg, Object arg2, Object arg3);
    public double objc_msgSend(Pointer theReceiver, Pointer theSelector,Object arg, Object arg2, Object arg3, Object arg4);
}