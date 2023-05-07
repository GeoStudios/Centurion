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
 * A Simple FilterInputStream subclass to capture HTTP traffic.
 * Every byte read is also passed to the HttpCapture class.
 *
 * @author jccollet
 */
public class HttpCaptureInputStream extends FilterInputStream {
    private HttpCapture capture = null;

    public HttpCaptureInputStream(InputStream in, HttpCapture cap) {
        super(in);
        capture = cap;
    }

    @Override
    public int read() throws IOException {
        int i = super.read();
        capture.received(i);
        return i;
    }

    @Override
    public void close() throws IOException {
        try {
            capture.flush();
        } catch (IOException iOException) {
        }
        super.close();
    }

    @Override
    public int read(byte[] b) throws IOException {
        int ret = super.read(b);
        for (int i = 0; i < ret; i++) {
            capture.received(b[i]);
        }
        return ret;
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int ret = super.read(b, off, len);
        for (int i = 0; i < ret; i++) {
            capture.received(b[off+i]);
        }
        return ret;
    }
}
