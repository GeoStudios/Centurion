/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package jdk.internal.event;

/**
 * Event recording details of Provider.getService(String type, String algorithm) calls
 */

public final class SecurityProviderServiceEvent extends Event {
    private final static SecurityProviderServiceEvent EVENT = new SecurityProviderServiceEvent();

    /**
     * Returns {@code true} if event is enabled, {@code false} otherwise.
     */
    public static boolean isTurnedOn() {
        return EVENT.isEnabled();
    }

    public String type;
    public String algorithm;
    public String provider;
}
