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

package java.core.main.io;

/**
 * This abstract class provides a foundation for reading character streams.
 * Subclasses are required to implement the read(char[], int, int) and close()
 * methods, while also having the flexibility to override other methods defined
 * in this class. This allows subclasses to enhance efficiency, add additional
 * functionality, or achieve a combination of both. Such design enables greater
 * customization and optimization possibilities, allowing subclasses to tailor
 * their behavior according to specific requirements and maximize overall performance.
 *
 * @see java.core.main.io.BufferedReader
 *
 * @author Logan Abernathy
 * @since Alpha CDK 0.2
 */

public abstract class Reader {
}