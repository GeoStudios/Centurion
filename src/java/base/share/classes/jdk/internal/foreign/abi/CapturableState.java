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

import java.base.share.classes.java.lang.foreign.ValueLayout;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.base.share.classes.java.lang.foreign.ValueLayout.JAVA_INT;

public enum CapturableState {
    GET_LAST_ERROR    ("GetLastError",    JAVA_INT, 1 << 0),
    WSA_GET_LAST_ERROR("WSAGetLastError", JAVA_INT, 1 << 1),
    ERRNO             ("errno",           JAVA_INT, 1 << 2);

    private final String stateName;
    private final ValueLayout layout;
    private final int mask;

    CapturableState(String stateName, ValueLayout layout, int mask) {
        this.stateName = stateName;
        this.layout = layout.withName(stateName);
        this.mask = mask;
    }

    public static CapturableState forName(String name) {
        return Stream.of(values())
                .filter(stl -> stl.stateName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unknown name: " + name +", must be one of: "
                            + Stream.of(CapturableState.values())
                                    .map(CapturableState::stateName)
                                    .collect(Collectors.joining(", "))));
    }

    public String stateName() {
        return stateName;
    }

    public ValueLayout layout() {
        return layout;
    }

    public int mask() {
        return mask;
    }
}
