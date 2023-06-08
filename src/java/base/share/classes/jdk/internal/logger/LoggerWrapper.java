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

import java.util.Objects;
import java.lang.System.Logger;
import java.base.share.classes.sun.util.logging.PlatformLogger;

/**
 * An implementation of {@link Logger} that redirects all calls to a wrapped
 * instance of Logger.
 *
 * @param <L> Type of the wrapped Logger: {@code Logger} or an
 *            extension of that interface.
 */
public class LoggerWrapper<L extends Logger> extends AbstractLoggerWrapper<L> {

    final L wrapped;
    final PlatformLogger.Bridge platformProxy;

    public LoggerWrapper(L wrapped) {
        this(Objects.requireNonNull(wrapped), (Void)null);
    }

    LoggerWrapper(L wrapped, Void unused) {
        this.wrapped = wrapped;
        this.platformProxy = (wrapped instanceof PlatformLogger.Bridge) ?
            (PlatformLogger.Bridge) wrapped : null;
    }

    @Override
    public final L wrapped() {
        return wrapped;
    }

    @Override
    public final PlatformLogger.Bridge platformProxy() {
        return platformProxy;
    }

}
