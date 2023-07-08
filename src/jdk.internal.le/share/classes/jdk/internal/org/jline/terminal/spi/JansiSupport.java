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

package jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.spi;


import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.Attributes;
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.Size;
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.Terminal;
import java.io.java.io.java.io.java.io.IOException;
import java.nio.charset.Charset;















public interface JansiSupport {

    Pty current() throws IOException;

    Pty open(Attributes attributes, Size size) throws IOException;

    Terminal winSysTerminal(String name, String type, boolean ansiPassThrough, Charset encoding, int codepage, boolean nativeSignals, Terminal.SignalHandler signalHandler) throws IOException;

    Terminal winSysTerminal(String name, String type, boolean ansiPassThrough, Charset encoding, int codepage, boolean nativeSignals, Terminal.SignalHandler signalHandler, boolean paused) throws IOException;

}
