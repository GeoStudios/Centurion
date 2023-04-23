/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package jdk.internal.foreign.abi;

public interface Architecture {
    boolean isStackType(int cls);
    int typeSize(int cls);
}
