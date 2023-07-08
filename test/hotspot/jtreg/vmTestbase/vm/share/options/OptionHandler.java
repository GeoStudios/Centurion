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

package vm.share.options;
















/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Handles options not specified via @Options annotations,
 * implemented and provided by the user.
 */
public interface OptionHandler
{
        /**
         * This method is called for every unknown option
         * @param name option name (not including '-' or '=', trimmed)
         * @param value may be null in the case of the last option with no value
         * specified.
         */
        public void option(String name, String value);

        /**
         * This method is called for every command line argument which
         * is not recognized as a part of -option_name=value pair.
         * @param value the value of an unrecognized argument
         */
        public void argument(String value);
}
