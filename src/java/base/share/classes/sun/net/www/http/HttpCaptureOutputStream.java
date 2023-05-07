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

package java.base.share.classes.sun.net.www.http;
import java.io.*;

/**
 * A Simple FilterOutputStream subclass to capture HTTP traffic.
 * Every byte written is also passed to the HttpCapture class.
 *
 * @author jccollet
 */
public class HttpCaptureOutputStream extends FilterOutputStream {
    private HttpCapture capture = null;

    public HttpCaptureOutputStream(OutputStream out, HttpCapture cap) {
        super(out);
        capture = cap;
    }

    @Override
    public void write(int b) throws IOException {
        capture.sent(b);
        out.write(b);
    }

    @Override
    public void write(byte[] ba) throws IOException {
        for (byte b : ba) {
            capture.sent(b);
        }
        out.write(ba);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        for (int i = off; i < len; i++) {
            capture.sent(b[i]);
        }
        out.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        try {
            capture.flush();
        } catch (IOException iOException) {
        }
        super.flush();
    }
}
