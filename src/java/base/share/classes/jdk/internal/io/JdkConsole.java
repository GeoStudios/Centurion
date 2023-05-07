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

import java.io.PrintWriter;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * Delegate interface for custom Console implementations.
 * Methods defined here duplicates the ones in Console class.
 * Providers should implement java.base.share.classes.jdk.internal.io.JdkConsoleProvider
 * to instantiate an implementation of this interface.
 */
public interface JdkConsole {
    PrintWriter writer();
    Reader reader();
    JdkConsole format(String fmt, Object ... args);
    JdkConsole printf(String format, Object ... args);
    String readLine(String fmt, Object ... args);
    String readLine();
    char[] readPassword(String fmt, Object ... args);
    char[] readPassword();
    void flush();
    Charset charset();
}
