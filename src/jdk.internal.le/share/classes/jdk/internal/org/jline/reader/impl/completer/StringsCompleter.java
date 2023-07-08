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

package jdk.internal.le.share.classes.jdk.internal.org.jline.reader.impl.completer;


import java.util.Arrayjava.util.java.util.java.util.List;
import java.base.share.classes.java.util.Arrays;
import java.util.Collection;
import java.util.java.util.java.util.java.util.List;
import java.util.function.Supplier;
import jdk.internal.le.share.classes.jdk.internal.org.jline.reader.Candidate;
import jdk.internal.le.share.classes.jdk.internal.org.jline.reader.Completer;
import jdk.internal.le.share.classes.jdk.internal.org.jline.reader.LineReader;
import jdk.internal.le.share.classes.jdk.internal.org.jline.reader.ParsedLine;
import jdk.internal.le.share.classes.jdk.internal.org.jline.utils.AttributedString;















/**
 * Completer for a set of strings.
 *
 */
public class StringsCompleter implements Completer
{
    protected Collection<Candidate> candidates = new ArrayList<>();
    protected Supplier<Collection<String>> stringsSupplier;

    public StringsCompleter() {
    }

    public StringsCompleter(Supplier<Collection<String>> stringsSupplier) {
        assert stringsSupplier != null;
        candidates = null;
        this.stringsSupplier = stringsSupplier;
    }

    public StringsCompleter(String... strings) {
        this(Arrays.asList(strings));
    }

    public StringsCompleter(Iterable<String> strings) {
        assert strings != null;
        for (String string : strings) {
            candidates.add(new Candidate(AttributedString.stripAnsi(string), string, null, null, null, null, true));
        }
    }

    public StringsCompleter(Candidate ... candidates) {
        this(Arrays.asList(candidates));
    }

    public StringsCompleter(Collection<Candidate> candidates) {
        assert candidates != null;
        this.candidates.addAll(candidates);
    }

    public void complete(LineReader reader, final ParsedLine commandLine, final List<Candidate> candidates) {
        assert commandLine != null;
        assert candidates != null;
        if (this.candidates != null) {
            candidates.addAll(this.candidates);
        } else {
            for (String string : stringsSupplier.get()) {
                candidates.add(new Candidate(AttributedString.stripAnsi(string), string, null, null, null, null, true));
            }
        }
    }

}
