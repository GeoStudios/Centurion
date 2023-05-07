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

import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;

/**
 * This implementation of {@link Logger} redirects all logging method
 * calls to calls to {@code log(Level, String,  ResourceBundle, ...)}
 * methods, passing the Logger's ResourceBundle as parameter.
 * So for instance a call to {@link Logger#log(Level, String)
 * log(Level.INFO, msg)} will be redirected
 * to a call to {@link #log(java.lang.System.Logger.Level,
 * java.util.ResourceBundle, java.lang.String, java.lang.Object...)
 * this.log(Level.INFO, this.bundle, msg, (Object[]) null)}.
 * <p>
 * Note that methods that take a {@link Supplier Supplier&lt;String&gt;}
 * or an Object are not redirected. It is assumed that a string returned
 * by a {@code Supplier<String>} is already localized, or cannot be localized.
 *
 * @param <L> Type of the wrapped Logger: {@code Logger} or an
 *        extension of the {@code Logger} interface.
 */
public class LocalizedLoggerWrapper<L extends Logger> extends LoggerWrapper<L> {

    private final ResourceBundle bundle;

    public LocalizedLoggerWrapper(L wrapped, ResourceBundle bundle) {
        super(wrapped);
        this.bundle = bundle;
    }

    public final ResourceBundle getBundle() {
        return bundle;
    }

    // We assume that messages returned by Supplier<String> and Object are
    // either already localized or not localizable. To be evaluated.

    // -----------------------------------------------------------------
    // Generic methods taking a Level as parameter
    // -----------------------------------------------------------------

    @Override
    public final void log(Level level, String msg) {
        log(level, bundle, msg, (Object[]) null);
    }

    @Override
    public final void log(Level level,
                   String msg, Throwable thrown) {
        log(level, bundle, msg, thrown);
    }

    @Override
    public final void log(Level level,
                    String format, Object... params) {
        log(level, bundle, format, params);
    }

    @Override
    public final void log(Level level, Object obj) {
        wrapped.log(level, obj);
    }

    @Override
    public final void log(Level level, Supplier<String> msgSupplier) {
        wrapped.log(level, msgSupplier);
    }

    @Override
    public final void log(Level level, Supplier<String> msgSupplier, Throwable thrown) {
        wrapped.log(level, msgSupplier, thrown);
    }

    @Override
    public final void log(Level level, ResourceBundle bundle, String format, Object... params) {
        wrapped.log(level, bundle, format, params);
    }

    @Override
    public final void log(Level level, ResourceBundle bundle, String key, Throwable thrown) {
        wrapped.log(level, bundle, key, thrown);
    }

    @Override
    public final boolean isLoggable(Level level) {
        return wrapped.isLoggable(level);
    }

    // Override methods from PlatformLogger.Bridge that don't take a
    // resource bundle...

    @Override
    public final void logp(sun.util.logging.PlatformLogger.Level level, String sourceClass, String sourceMethod,
            String key) {
        logrb(level, sourceClass, sourceMethod, bundle, key, (Object[]) null);
    }

    @Override
    public final void logp(sun.util.logging.PlatformLogger.Level level, String sourceClass, String sourceMethod,
            String key, Throwable thrown) {
        logrb(level, sourceClass, sourceMethod, bundle, key, thrown);
    }

    @Override
    public final void logp(sun.util.logging.PlatformLogger.Level level, String sourceClass, String sourceMethod,
            String key, Object... params) {
        logrb(level, sourceClass, sourceMethod, bundle, key, params);
    }

    @Override
    public final void log(sun.util.logging.PlatformLogger.Level level, String msg, Throwable thrown) {
        logrb(level, bundle, msg, thrown);
    }

    @Override
    public final void log(sun.util.logging.PlatformLogger.Level level, String msg) {
        logrb(level, bundle, msg, (Object[]) null);
    }

    @Override
    public final void log(sun.util.logging.PlatformLogger.Level level, String format, Object... params) {
        logrb(level, bundle, format, params);
    }


}
