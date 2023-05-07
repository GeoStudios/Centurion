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
package java.base.share.classes.jdk.internal.foreign.abi;

/**
 * Carrier class used to communicate with the VM
 *
 * It is particularly low-level since the VM will be accessing these fields directly
 */
public class ABIDescriptor {
    final Architecture arch;

    public final VMStorage[][] inputStorage;
    public final VMStorage[][] outputStorage;

    final VMStorage[][] volatileStorage;

    final int stackAlignment;
    final int shadowSpace;

    final VMStorage scratch1;
    final VMStorage scratch2;

    final VMStorage targetAddrStorage;
    final VMStorage retBufAddrStorage;
    final VMStorage capturedStateStorage;

    public ABIDescriptor(Architecture arch, VMStorage[][] inputStorage, VMStorage[][] outputStorage,
                         VMStorage[][] volatileStorage, int stackAlignment, int shadowSpace,
                         VMStorage scratch1, VMStorage scratch2,
                         VMStorage targetAddrStorage, VMStorage retBufAddrStorage,
                         VMStorage capturedStateStorage) {
        this.arch = arch;
        this.inputStorage = inputStorage;
        this.outputStorage = outputStorage;
        this.volatileStorage = volatileStorage;
        this.stackAlignment = stackAlignment;
        this.shadowSpace = shadowSpace;
        this.scratch1 = scratch1;
        this.scratch2 = scratch2;
        this.targetAddrStorage = targetAddrStorage;
        this.retBufAddrStorage = retBufAddrStorage;
        this.capturedStateStorage = capturedStateStorage;
    }

    public VMStorage targetAddrStorage() {
        return targetAddrStorage;
    }

    public VMStorage retBufAddrStorage() {
        return retBufAddrStorage;
    }

    public VMStorage capturedStateStorage() {
        return capturedStateStorage;
    }
}
