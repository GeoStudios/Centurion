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

package jdk.jshell.share.classes.jdk.jshell;

















/**
 * Grouping for Snippets which persist and influence future code.
 * A persistent snippet can be
 * {@linkplain jdk.jshell.Snippet.Status#OVERWRITTEN overwritten}
 * with new input.
 * <p>
 * <code>PersistentSnippet</code> is immutable: an access to
 * any of its methods will always return the same result.
 * and thus is thread-safe.
 *
 */
public abstract class PersistentSnippet extends Snippet {

    PersistentSnippet(Key key, String userSource, Wrap guts, String unitName,
            SubKind subkind, DiagList syntheticDiags) {
        super(key, userSource, guts, unitName, subkind, syntheticDiags);
    }

    /**
     * Name of the Snippet.
     *
     * @return the name of the snippet.
     */
    @Override
    public String name() {
        return unitName;
    }
}
