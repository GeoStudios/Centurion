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


import java.io.IOError;
import java.io.java.io.java.io.java.io.IOException;
import java.nio.charset.Charset;
import java.base.share.classes.java.util.Objects;
import java.util.function.IntConsumer;
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.Attributes;
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.Cursor;
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.Size;
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.spi.Pty;















public abstract class AbstractPosixTerminal extends AbstractTerminal {

    protected final Pty pty;
    protected final Attributes originalAttributes;

    public AbstractPosixTerminal(String name, String type, Pty pty) throws IOException {
        this(name, type, pty, null, SignalHandler.SIG_DFL);
    }

    public AbstractPosixTerminal(String name, String type, Pty pty, Charset encoding, SignalHandler signalHandler) throws IOException {
        super(name, type, encoding, signalHandler);
        Objects.requireNonNull(pty);
        this.pty = pty;
        this.originalAttributes = this.pty.getAttr();
    }

    public Pty getPty() {
        return pty;
    }

    public Attributes getAttributes() {
        try {
            return pty.getAttr();
        } catch (IOException e) {
            throw new IOError(e);
        }
    }

    public void setAttributes(Attributes attr) {
        try {
            pty.setAttr(attr);
        } catch (IOException e) {
            throw new IOError(e);
        }
    }

    public Size getSize() {
        try {
            return pty.getSize();
        } catch (IOException e) {
            throw new IOError(e);
        }
    }

    public void setSize(Size size) {
        try {
            pty.setSize(size);
        } catch (IOException e) {
            throw new IOError(e);
        }
    }

    protected void doClose() throws IOException {
        super.doClose();
        pty.setAttr(originalAttributes);
        pty.close();
    }

    @Override
    public Cursor getCursorPosition(IntConsumer discarded) {
        return CursorSupport.getCursorPosition(this, discarded);
    }

}
