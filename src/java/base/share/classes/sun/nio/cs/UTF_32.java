/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.nio.cs;

import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @since Java 2
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

public class UTF_32 extends Unicode
{
    public UTF_32() {
        super("UTF-32", StandardCharsets.aliases_UTF_32());
    }

    public String historicalName() {
        return "UTF-32";
    }

    public CharsetDecoder newDecoder() {
        return new UTF_32Coder.Decoder(this, UTF_32Coder.NONE);
    }

    public CharsetEncoder newEncoder() {
        return new UTF_32Coder.Encoder(this, UTF_32Coder.BIG, false);
    }
}
