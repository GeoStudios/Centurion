/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
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
