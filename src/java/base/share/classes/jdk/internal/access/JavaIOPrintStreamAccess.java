/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package java.base.share.classes.jdk.internal.access;

import java.io.PrintStream;

public interface JavaIOPrintStreamAccess {
    Object lock(PrintStream ps);
}
