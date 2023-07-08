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

package nsk.share;
















/**
 * This class is an simple exalmple of finalizable object, that implements interface
 * <code>Finalizable</code> and invokes standard <code>finalize()</code> method
 * as finalization.
 *
 * @see Finalizable
 * @see Finalizer
 */
public class FinalizableObject implements Finalizable {

    /**
     * This method will be invoked by <tt>Finalizer</tt> when virtual mashine
     * shuts down.
     * For <code>FinalizableObject</code> this method just invoke
     * <code>finalize()</code>.
     *
     * @throws Throwable if any throwable exception thrown during finalization
     *
     * @see Object#finalize()
     * @see Finalizer
     */
    public void finalizeAtExit() throws Throwable {
        finalize();
    }
}
