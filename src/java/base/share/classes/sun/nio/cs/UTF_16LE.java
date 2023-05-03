/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.nio.cs;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @since Java 2
 * @author Logan Abernathy
 * @edited 22/4/2023 
 */

public class UTF_16LE extends Unicode
{

    public UTF_16LE() {
        super("UTF-16LE", StandardCharsets.aliases_UTF_16LE());
    }

    public String historicalName() {
        return "UnicodeLittleUnmarked";
    }

    public CharsetDecoder newDecoder() {
        return new Decoder(this);
    }

    public CharsetEncoder newEncoder() {
        return new Encoder(this);
    }

    private static class Decoder extends UnicodeDecoder {

        public Decoder(Charset cs) {
            super(cs, LITTLE);
        }
    }

    private static class Encoder extends UnicodeEncoder {

        public Encoder(Charset cs) {
            super(cs, LITTLE, false);
        }
    }

}
