/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
