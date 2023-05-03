/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.util;

/**
 * This class implements an event model with services for reporter and listener.
 * Reporter uses report() method to generate an event.
 * Listener uses setReportListener() to register for listening to an event,
 * and uses clearReportListener() to unregister a listening session.
 * Listener should implement the event handling of the Reporter interface.
 * 
 * @since Java 2
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
