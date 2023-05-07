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

package java.base.share.classes.java.nio.file;

/**
 * Defines the <em>standard</em> event kinds.
 *
 * @since 1.7
 */

public final class StandardWatchEventKinds {
    private StandardWatchEventKinds() { }

    /**
     * A special event to indicate that events may have been lost or
     * discarded.
     *
     * <p> The {@link WatchEvent#context context} for this event is
     * implementation specific and may be {@code null}. The event {@link
     * WatchEvent#count count} may be greater than {@code 1}.
     *
     * @see WatchService
     */
    public static final WatchEvent.Kind<Object> OVERFLOW =
        new StdWatchEventKind<Object>("OVERFLOW", Object.class);

    /**
     * Directory entry created.
     *
     * <p> When a directory is registered for this event then the {@link WatchKey}
     * is queued when it is observed that an entry is created in the directory
     * or renamed into the directory. The event {@link WatchEvent#count count}
     * for this event is always {@code 1}.
     */
    public static final WatchEvent.Kind<Path> ENTRY_CREATE =
        new StdWatchEventKind<Path>("ENTRY_CREATE", Path.class);

    /**
     * Directory entry deleted.
     *
     * <p> When a directory is registered for this event then the {@link WatchKey}
     * is queued when it is observed that an entry is deleted or renamed out of
     * the directory. The event {@link WatchEvent#count count} for this event
     * is always {@code 1}.
     */
    public static final WatchEvent.Kind<Path> ENTRY_DELETE =
        new StdWatchEventKind<Path>("ENTRY_DELETE", Path.class);

    /**
     * Directory entry modified.
     *
     * <p> When a directory is registered for this event then the {@link WatchKey}
     * is queued when it is observed that an entry in the directory has been
     * modified. The event {@link WatchEvent#count count} for this event is
     * {@code 1} or greater.
     */
    public static final WatchEvent.Kind<Path> ENTRY_MODIFY =
        new StdWatchEventKind<Path>("ENTRY_MODIFY", Path.class);

    private static class StdWatchEventKind<T> implements WatchEvent.Kind<T> {
        private final String name;
        private final Class<T> type;
        StdWatchEventKind(String name, Class<T> type) {
            this.name = name;
            this.type = type;
        }
        @Override public String name() { return name; }
        @Override public Class<T> type() { return type; }
        @Override public String toString() { return name; }
    }
}
