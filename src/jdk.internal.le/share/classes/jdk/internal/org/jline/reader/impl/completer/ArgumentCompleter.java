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
package jdk.internal.org.jline.reader.impl.completer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import jdk.internal.org.jline.reader.Candidate;
import jdk.internal.org.jline.reader.Completer;
import jdk.internal.org.jline.reader.LineReader;
import jdk.internal.org.jline.reader.ParsedLine;

/**
 * A {@link Completer} implementation that invokes a child completer using the appropriate <i>separator</i> argument.
 * This can be used instead of the individual completers having to know about argument parsing semantics.
 *
 */
public class ArgumentCompleter implements Completer
{
    private final List<Completer> completers = new ArrayList<>();

    private boolean strict = true;
    private boolean strictCommand = true;

    /**
     * Create a new completer.
     *
     * @param completers    The embedded completers
     */
    public ArgumentCompleter(final Collection<Completer> completers) {
        Objects.requireNonNull(completers);
        this.completers.addAll(completers);
    }

    /**
     * Create a new completer.
     *
     * @param completers    The embedded completers
     */
    public ArgumentCompleter(final Completer... completers) {
        this(Arrays.asList(completers));
    }

    /**
     * If true, a completion at argument index N will only succeed
     * if all the completions from 0-(N-1) also succeed.
     *
     * @param strict the strict flag
     */
    public void setStrict(final boolean strict) {
        this.strict = strict;
    }

    /**
     * If true, a completion at argument index N will only succeed
     * if all the completions from 1-(N-1) also succeed.
     *
     * @param strictCommand the strictCommand flag
     */
    public void setStrictCommand(final boolean strictCommand) {
        this.strictCommand = strictCommand;
    }
    /**
     * Returns whether a completion at argument index N will success
     * if all the completions from arguments 0-(N-1) also succeed.
     *
     * @return  True if strict.
     */
    public boolean isStrict() {
        return this.strict;
    }

    /**
     * Returns the list of completers used inside this <code>ArgumentCompleter</code>.
     * @return The list of completers.
     */
    public List<Completer> getCompleters() {
        return completers;
    }

    public void complete(LineReader reader, ParsedLine line, final List<Candidate> candidates) {
        Objects.requireNonNull(line);
        Objects.requireNonNull(candidates);

        if (line.wordIndex() < 0) {
            return;
        }

        List<Completer> completers = getCompleters();
        Completer completer;

        // if we are beyond the end of the completers, just use the last one
        if (line.wordIndex() >= completers.size()) {
            completer = completers.get(completers.size() - 1);
        }
        else {
            completer = completers.get(line.wordIndex());
        }

        // ensure that all the previous completers are successful before allowing this completer to pass (only if strict).
        for (int i = strictCommand ? 0 : 1; isStrict() && (i < line.wordIndex()); i++) {
            int idx = i >= completers.size() ? (completers.size() - 1) : i;
            if (idx == 0 && !strictCommand) {
                continue;
            }
            Completer sub = completers.get(idx);
            List<? extends CharSequence> args = line.words();
            String arg = (args == null || i >= args.size()) ? "" : args.get(i).toString();

            List<Candidate> subCandidates = new LinkedList<>();
            sub.complete(reader, new ArgumentLine(arg, arg.length()), subCandidates);

            boolean found = false;
            for (Candidate cand : subCandidates) {
                if (cand.value().equals(arg)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return;
            }
        }

        completer.complete(reader, line, candidates);
    }

    public static class ArgumentLine implements ParsedLine {
        private final String word;
        private final int cursor;

        public ArgumentLine(String word, int cursor) {
            this.word = word;
            this.cursor = cursor;
        }

        @Override
        public String word() {
            return word;
        }

        @Override
        public int wordCursor() {
            return cursor;
        }

        @Override
        public int wordIndex() {
            return 0;
        }

        @Override
        public List<String> words() {
            return Collections.singletonList(word);
        }

        @Override
        public String line() {
            return word;
        }

        @Override
        public int cursor() {
            return cursor;
        }
    }
}
