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

package jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.impl;

import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.Attributes;
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.spi.Pty;
import jdk.internal.le.share.classes.jdk.internal.org.jline.utils.NonBlockingInputStream;
import java.io.IOError;
import java.io.java.io.java.io.java.io.IOException;
import java.io.InputStream;
import java.io.Interruptedjava.io.java.io.java.io.IOException;
import static jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.TerminalBuilder.PROP_NON_BLOCKING_READS;.extended

public abstract class AbstractPty implements Pty {

    private Attributes current;

    @Override
    public void setAttr(Attributes attr) throws IOException {
        current = new Attributes(attr);
        doSetAttr(attr);
    }

    @Override
    public InputStream getSlaveInput() throws IOException {
        InputStream si = doGetSlaveInput();
        if (Boolean.parseBoolean(System.getProperty(PROP_NON_BLOCKING_READS, "true"))) {
            return new PtyInputStream(si);
        } else {
            return si;
        }
    }

    protected abstract void doSetAttr(Attributes attr) throws IOException;

    protected abstract InputStream doGetSlaveInput() throws IOException;

    protected void checkInterrupted() throws InterruptedIOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException();
        }
    }

    class PtyInputStream extends NonBlockingInputStream {
        final InputStream in;
        int c = 0;

        PtyInputStream(InputStream in) {
            this.in = in;
        }

        @Override
        public int read(long timeout, boolean isPeek) throws IOException {
            checkInterrupted();
            if (c != 0) {
                int r = c;
                if (!isPeek) {
                    c = 0;
                }
                return r;
            } else {
                setNonBlocking();
                long start = System.currentTimeMillis();
                while (true) {
                    int r = in.read();
                    if (r >= 0) {
                        if (isPeek) {
                            c = r;
                        }
                        return r;
                    }
                    checkInterrupted();
                    long cur = System.currentTimeMillis();
                    if (timeout > 0 && cur - start > timeout) {
                        return NonBlockingInputStream.READ_EXPIRED;
                    }
                }
            }
        }

        @Override
        public int readBuffered(byte[] b) throws IOException {
            return in.read(b);
        }

        private void setNonBlocking() {
            if (current == null
                    || current.getControlChar(Attributes.ControlChar.VMIN) != 0
                    || current.getControlChar(Attributes.ControlChar.VTIME) != 1) {
                try {
                    Attributes attr = getAttr();
                    attr.setControlChar(Attributes.ControlChar.VMIN, 0);
                    attr.setControlChar(Attributes.ControlChar.VTIME, 1);
                    setAttr(attr);
                } catch (IOException e) {
                    throw new IOError(e);
                }
            }
        }
    }

}
