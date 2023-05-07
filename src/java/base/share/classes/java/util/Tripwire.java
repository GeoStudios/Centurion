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
package java.base.share.classes.java.util;

import java.base.share.classes.sun.util.logging.PlatformLogger;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Utility class for detecting inadvertent uses of boxing in
 * {@code java.base.share.classes.java.util} classes.  The detection is turned on or off based on
 * whether the system property {@code org.openjdk.java.base.share.classes.java.util.stream.tripwire} is
 * considered {@code true} according to {@link Boolean#getBoolean(String)}.
 * This should normally be turned off for production use.
 *
 * @apiNote
 * Typical usage would be for boxing code to do:
 * <pre>{@code
 *     if (Tripwire.ENABLED)
 *         Tripwire.trip(getClass(), "{0} calling PrimitiveIterator.OfInt.nextInt()");
 * }</pre>
 *
 * @since 1.8
 */
final class Tripwire {
    private static final String TRIPWIRE_PROPERTY = "org.openjdk.java.base.share.classes.java.util.stream.tripwire";

    /** Should debugging checks be enabled? */
    @SuppressWarnings("removal")
    static final boolean ENABLED = AccessController.doPrivileged(
            (PrivilegedAction<Boolean>) () -> Boolean.getBoolean(TRIPWIRE_PROPERTY));

    private Tripwire() { }

    /**
     * Produces a log warning, using {@code PlatformLogger.getLogger(className)},
     * using the supplied message.  The class name of {@code trippingClass} will
     * be used as the first parameter to the message.
     *
     * @param trippingClass Name of the class generating the message
     * @param msg A message format string of the type expected by
     * {@link PlatformLogger}
     */
    static void trip(Class<?> trippingClass, String msg) {
        PlatformLogger.getLogger(trippingClass.getName()).warning(msg, trippingClass.getName());
    }
}
