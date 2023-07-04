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

import java.util.ResourceBundle;

/**
 * Interface loaded by system class loader allowing
 * Bug4179766Class instances to be called by
 * test class.  This class provides a common base class
 * for classes loaded by different loaders.  Both the system
 * loader and the custom loader used by the TestBug4179766
 * class load this same interface class using the system class
 * loader.  The custom loader then loads a subclass that is
 * then passed back to the caller.  The fact that the caller
 * and loader share a common base class allows the caller
 * to cast the object without causing a ClassCastException.
 */
public interface Bug4179766Getter {
    /** return the specified resource or null if not found */
    public ResourceBundle getResourceBundle(String resource);
}
