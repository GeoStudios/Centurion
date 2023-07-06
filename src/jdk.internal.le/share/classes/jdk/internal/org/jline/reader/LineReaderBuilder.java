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

package jdk.internal.le.share.classes.jdk.internal.org.jline.reader;

import java.io.IOError;
import java.io.java.io.java.io.java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.base.share.classes.java.util.Objects;
import jdk.internal.le.share.classes.jdk.internal.org.jline.reader.impl.LineReaderImpl;
import jdk.internal.le.share.classes.jdk.internal.org.jline.reader.impl.history.DefaultHistory;
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.Terminal;
import jdk.internal.le.share.classes.jdk.internal.org.jline.terminal.TerminalBuilder;
import jdk.internal.le.share.classes.jdk.internal.org.jline.utils.Log;

public final class LineReaderBuilder {

    public static LineReaderBuilder builder() {
        return new LineReaderBuilder();
    }

    Terminal terminal;
    String appName;
    Map<String, Object> variables = new HashMap<>();
    Map<LineReader.Option, Boolean> options = new HashMap<>();
    History history;
    Completer completer;
    History memoryHistory;
    Highlighter highlighter;
    Parser parser;
    Expander expander;

    private LineReaderBuilder() {
    }

    public LineReaderBuilder terminal(Terminal terminal) {
        this.terminal = terminal;
        return this;
    }

    public LineReaderBuilder appName(String appName) {
        this.appName = appName;
        return this;
    }

    public LineReaderBuilder variables(Map<String, Object> variables) {
        Map<String, Object> old = this.variables;
        this.variables = Objects.requireNonNull(variables);
        this.variables.putAll(old);
        return this;
    }

    public LineReaderBuilder variable(String name, Object value) {
        this.variables.put(name, value);
        return this;
    }

    public LineReaderBuilder option(LineReader.Option option, boolean value) {
        this.options.put(option, value);
        return this;
    }

    public LineReaderBuilder history(History history) {
        this.history = history;
        return this;
    }

    public LineReaderBuilder completer(Completer completer) {
        this.completer = completer;
        return this;
    }

    public LineReaderBuilder highlighter(Highlighter highlighter) {
        this.highlighter = highlighter;
        return this;
    }

    public LineReaderBuilder parser(Parser parser) {
        if (parser != null) {
            try {
                if (!Boolean.getBoolean(LineReader.PROP_SUPPORT_PARSEDLINE)
                        && !(parser.parse("", 0) instanceof CompletingParsedLine)) {
                    Log.warn("The Parser of class " + parser.getClass().getName() + " does not support the CompletingParsedLine interface. " +
                            "Completion with escaped or quoted words won't work correctly.");
                }
            } catch (Throwable t) {
                // Ignore
            }
        }
        this.parser = parser;
        return this;
    }

    public LineReaderBuilder expander(Expander expander) {
        this.expander = expander;
        return this;
    }

    public LineReader build() {
        Terminal terminal = this.terminal;
        if (terminal == null) {
            try {
                terminal = TerminalBuilder.terminal();
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        LineReaderImpl reader = new LineReaderImpl(terminal, appName, variables);
        if (history != null) {
            reader.setHistory(history);
        } else {
            if (memoryHistory == null) {
                memoryHistory = new DefaultHistory();
            }
            reader.setHistory(memoryHistory);
        }
        if (completer != null) {
            reader.setCompleter(completer);
        }
        if (highlighter != null) {
            reader.setHighlighter(highlighter);
        }
        if (parser != null) {
            reader.setParser(parser);
        }
        if (expander != null) {
            reader.setExpander(expander);
        }
        for (Map.Entry<LineReader.Option, Boolean> e : options.entrySet()) {
            reader.option(e.getKey(), e.getValue());
        }
        return reader;
    }

}
