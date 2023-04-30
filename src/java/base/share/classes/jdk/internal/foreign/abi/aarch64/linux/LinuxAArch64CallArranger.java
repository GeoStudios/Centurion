/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.jdk.internal.foreign.abi.aarch64.linux;

import jdk.internal.foreign.abi.aarch64.CallArranger;

/**
 * AArch64 CallArranger specialized for Linux ABI.
 */
public class LinuxAArch64CallArranger extends CallArranger {

    @Override
    protected boolean varArgsOnStack() {
        // Variadic arguments are passed as normal arguments
        return false;
    }

    @Override
    protected boolean requiresSubSlotStackPacking() {
        return false;
    }

}
