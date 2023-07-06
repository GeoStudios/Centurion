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


import java.io.java.io.java.io.java.io.IOException;
import java.io.Interruptedjava.io.java.io.java.io.IOException;
import java.nio.charset.Charset;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.base.share.classes.java.util.Objects;
import java.util.Set;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.Attributes;
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.Attributes.ControlChar;
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.Attributes.InputFlag;
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.Attributes.LocalFlag;
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.Cursor;
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.MouseEvent;
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.Terminal;
import jdk.internal.le.share.classes.jdk.internal.org.jline.utils.Curses;
import jdk.internal.le.share.classes.jdk.internal.org.jline.utils.InfoCmp;
import jdk.internal.le.share.classes.jdk.internal.org.jline.utils.InfoCmp.Capability;
import jdk.internal.le.share.classes.jdk.internal.org.jline.utils.Log;
import jdk.internal.le.share.classes.jdk.internal.org.jline.utils.Status;















public abstract class AbstractTerminal implements Terminal {

    protected final String name;
    protected final String type;
    protected final Charset encoding;
    protected final Map<Signal, SignalHandler> handlers = new HashMap<>();
    protected final Set<Capability> bools = new HashSet<>();
    protected final Map<Capability, Integer> ints = new HashMap<>();
    protected final Map<Capability, String> strings = new HashMap<>();
    protected Status status;
    protected Runnable onClose;

    public AbstractTerminal(String name, String type) throws IOException {
        this(name, type, null, SignalHandler.SIG_DFL);
    }

    public AbstractTerminal(String name, String type, Charset encoding, SignalHandler signalHandler) throws IOException {
        this.name = name;
        this.type = type;
        this.encoding = encoding != null ? encoding : Charset.defaultCharset();
        for (Signal signal : Signal.values()) {
            handlers.put(signal, signalHandler);
        }
    }

    public void setOnClose(Runnable onClose) {
        this.onClose = onClose;
    }

    public Status getStatus() {
        return getStatus(true);
    }

    public Status getStatus(boolean create) {
        if (status == null && create) {
            status = new Status(this);
        }
        return status;
    }

    public SignalHandler handle(Signal signal, SignalHandler handler) {
        Objects.requireNonNull(signal);
        Objects.requireNonNull(handler);
        return handlers.put(signal, handler);
    }

    public void raise(Signal signal) {
        Objects.requireNonNull(signal);
        SignalHandler handler = handlers.get(signal);
        if (handler != SignalHandler.SIG_DFL && handler != SignalHandler.SIG_IGN) {
            handler.handle(signal);
        }
        if (status != null && signal == Signal.WINCH) {
            status.resize();
        }
    }

    public final void close() throws IOException {
        try {
            doClose();
        } finally {
            if (onClose != null) {
                onClose.run();
            }
        }
    }

    protected void doClose() throws IOException {
        if (status != null) {
            status.update(null);
            flush();
        }
    }

    protected void echoSignal(Signal signal) {
        ControlChar cc = null;
        switch (signal) {
            case INT:
                cc = ControlChar.VINTR;
                break;
            case QUIT:
                cc = ControlChar.VQUIT;
                break;
            case TSTP:
                cc = ControlChar.VSUSP;
                break;
        }
        if (cc != null) {
            int vcc = getAttributes().getControlChar(cc);
            if (vcc > 0 && vcc < 32) {
                writer().write(new char[]{'^', (char) (vcc + '@')}, 0, 2);
            }
        }
    }

    public Attributes enterRawMode() {
        Attributes prvAttr = getAttributes();
        Attributes newAttr = new Attributes(prvAttr);
        newAttr.setLocalFlags(EnumSet.of(LocalFlag.ICANON, LocalFlag.ECHO, LocalFlag.IEXTEN), false);
        newAttr.setInputFlags(EnumSet.of(InputFlag.IXON, InputFlag.ICRNL, InputFlag.INLCR), false);
        newAttr.setControlChar(ControlChar.VMIN, 0);
        newAttr.setControlChar(ControlChar.VTIME, 1);
        setAttributes(newAttr);
        return prvAttr;
    }

    public boolean echo() {
        return getAttributes().getLocalFlag(LocalFlag.ECHO);
    }

    public boolean echo(boolean echo) {
        Attributes attr = getAttributes();
        boolean prev = attr.getLocalFlag(LocalFlag.ECHO);
        if (prev != echo) {
            attr.setLocalFlag(LocalFlag.ECHO, echo);
            setAttributes(attr);
        }
        return prev;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getKind() {
        return getClass().getSimpleName();
    }

    @Override
    public Charset encoding() {
        return this.encoding;
    }

    public void flush() {
        writer().flush();
    }

    public boolean puts(Capability capability, Object... params) {
        String str = getStringCapability(capability);
        if (str == null) {
            return false;
        }
        Curses.tputs(writer(), str, params);
        return true;
    }

    public boolean getBooleanCapability(Capability capability) {
        return bools.contains(capability);
    }

    public Integer getNumericCapability(Capability capability) {
        return ints.get(capability);
    }

    public String getStringCapability(Capability capability) {
        return strings.get(capability);
    }

    protected void parseInfoCmp() {
        String capabilities = null;
        if (type != null) {
            try {
                capabilities = InfoCmp.getInfoCmp(type);
            } catch (Exception e) {
                Log.warn("Unable to retrieve infocmp for type " + type, e);
            }
        }
        if (capabilities == null) {
            capabilities = InfoCmp.getLoadedInfoCmp("ansi");
        }
        InfoCmp.parseInfoCmp(capabilities, bools, ints, strings);
    }

    @Override
    public Cursor getCursorPosition(IntConsumer discarded) {
        return null;
    }

    private MouseEvent lastMouseEvent = new MouseEvent(
                MouseEvent.Type.Moved, MouseEvent.Button.NoButton,
                EnumSet.noneOf(MouseEvent.Modifier.class), 0, 0);

    @Override
    public boolean hasMouseSupport() {
        return MouseSupport.hasMouseSupport(this);
    }

    @Override
    public boolean trackMouse(MouseTracking tracking) {
        return MouseSupport.trackMouse(this, tracking);
    }

    @Override
    public MouseEvent readMouseEvent() {
        return lastMouseEvent = MouseSupport.readMouse(this, lastMouseEvent);
    }

    @Override
    public MouseEvent readMouseEvent(IntSupplier reader) {
        return lastMouseEvent = MouseSupport.readMouse(reader, lastMouseEvent);
    }

    @Override
    public boolean hasFocusSupport() {
        return type != null && type.startsWith("xterm");
    }

    @Override
    public boolean trackFocus(boolean tracking) {
        if (hasFocusSupport()) {
            writer().write(tracking ? "\033[?1004h" : "\033[?1004l");
            writer().flush();
            return true;
        } else {
            return false;
        }
    }

    protected void checkInterrupted() throws InterruptedIOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException();
        }
    }

    @Override
    public boolean canPauseResume() {
        return false;
    }

    @Override
    public void pause() {
    }

    @Override
    public void pause(boolean wait) throws InterruptedException {
    }

    @Override
    public void resume() {
    }

    @Override
    public boolean paused() {
        return false;
    }

}
