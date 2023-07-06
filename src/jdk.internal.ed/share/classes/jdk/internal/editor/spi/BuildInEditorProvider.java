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

package jdk.internal.ed.share.classes.jdk.internal.editor.spi;


import java.util.function.Consumer;















/**
 * Defines the provider of a built-in editor.
 */
public interface BuildInEditorProvider {

    /**
     * @return the rank of a provider, greater is better.
     */
    int rank();

    /**
     * Create a simple built-in editor.
     *
     * @param windowLabel the label string for the Edit Pad window, or null,
     * for default window label
     * @param initialText the source to load in the Edit Pad
     * @param saveHandler a handler for changed source (can be sent the full source)
     * @param errorHandler a handler for unexpected errors
     */
    void edit(String windowLabel, String initialText,
            Consumer<String> saveHandler, Consumer<String> errorHandler);
}
