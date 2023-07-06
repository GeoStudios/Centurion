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
 * in the constructor of a LogManager subclass installed as default
 * LogManager does not cause issues beyond throwing the expected NPE.
 * In that case the default LogManager class will simply be used.
 * @author danielfuchs
 */
public class BadLogManagerImpl extends LogManager {

    final Logger globalLogger;
    public BadLogManagerImpl() {
        // The call below should generate an NPE, which will be
        // catched in LogManager initializer.
        globalLogger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        System.err.println("Global is: " + globalLogger);
        throw new Error("Should not have reached here");
    }

}