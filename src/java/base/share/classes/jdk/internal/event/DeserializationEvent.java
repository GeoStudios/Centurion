/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package jdk.internal.event;

/**
 * Event details relating to deserialization.
 */

public final class DeserializationEvent extends Event {
    public boolean filterConfigured;
    public String filterStatus;
    public Class<?> type;
    public int arrayLength;
    public long objectReferences;
    public long depth;
    public long bytesRead;
    public Class<?> exceptionType;
    public String exceptionMessage;
}
