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

package java.base.share.classes.jdk.internal.event;

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
