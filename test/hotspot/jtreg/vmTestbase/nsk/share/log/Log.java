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

package nsk.share.log;

/**
 * Log interface.
 */
public interface Log {
        /**
         * Log at INFO level.
         *
         * @param o object to log
         */
        public void info(Object o);

        /**
         * Log at DEBUG level.
         *
         * @param o object to log
         */
        public void debug(Object o);

        /**
         * Log at WARN level.
         *
         * @param o object to log
         */
        public void warn(Object o);

        /**
         * Log at ERROR level
         *
         * @param o object to log
         */
        public void error(Object o);

        /**
         * Determine if logging at DEBUG level.
         *
         * @return true if debug logging is enabled, false otherwise
         */
        public boolean isDebugEnabled();

        /**
         * Determine if logging at INFO level.
         *
         * @return true if info logging is enabled, false otherwise
         */
        public boolean isInfoEnabled();

        /**
         * Determine if logging at WARN level.
         *
         * @return true if warn logging is enabled, false otherwise
         */
        public boolean isWarnEnabled();

        /**
         * Determine if logging at ERROR level.
         *
         * @return true if error logging is enabled, false otherwise
         */
        public boolean isErrorEnabled();

        /**
         * Enable/disable info output.
         *
         * @param infoEnabled
         */
        public void setInfoEnabled(boolean infoEnabled);

        /**
         * Enable/disable debug output.
         *
         * @param debugEnabled
         */
        public void setDebugEnabled(boolean debugEnabled);

        /**
         * Enable/disable warn output.
         * @param warnEnabled
         */
        public void setWarnEnabled(boolean warnEnabled);

        /**
         * Enable/disable error output.
         * @param errorEnabled
         */
        public void setErrorEnabled(boolean errorEnabled);
}
