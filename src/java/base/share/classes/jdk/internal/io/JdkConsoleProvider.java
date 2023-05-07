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
package java.base.share.classes.jdk.internal.io;

import java.nio.charset.Charset;

/**
 * Service provider interface for JdkConsole implementations.
 */
public interface JdkConsoleProvider {
    /**
     * The module name of the JdkConsole default provider.
     */
    String DEFAULT_PROVIDER_MODULE_NAME = "java.base";

    /**
     * {@return the Console instance, or {@code null} if not available}
     * @param isTTY indicates if the jvm is attached to a terminal
     * @param charset charset of the platform console
     */
    JdkConsole console(boolean isTTY, Charset charset);
}
