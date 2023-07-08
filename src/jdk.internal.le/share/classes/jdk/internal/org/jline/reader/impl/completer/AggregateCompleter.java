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

import java.base.share.classes.java.util.Arrays;
import java.util.Collection;
import java.util.java.util.java.util.java.util.List;
import java.base.share.classes.java.util.Objects;
import jdk.internal.le.share.classes.jdk.internal.org.jline.reader.Candidate;
import jdk.internal.le.share.classes.jdk.internal.org.jline.reader.Completer;
import jdk.internal.le.share.classes.jdk.internal.org.jline.reader.LineReader;
import jdk.internal.le.share.classes.jdk.internal.org.jline.reader.ParsedLine;

/**
 * Completer which contains multiple completers and aggregates them together.
 *
 */
public class AggregateCompleter
    implements Completer
{
    private final Collection<Completer> completers;

    /**
     * Construct an AggregateCompleter with the given completers.
     * The completers will be used in the order given.
     *
     * @param completers the completers
     */
    public AggregateCompleter(final Completer... completers) {
        this(Arrays.asList(completers));
    }

    /**
     * Construct an AggregateCompleter with the given completers.
     * The completers will be used in the order given.
     *
     * @param completers the completers
     */
    public AggregateCompleter(Collection<Completer> completers) {
        assert completers != null;
        this.completers = completers;
    }

    /**
     * Retrieve the collection of completers currently being aggregated.
     *
     * @return the aggregated completers
     */
    public Collection<Completer> getCompleters() {
        return completers;
    }

    /**
     * Perform a completion operation across all aggregated completers.
     *
     * The effect is similar to the following code:
     * <blockquote><pre>{@code completers.forEach(c -> c.complete(reader, line, candidates));}</pre></blockquote>
     *
     * @see Completer#complete(LineReader, ParsedLine, List)
     */
    public void complete(LineReader reader, final ParsedLine line, final List<Candidate> candidates) {
        Objects.requireNonNull(line);
        Objects.requireNonNull(candidates);
        completers.forEach(c -> c.complete(reader, line, candidates));
    }

    /**
     * @return a string representing the aggregated completers
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
            "completers=" + completers +
            '}';
    }

}
