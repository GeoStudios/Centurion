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

package vm.runtime.defmeth.shared;

import static java.lang.String.format;

/**
 * Set of constants which are used in the test suite.
 * The actual values can be changed through corresponding property.
 */
public class Constants {
    static public final boolean PRINT_ASSEMBLY    = getValue("vm.runtime.defmeth.printAssembly",          false);
    static public final boolean PRINT_TESTS       = getValue("vm.runtime.defmeth.printTests",             false);
    static public final boolean ASMIFY            = getValue("vm.runtime.defmeth.printASMify",            false);
    static public final boolean DUMP_CLASSES      = getValue("vm.runtime.defmeth.dumpClasses",            false);
    static public final boolean PRINT_STACK_TRACE = getValue("vm.runtime.defmeth.printStackTrace",        false);
    static public final boolean TRACE_CLASS_REDEF = getValue("vm.runtime.defmeth.traceClassRedefinition", false);

    /**
     * Get value of the test suite's property
     * Supported values:
     *   ""        == true
     *   "true"    == true
     *   "false"   == false
     *   undefined == default value
     */
    static boolean getValue(String property, boolean defaultValue) {
        String value = System.getProperty(property);

        if ("false".equals(value))  return false;
        if ("true".equals(value))   return true;
        if ("".equals(value))       return true;
        if (value == null )         return defaultValue;

        throw new IllegalArgumentException(format("Unknown value: -D%s=%s", property, value));
    }
}
