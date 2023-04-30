/*
 * Copyright (c) 2023 Geo-Studios - All Rights Reserved.
 */

package java.base.share.classes.sun.net;

import java.io.*;

/**
 * An unexpected result was received by the client when talking to the
 * telnet server.
 *
 * @author      Jonathan Payne
 */

public class TelnetProtocolException extends IOException {
    @java.io.Serial
    private static final long serialVersionUID = 8509127047257111343L;

    public TelnetProtocolException(String s) {
        super(s);
    }
}
