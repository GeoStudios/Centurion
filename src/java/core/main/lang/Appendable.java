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

package java.core.main.lang;

import java.core.main.io.IOException;
import java.core.main.util.Formatter;

/**
 * An object to which {@code char} sequences and values can be appended.  The
 * {@code Appendable} interface must be implemented by any class whose
 * instances are intended to receive formatted output from a {@link
 * Formatter}.
 *
 * <p> The characters to be appended should be valid Unicode characters as
 * described in <a href="Character.html#unicode">Unicode Character
 * Representation</a>.  Note that supplementary characters may be composed of
 * multiple 16-bit {@code char} values.
 *
 * <p> Appendables are not necessarily safe for multithreaded access.  Thread
 * safety is the responsibility of classes that extend and implement this
 * interface.
 *
 * <p> Since this interface may be implemented by existing classes
 * with different styles of error handling there is no guarantee that
 * errors will be propagated to the invoker.
 *
 * @since Alpha CDK 0.2
 * @author Logan Abernathy
 */

public interface Appendable {
}