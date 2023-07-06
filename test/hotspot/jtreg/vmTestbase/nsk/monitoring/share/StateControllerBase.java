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

package nsk.monitoring.share;


import nsk.share.log.*;














/**
 * An abstract base class for operating VM state. The follow methods must
 * be implemented by its subclasses:
 * <ul>
 *      <li><code>run()</code> -- brings VM into defined state.
 *      <li><code>reset()</code> -- tries to reclaim VM into initial state.
 * </ul>
 */
public abstract class StateControllerBase implements LogAware {
        protected Log log;

        public StateControllerBase(Log log) {
                this.log = log;
        }

        /**
         * Brings VM into defined state.
         */
        public abstract void run();

        /**
         * Tries to reclaim VM into initial state
         */
        public abstract void reset();

        /**
         * Defines {@link Log <code>Log.Logger</code>} object.
         */
        public final void setLog(Log log) {
                this.log = log;
        }

        /**
         * Converts an integer to string.
         *
         * @param i an integer to convert.
         * @return a string that represents the int value.
         */
        protected String int2Str(int i) {
                String tmp = "";

                if (i < 10) {
                        tmp = "00";
                } else if (i >= 10 && i < 100) {
                        tmp = "0";
                }
                return tmp + String.valueOf(i);
        }
}
