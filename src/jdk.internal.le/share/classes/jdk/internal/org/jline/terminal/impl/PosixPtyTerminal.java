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
package jdk.internal.org.jline.terminal.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import jdk.internal.org.jline.terminal.spi.Pty;
import jdk.internal.org.jline.utils.ClosedException;
import jdk.internal.org.jline.utils.NonBlocking;
import jdk.internal.org.jline.utils.NonBlockingInputStream;
import jdk.internal.org.jline.utils.NonBlockingReader;

public class PosixPtyTerminal extends AbstractPosixTerminal {

    private final InputStream in;
    private final OutputStream out;
    private final InputStream masterInput;
    private final OutputStream masterOutput;
    private final NonBlockingInputStream input;
    private final OutputStream output;
    private final NonBlockingReader reader;
    private final PrintWriter writer;

    private final Object lock = new Object();
    private Thread inputPumpThread;
    private Thread outputPumpThread;
    private boolean paused = true;

    public PosixPtyTerminal(String name, String type, Pty pty, InputStream in, OutputStream out, Charset encoding) throws IOException {
        this(name, type, pty, in, out, encoding, SignalHandler.SIG_DFL);
    }

    public PosixPtyTerminal(String name, String type, Pty pty, InputStream in, OutputStream out, Charset encoding, SignalHandler signalHandler) throws IOException {
        this(name, type, pty, in, out, encoding, signalHandler, false);
    }

    public PosixPtyTerminal(String name, String type, Pty pty, InputStream in, OutputStream out, Charset encoding, SignalHandler signalHandler, boolean paused) throws IOException {
        super(name, type, pty, encoding, signalHandler);
        this.in = Objects.requireNonNull(in);
        this.out = Objects.requireNonNull(out);
        this.masterInput = pty.getMasterInput();
        this.masterOutput = pty.getMasterOutput();
        this.input = new InputStreamWrapper(NonBlocking.nonBlocking(name, pty.getSlaveInput()));
        this.output = pty.getSlaveOutput();
        this.reader = NonBlocking.nonBlocking(name, input, encoding());
        this.writer = new PrintWriter(new OutputStreamWriter(output, encoding()));
        parseInfoCmp();
        if (!paused) {
            resume();
        }
    }

    public InputStream input() {
        return input;
    }

    public NonBlockingReader reader() {
        return reader;
    }

    public OutputStream output() {
        return output;
    }

    public PrintWriter writer() {
        return writer;
    }

    @Override
    protected void doClose() throws IOException {
        super.doClose();
        reader.close();
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
        Thread p1, p2;
        synchronized (lock) {
            paused = true;
            p1 = inputPumpThread;
            p2 = outputPumpThread;
        }
        if (p1 != null) {
            p1.interrupt();
        }
        if (p2 != null) {
            p2.interrupt();
        }
        if (p1 != null) {
            p1.join();
        }
        if (p2 !=null) {
            p2.join();
        }
    }

    @Override
    public void resume() {
        synchronized (lock) {
            paused = false;
            if (inputPumpThread == null) {
                inputPumpThread = new Thread(this::pumpIn, this + " input pump thread");
                inputPumpThread.setDaemon(true);
                inputPumpThread.start();
            }
            if (outputPumpThread == null) {
                outputPumpThread = new Thread(this::pumpOut, this + " output pump thread");
                outputPumpThread.setDaemon(true);
                outputPumpThread.start();
            }
        }
    }

    @Override
    public boolean paused() {
        synchronized (lock) {
            return paused;
        }
    }

    private class InputStreamWrapper extends NonBlockingInputStream {

        private final NonBlockingInputStream in;
        private final AtomicBoolean closed = new AtomicBoolean();

        protected InputStreamWrapper(NonBlockingInputStream in) {
            this.in = in;
        }

        @Override
        public int read(long timeout, boolean isPeek) throws IOException {
            if (closed.get()) {
                throw new ClosedException();
            }
            return in.read(timeout, isPeek);
        }

        @Override
        public void close() throws IOException {
            closed.set(true);
        }
    }

    private void pumpIn() {
        try {
            for (;;) {
                synchronized (lock) {
                    if (paused) {
                        inputPumpThread = null;
                        return;
                    }
                }
                int b = in.read();
                if (b < 0) {
                    input.close();
                    break;
                }
                masterOutput.write(b);
                masterOutput.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            synchronized (lock) {
                inputPumpThread = null;
            }
        }
    }

    private void pumpOut() {
        try {
            for (;;) {
                synchronized (lock) {
                    if (paused) {
                        outputPumpThread = null;
                        return;
                    }
                }
                int b = masterInput.read();
                if (b < 0) {
                    input.close();
                    break;
                }
                out.write(b);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            synchronized (lock) {
                outputPumpThread = null;
            }
        }
        try {
            close();
        } catch (Throwable t) {
            // Ignore
        }
    }

}
