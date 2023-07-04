/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package jdk.internal.org.jline.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Supplier;
//import java.util.logging.Level;
//import java.util.logging.LogRecord;
//import java.util.logging.Logger;

/**
 * Internal logger.
 *
 */
public final class Log
{
    public static void trace(final Object... messages) {
//        log(Level.FINEST, messages);
    }

    public static void trace(Supplier<String> supplier) {
//        log(Level.FINEST, supplier);
    }

    public static void debug(Supplier<String> supplier) {
//        log(Level.FINE, supplier);
    }

    public static void debug(final Object... messages) {
//        log(Level.FINE, messages);
    }

    public static void info(final Object... messages) {
//        log(Level.INFO, messages);
    }

    public static void warn(final Object... messages) {
//        log(Level.WARNING, messages);
    }

    public static void error(final Object... messages) {
//        log(Level.SEVERE, messages);
    }

    public static boolean isDebugEnabled() {
//        return isEnabled(Level.FINE);
        return false;
    }

    /**
     * Helper to support rendering messages.
     */
    static void render(final PrintStream out, final Object message) {
        if (message != null && message.getClass().isArray()) {
            Object[] array = (Object[]) message;

            out.print("[");
            for (int i = 0; i < array.length; i++) {
                out.print(array[i]);
                if (i + 1 < array.length) {
                    out.print(",");
                }
            }
            out.print("]");
        }
        else {
            out.print(message);
        }
    }

//    static LogRecord createRecord(final Level level, final Object... messages) {
//        Throwable cause = null;
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        PrintStream ps = new PrintStream(baos);
//        for (int i = 0; i < messages.length; i++) {
//            // Special handling for the last message if its a throwable, render its stack on the next line
//            if (i + 1 == messages.length && messages[i] instanceof Throwable) {
//                cause = (Throwable) messages[i];
//            }
//            else {
//                render(ps, messages[i]);
//            }
//        }
//        ps.close();
//        LogRecord r = new LogRecord(level, baos.toString());
//        r.setThrown(cause);
//        return r;
//    }
//
//    static LogRecord createRecord(final Level level, final Supplier<String> message) {
//        return new LogRecord(level, message.get());
//    }
//
//    static void log(final Level level, final Supplier<String> message) {
//        logr(level, () -> createRecord(level, message));
//    }
//
//    static void log(final Level level, final Object... messages) {
//        logr(level, () -> createRecord(level, messages));
//    }
//
//    static void logr(final Level level, final Supplier<LogRecord> record) {
//        Logger logger = Logger.getLogger("org.jline");
//        if (logger.isLoggable(level)) {
//            // inform record of the logger-name
//            LogRecord tmp = record.get();
//            tmp.setLoggerName(logger.getName());
//            logger.log(tmp);
//        }
//    }
//
//    static boolean isEnabled(Level level) {
//        Logger logger = Logger.getLogger("org.jline");
//        return logger.isLoggable(level);
//    }

}
