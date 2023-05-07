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

package java.base.share.classes.jdk.internal.logger;

import java.util.function.Function;
import java.base.share.classes.sun.util.logging.PlatformLogger;

/**
 * A simple console logger used to emulate the behavior of JUL loggers when
 * java.util.logging has no custom configuration.
 * Surrogate loggers are usually only used temporarily, until the LogManager
 * is initialized. At this point, the surrogates are replaced by an actual
 * logger obtained from LogManager.
 */
public final class SurrogateLogger extends SimpleConsoleLogger {

    private static final PlatformLogger.Level JUL_DEFAULT_LEVEL =
            PlatformLogger.Level.INFO;
    private static volatile String simpleFormatString;

    SurrogateLogger(String name) {
        super(name, true);
    }

    @Override
    PlatformLogger.Level defaultPlatformLevel() {
        return JUL_DEFAULT_LEVEL;
    }

    @Override
    String getSimpleFormatString() {
        if (simpleFormatString == null) {
            simpleFormatString = getSimpleFormat(null);
        }
        return simpleFormatString;
    }

    public static String getSimpleFormat(Function<String, String> defaultPropertyGetter) {
        return Formatting.getSimpleFormat(Formatting.JUL_FORMAT_PROP_KEY, defaultPropertyGetter);
    }

    public static SurrogateLogger makeSurrogateLogger(String name) {
        return new SurrogateLogger(name);
    }

    public static boolean isFilteredFrame(StackWalker.StackFrame st) {
        return Formatting.isFilteredFrame(st);
    }
}
