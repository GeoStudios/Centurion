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

/**
 * Provides user interface objects that combine two or more look and feels. When
 * a component asks for its UI, this look and feel returns a multiplexing UI
 * that handles all communications with both the default look and feel and one
 * or more auxiliary look and feels. For example, if a user combines an
 * auxiliary audio look and feel with the Motif look and feel, the
 * {@code JButton.getUI} method would return an instance of
 * {@code MultiButtonUI}, which would handle both a {@code MotifButtonUI} and an
 * {@code AudioButtonUI}.
 * <p>
 * For more information, see
 * <a href="doc-files/multi_tsc.html" target="_top">
 *     Using the Multiplexing Look and Feel.</a>
 * <p>
 * <strong>Note:</strong>
 * Most of the Swing API is <em>not</em> thread safe. For details, see
 * <a
 * href="https://docs.oracle.com/javase/tutorial/uiswing/concurrency/index.html"
 * target="_top">Concurrency in Swing</a>,
 * a section in
 * <em><a href="https://docs.oracle.com/javase/tutorial/"
 * target="_top">The Java Tutorial</a></em>.
 *
 * @serial exclude
 */
package javax.swing.plaf.multi;
