/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.nio.cs;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CoderResult;

/**
 * A decoder that can be delegated to by another decoder
 * when normal inheritance cannot be used.
 * Used by autodecting decoders.
 * 
 * @since Java 2
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */
public interface DelegatableDecoder {
    CoderResult decodeLoop(ByteBuffer src, CharBuffer dst);
    void implReset();
    CoderResult implFlush(CharBuffer out);
}
