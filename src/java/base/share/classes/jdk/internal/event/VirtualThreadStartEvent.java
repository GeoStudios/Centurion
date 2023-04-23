/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */
package jdk.internal.event;

/**
 * Event recording that a virtual thread has been started.
 */
public class VirtualThreadStartEvent extends Event {
    private final static VirtualThreadStartEvent EVENT = new VirtualThreadStartEvent();

    /**
     * Returns {@code true} if event is enabled, {@code false} otherwise.
     */
    public static boolean isTurnedOn() {
        return EVENT.isEnabled();
    }

    public long javaThreadId;
}
