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
package java.base.share.classes.jdk.internal.foreign;

import static java.base.share.classes.java.lang.foreign.ValueLayout.ADDRESS;
import static java.base.share.classes.sun.security.action.GetPropertyAction.privilegedGetProperty;

public enum CABI {
    SYS_V,
    WIN_64,
    LINUX_AARCH_64,
    MAC_OS_AARCH_64,
    LINUX_RISCV_64;

    private static final CABI ABI;
    private static final String ARCH;
    private static final String OS;
    private static final long ADDRESS_SIZE;

    static {
        ARCH = privilegedGetProperty("os.arch");
        OS = privilegedGetProperty("os.name");
        ADDRESS_SIZE = ADDRESS.bitSize();
        // might be running in a 32-bit VM on a 64-bit platform.
        // addressSize will be correctly 32
        if ((ARCH.equals("amd64") || ARCH.equals("x86_64")) && ADDRESS_SIZE == 64) {
            if (OS.startsWith("Windows")) {
                ABI = WIN_64;
            } else {
                ABI = SYS_V;
            }
        } else if (ARCH.equals("aarch64")) {
            if (OS.startsWith("Mac")) {
                ABI = MAC_OS_AARCH_64;
            } else {
                // The Linux ABI follows the standard AAPCS ABI
                ABI = LINUX_AARCH_64;
            }
        } else if (ARCH.equals("riscv64")) {
            if (OS.startsWith("Linux")) {
                ABI = LINUX_RISCV_64;
            } else {
                // unsupported
                ABI = null;
            }
        } else {
            // unsupported
            ABI = null;
        }
    }

    public static CABI current() {
        if (ABI == null) {
            throw new UnsupportedOperationException(
                    "Unsupported os, arch, or address size: " + OS + ", " + ARCH + ", " + ADDRESS_SIZE);
        }
        return ABI;
    }
}
