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

// NOTE:  This class is part of the ResourceBundleTest.

// NOTE:  As of 11/21/97, this class isn't actually being used by the ResourceBundleTest
// anymore.  I've left it here simply because we don't currently have a way to obsolete
// a file in RCS.  If you're running it, you've got a problem!

import java.util.*;
import java.io.*;

public class TestResource_de extends PropertyResourceBundle {
    public TestResource_de() throws IOException, FileNotFoundException {
        super(new FileInputStream("TestResource_de"));
    }
}
