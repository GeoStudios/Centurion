/*
 * Copyright (c) 2023 Geo-Studios and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 only, as published
 * by the Free Software Foundation. Geo-Studios designates this particular
 * file as subject to the "Classpath" exception as provided
 * by Geo-Studio in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License version 2 for more details (a copy is
 * included in the LICENSE file that accompanied this code).
 *
 * You should have received a copy of the GNU General Public License
 * version 2 along with this work; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package jdk.compiler.share.classes.com.sun.tools.sjavac.server.log;

import jdk.compiler.share.classes.com.sun.tools.sjavac.Log;
import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.java.io.java.io.java.io.IOException;
import java.io.OutputStream;

public class LoggingOutputStream extends FilterOutputStream {

    private static final byte[] LINE_SEP = System.lineSeparator().getBytes();

    private final Log.Level level;
    private final String linePrefix;
    private EolTrackingByteArrayOutputStream buf = new EolTrackingByteArrayOutputStream();

    public LoggingOutputStream(OutputStream out, Log.Level level, String linePrefix) {
        super(out);
        this.level = level;
        this.linePrefix = linePrefix;
    }

    @Override
    public void write(int b) throws IOException {
        super.write(b);
        buf.write(b);
        if (buf.isLineComplete()) {
            String line = new String(buf.toByteArray(), 0, buf.size() - LINE_SEP.length);
            Log.log(level, linePrefix + line);
            buf = new EolTrackingByteArrayOutputStream();
        }
    }

    private static class EolTrackingByteArrayOutputStream extends ByteArrayOutputStream {
        private static final byte[] EOL = System.lineSeparator().getBytes();
        private boolean isLineComplete() {
            if (count < EOL.length) {
                return false;
            }
            for (int i = 0; i < EOL.length; i++) {
                if (buf[count - EOL.length + i] != EOL[i]) {
                    return false;
                }
            }
            return true;
        }
    }
}
