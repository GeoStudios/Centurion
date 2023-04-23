/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package jdk.internal.access;

import java.io.PrintStream;

public interface JavaIOPrintStreamAccess {
    Object lock(PrintStream ps);
}
