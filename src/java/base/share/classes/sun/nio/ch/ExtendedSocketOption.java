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

package java.base.share.classes.sun.nio.ch;

import java.net.SocketOption;

/**
 * Defines socket options that are supported by the implementation
 * but not defined in StandardSocketOptions.
 */

class ExtendedSocketOption {
    private ExtendedSocketOption() { }

    static final SocketOption<Boolean> SO_OOBINLINE =
        new SocketOption<Boolean>() {
            public String name() { return "SO_OOBINLINE"; }
            public Class<Boolean> type() { return Boolean.class; }
            public String toString() { return name(); }
        };
}
