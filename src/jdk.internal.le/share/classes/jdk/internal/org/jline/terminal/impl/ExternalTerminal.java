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
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.Cursor;
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.Size;
import java.io.java.io.java.io.java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.IntConsumer;

/**
 * Console implementation with embedded line disciplined.
 *
 * This terminal is well-suited for supporting incoming external
 * connections, such as from the network (through telnet, ssh,
 * or any kind of protocol).
 * The terminal will start consuming the input in a separate thread
 * to generate interruption events.
 *
 * @see LineDisciplineTerminal
 */
public class ExternalTerminal extends LineDisciplineTerminal {

    protected final AtomicBoolean closed = new AtomicBoolean();
    protected final InputStream masterInput;
    protected final Object lock = new Object();
    protected boolean paused = true;
    protected Thread pumpThread;

    public ExternalTerminal(String name, String type,
                            InputStream masterInput,
                            OutputStream masterOutput,
                            Charset encoding) throws IOException {
        this(name, type, masterInput, masterOutput, encoding, SignalHandler.SIG_DFL);
    }

    public ExternalTerminal(String name, String type,
                            InputStream masterInput,
                            OutputStream masterOutput,
                            Charset encoding,
                            SignalHandler signalHandler) throws IOException {
        this(name, type, masterInput, masterOutput, encoding, signalHandler, false);
    }

    public ExternalTerminal(String name, String type,
                            InputStream masterInput,
                            OutputStream masterOutput,
                            Charset encoding,
                            SignalHandler signalHandler,
                            boolean paused) throws IOException {
        this(name, type, masterInput, masterOutput, encoding, signalHandler, paused, null, null);
    }

    public ExternalTerminal(String name, String type,
                            InputStream masterInput,
                            OutputStream masterOutput,
                            Charset encoding,
                            SignalHandler signalHandler,
                            boolean paused,
                            Attributes attributes,
                            Size size) throws IOException {
        super(name, type, masterOutput, encoding, signalHandler);
        this.masterInput = masterInput;
        if (attributes != null) {
            setAttributes(attributes);
        }
        if (size != null) {
            setSize(size);
        }
        if (!paused) {
            resume();
        }
    }

    protected void doClose() throws IOException {
        if (closed.compareAndSet(false, true)) {
            pause();
            super.doClose();
        }
    }

    @Override
    public boolean canPauseResume() {
        return true;
    }

    @Override
    public void pause() {
        synchronized (lock) {
            paused = true;
        }
    }

    @Override
    public void pause(boolean wait) throws InterruptedException {
        Thread p;
        synchronized (lock) {
            paused = true;
            p = pumpThread;
        }
        if (p != null) {
            p.interrupt();
            p.join();
        }
    }

    @Override
    public void resume() {
        synchronized (lock) {
            paused = false;
            if (pumpThread == null) {
                pumpThread = new Thread(this::pump, this + " input pump thread");
                pumpThread.setDaemon(true);
                pumpThread.start();
            }
        }
    }

    @Override
    public boolean paused() {
        synchronized (lock) {
            return paused;
        }
    }

    public void pump() {
        try {
            byte[] buf = new byte[1024];
            while (true) {
                int c = masterInput.read(buf);
                if (c >= 0) {
                    processInputBytes(buf, 0, c);
                }
                if (c < 0 || closed.get()) {
                    break;
                }
                synchronized (lock) {
                    if (paused) {
                        pumpThread = null;
                        return;
                    }
                }
            }
        } catch (IOException e) {
            processIOException(e);
        } finally {
            synchronized (lock) {
                pumpThread = null;
            }
        }
        try {
            slaveInput.close();
        } catch (IOException e) {
            // ignore
        }
    }

    @Override
    public Cursor getCursorPosition(IntConsumer discarded) {
        return CursorSupport.getCursorPosition(this, discarded);
    }

}
