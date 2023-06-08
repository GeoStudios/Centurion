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

package java.base.share.classes.sun.security.util;

import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.util.Arrays;
import java.base.share.classes.jdk.internal.access.SharedSecrets;

/**
 * A utility class for reading passwords
 *
 * @since Alpha cdk-1.1
 * @author Logan Abernathy
 * @edited 21/4/2023 
 */
public class Password {
    /** Reads user password from given input stream. */
    public static char[] readPassword(InputStream in) throws IOException {
        return readPassword(in, false);
    }

    /** Reads user password from given input stream.
     * @param isEchoOn true if the password should be echoed on the screen
     */
    @SuppressWarnings("fallthrough")
    public static char[] readPassword(InputStream in, boolean isEchoOn)
            throws IOException {

        char[] consoleEntered = null;
        byte[] consoleBytes = null;

        try {
            // Only use Console if `in` is the initial System.in
            Console con;
            if (!isEchoOn &&
                    in == SharedSecrets.getJavaLangAccess().initialSystemIn() &&
                    ((con = System.console()) != null)) {
                consoleEntered = con.readPassword();
                // readPassword returns "" if you just press ENTER with the built-in Console,
                // to be compatible with old Password class, change to null
                if (consoleEntered == null || consoleEntered.length == 0) {
                    return null;
                }
                consoleBytes = convertToBytes(consoleEntered);
                in = new ByteArrayInputStream(consoleBytes);
            }

            // Rest of the lines still necessary for KeyStoreLoginModule
            // and when there is no console.

            char[] lineBuffer;
            char[] buf;

            buf = lineBuffer = new char[128];

            int room = buf.length;
            int offset = 0;
            int c;

            boolean done = false;
            while (!done) {
                switch (c = in.read()) {
                  case -1:
                  case '\n':
                      done = true;
                      break;

                  case '\r':
                    int c2 = in.read();
                    if ((c2 != '\n') && (c2 != -1)) {
                        if (!(in instanceof PushbackInputStream)) {
                            in = new PushbackInputStream(in);
                        }
                        ((PushbackInputStream)in).unread(c2);
                    } else {
                        done = true;
                        break;
                    }
                    /* fall through */
                  default:
                    if (--room < 0) {
                        buf = new char[offset + 128];
                        room = buf.length - offset - 1;
                        System.arraycopy(lineBuffer, 0, buf, 0, offset);
                        Arrays.fill(lineBuffer, ' ');
                        lineBuffer = buf;
                    }
                    buf[offset++] = (char) c;
                    break;
                }
            }

            if (offset == 0) {
                return null;
            }

            char[] ret = new char[offset];
            System.arraycopy(buf, 0, ret, 0, offset);
            Arrays.fill(buf, ' ');

            return ret;
        } finally {
            if (consoleEntered != null) {
                Arrays.fill(consoleEntered, ' ');
            }
            if (consoleBytes != null) {
                Arrays.fill(consoleBytes, (byte)0);
            }
        }
    }

    /**
     * Change a password read from Console.readPassword() into
     * its original bytes.
     *
     * @param pass a char[]
     * @return its byte[] format, similar to new String(pass).getBytes()
     */
    private static byte[] convertToBytes(char[] pass) {
        if (enc == null) {
            synchronized (Password.class) {
                enc = System.console()
                        .charset()
                        .newEncoder()
                        .onMalformedInput(CodingErrorAction.REPLACE)
                        .onUnmappableCharacter(CodingErrorAction.REPLACE);
            }
        }
        byte[] ba = new byte[(int)(enc.maxBytesPerChar() * pass.length)];
        ByteBuffer bb = ByteBuffer.wrap(ba);
        synchronized (enc) {
            enc.reset().encode(CharBuffer.wrap(pass), bb, true);
        }
        if (bb.position() < ba.length) {
            ba[bb.position()] = '\n';
        }
        return ba;
    }
    private static volatile CharsetEncoder enc;
}
