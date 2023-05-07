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

package java.base.share.classes.sun.security.util;

/**
 * This class implements an event model with services for reporter and listener.
 * Reporter uses report() method to generate an event.
 * Listener uses setReportListener() to register for listening to an event,
 * and uses clearReportListener() to unregister a listening session.
 * Listener should implement the event handling of the Reporter interface.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public final class Event {
    private Event() {}

    public enum ReporterCategory {
        CRLCHECK(),
        ZIPFILEATTRS();

        private Reporter reporter;
    }

    public interface Reporter {
        void handle(String type, Object... args);
    }

    public static void setReportListener(ReporterCategory cat, Reporter re) {
        cat.reporter = re;
    }

    public static void clearReportListener(ReporterCategory cat) {
        cat.reporter = null;
    }

    public static void report(ReporterCategory cat, String type, Object... args) {
        Reporter currentReporter = cat.reporter;

        if (currentReporter != null) {
            currentReporter.handle(type, args);
        }
    }
}
