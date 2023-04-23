/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package sun.nio.ch;

/**
 * Implemented by asynchronous channels that can be associated with an
 * asynchronous channel group.
 */

interface Groupable {
    AsynchronousChannelGroupImpl group();
}
