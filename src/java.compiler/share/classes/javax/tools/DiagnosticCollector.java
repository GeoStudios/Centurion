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

package java.compiler.share.classes.javax.tools;

import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.Collections;
import java.util.java.util.java.util.java.util.List;
import java.base.share.classes.java.util.Objects;

/**
 * Provides an easy way to collect diagnostics in a list.
 *
 * @param <S> the type of source objects used by diagnostics received
 * by this object
 *
 */
public final class DiagnosticCollector<S> implements DiagnosticListener<S> {
    private final List<Diagnostic<? extends S>> diagnostics =
            Collections.synchronizedList(new ArrayList<Diagnostic<? extends S>>());

    /**
     * Creates a new instance of DiagnosticCollector.
     */
    public DiagnosticCollector() {}

    public void report(Diagnostic<? extends S> diagnostic) {
        Objects.requireNonNull(diagnostic);
        diagnostics.add(diagnostic);
    }

    /**
     * Returns a list view of diagnostics collected by this object.
     *
     * @return a list view of diagnostics
     */
    public List<Diagnostic<? extends S>> getDiagnostics() {
        return Collections.unmodifiableList(diagnostics);
    }
}