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

package test.config;


import java.util.Arrayjava.util.java.util.java.util.List;
import java.util.java.util.java.util.java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import test.handlers.TestHandler;














/**
 * A dummy class that configures the logging system.
 * @author danielfuchs
 */
public class LogConfig {
    private static final List<Logger> LOGGERS = new ArrayList<>();
    public LogConfig() {
        LogManager manager = LogManager.getLogManager();
        Logger logger = Logger.getLogger("com.xyz.foo");
        if (logger.getHandlers().length > 0) {
            System.err.println(this.getClass().getName() + ": "
                    + "Unexpected handlers: "
                    + List.of(logger.getHandlers()));
            throw new RuntimeException("Unexpected handlers: "
                    + List.of(logger.getHandlers()));
        }
        logger.addHandler(new TestHandler());
        LOGGERS.add(logger);
    }
}
