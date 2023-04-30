/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.jdk.internal.foreign.abi;

// must keep in sync with StubLocations in VM code
public enum StubLocations {
    TARGET_ADDRESS,
    RETURN_BUFFER,
    CAPTURED_STATE_BUFFER;

    public VMStorage storage(byte type) {
        return new VMStorage(type, (short) 8, ordinal());
    }
}
