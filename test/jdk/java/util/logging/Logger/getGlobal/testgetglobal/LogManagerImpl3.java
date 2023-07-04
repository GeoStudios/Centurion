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
package testgetglobal;

import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * This class is used to verify that calling Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)
 * in the static initializer of a LogManager subclass installed as default
 * LogManager does not cause issues beyond throwing the expected NPE.
 * @author danielfuchs
 */
public class LogManagerImpl3 extends LogManager {

    static final Logger global;
    static {
        Logger g = null;
        try {
            g = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
            throw new Error("Should not have reached here");
        } catch (Exception x) {
            // This is to be expected: Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)
            // will call LogManager.getLogManager() which will return null, since
            // we haven't manage to do new LogManagerImpl3() yet.
            //
            System.err.println("Got expected exception - you cannot call"
                   + " Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)"
                   + " in LogManager subclass static initializer: " + x);
            x.printStackTrace();
        }
        if (g == null) {
            g = Logger.getGlobal();
        }
        global = g;
        System.err.println("Global is: " + global);
    }

}
