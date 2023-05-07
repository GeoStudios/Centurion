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
package java.base.share.classes.jdk.internal.foreign.abi.aarch64.macos;

import java.base.share.classes.jdk.internal.foreign.abi.aarch64.CallArranger;

/**
 * AArch64 CallArranger specialized for macOS ABI.
 */
public class MacOsAArch64CallArranger extends CallArranger {

    @Override
    protected boolean varArgsOnStack() {
        // Variadic arguments are always passed on the stack
        return true;
    }

    @Override
    protected boolean requiresSubSlotStackPacking() {
        return true;
    }

}
