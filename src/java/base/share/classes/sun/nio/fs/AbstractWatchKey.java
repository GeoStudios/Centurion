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

package java.base.share.classes.sun.nio.fs;

import java.nio.file.*;
import java.util.*;

/**
 * Base implementation class for watch keys.
 * 
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

abstract class AbstractWatchKey implements WatchKey {

    /**
     * Maximum size of event list (in the future this may be tunable)
     */
    static final int MAX_EVENT_LIST_SIZE    = 512;

    /**
     * Special event to signal overflow
     */
    static final Event<Object> OVERFLOW_EVENT =
        new Event<Object>(StandardWatchEventKinds.OVERFLOW, null);

    /**
     * Possible key states
     */
    private static enum State { READY, SIGNALLED };

    // reference to watcher
    private final AbstractWatchService watcher;

    // reference to the original directory
    private final Path dir;

    // key state
    private State state;

    // pending events
    private List<WatchEvent<?>> events;

    // maps a context to the last event for the context (iff the last queued
    // event for the context is an ENTRY_MODIFY event).
    private Map<Object,WatchEvent<?>> lastModifyEvents;

    protected AbstractWatchKey(Path dir, AbstractWatchService watcher) {
        this.watcher = watcher;
        this.dir = dir;
        this.state = State.READY;
        this.events = new ArrayList<>();
        this.lastModifyEvents = new HashMap<>();
    }

    final AbstractWatchService watcher() {
        return watcher;
    }

    /**
     * Return the original watchable (Path)
     */
    @Override
    public Path watchable() {
        return dir;
    }

    /**
     * Enqueues this key to the watch service
     */
    final void signal() {
        synchronized (this) {
            if (state == State.READY) {
                state = State.SIGNALLED;
                watcher.enqueueKey(this);
            }
        }
    }

    /**
     * Adds the event to this key and signals it.
     */
    @SuppressWarnings("unchecked")
    final void signalEvent(WatchEvent.Kind<?> kind, Object context) {
        boolean isModify = (kind == StandardWatchEventKinds.ENTRY_MODIFY);
        synchronized (this) {
            int size = events.size();
            if (size > 0) {
                // if the previous event is an OVERFLOW event or this is a
                // repeated event then we simply increment the counter
                WatchEvent<?> prev = events.get(size-1);
                if ((prev.kind() == StandardWatchEventKinds.OVERFLOW) ||
                    ((kind == prev.kind() &&
                     Objects.equals(context, prev.context()))))
                {
                    ((Event<?>)prev).increment();
                    return;
                }

                // if this is a modify event and the last entry for the context
                // is a modify event then we simply increment the count
                if (!lastModifyEvents.isEmpty()) {
                    if (isModify) {
                        WatchEvent<?> ev = lastModifyEvents.get(context);
                        if (ev != null) {
                            assert ev.kind() == StandardWatchEventKinds.ENTRY_MODIFY;
                            ((Event<?>)ev).increment();
                            return;
                        }
                    } else {
                        // not a modify event so remove from the map as the
                        // last event will no longer be a modify event.
                        lastModifyEvents.remove(context);
                    }
                }

                // if the list has reached the limit then drop pending events
                // and queue an OVERFLOW event
                if (size >= MAX_EVENT_LIST_SIZE) {
                    kind = StandardWatchEventKinds.OVERFLOW;
                    isModify = false;
                    context = null;
                }
            }

            // non-repeated event
            Event<Object> ev =
                new Event<>((WatchEvent.Kind<Object>)kind, context);
            if (isModify) {
                lastModifyEvents.put(context, ev);
            } else if (kind == StandardWatchEventKinds.OVERFLOW) {
                // drop all pending events
                events.clear();
                lastModifyEvents.clear();
            }
            events.add(ev);
            signal();
        }
    }

    @Override
    public final List<WatchEvent<?>> pollEvents() {
        synchronized (this) {
            List<WatchEvent<?>> result = events;
            events = new ArrayList<>();
            lastModifyEvents.clear();
            return result;
        }
    }

    @Override
    public final boolean reset() {
        synchronized (this) {
            if (state == State.SIGNALLED && isValid()) {
                if (events.isEmpty()) {
                    state = State.READY;
                } else {
                    // pending events so re-queue key
                    watcher.enqueueKey(this);
                }
            }
            return isValid();
        }
    }

    /**
     * WatchEvent implementation
     */
    private static class Event<T> implements WatchEvent<T> {
        private final WatchEvent.Kind<T> kind;
        private final T context;

        // synchronize on watch key to access/increment count
        private int count;

        Event(WatchEvent.Kind<T> type, T context) {
            this.kind = type;
            this.context = context;
            this.count = 1;
        }

        @Override
        public WatchEvent.Kind<T> kind() {
            return kind;
        }

        @Override
        public T context() {
            return context;
        }

        @Override
        public int count() {
            return count;
        }

        // for repeated events
        void increment() {
            count++;
        }
    }
}
