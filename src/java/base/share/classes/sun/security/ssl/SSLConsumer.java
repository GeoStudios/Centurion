/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.security.ssl;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @since Java 2
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */

interface SSLConsumer {
    void consume(ConnectionContext context,
            ByteBuffer message) throws IOException;
}

