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
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.lang.System.LoggerFinder;
import java.lang.System.Logger;

public class LogProducerFinder extends LoggerFinder {

    static final RuntimePermission LOGGERFINDER_PERMISSION =
            new RuntimePermission("loggerFinder");
    final ConcurrentHashMap<String, PlatformLoggerBridgeTest.LoggerImpl>
            system = new ConcurrentHashMap<>();
    final ConcurrentHashMap<String, PlatformLoggerBridgeTest.LoggerImpl>
            user = new ConcurrentHashMap<>();

    private static PlatformLoggerBridgeTest.LoggerImpl newLoggerImpl(String name) {
        return new PlatformLoggerBridgeTest.LoggerImpl(name);
    }

    @Override
    public Logger getLogger(String name, Module caller) {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(LOGGERFINDER_PERMISSION);
        }
        PrivilegedAction<ClassLoader> pa = () -> caller.getClassLoader();
        ClassLoader callerLoader = AccessController.doPrivileged(pa);
        if (callerLoader == null) {
            return system.computeIfAbsent(name, (n) -> newLoggerImpl(n));
        } else {
            return user.computeIfAbsent(name, (n) -> newLoggerImpl(n));
        }
    }
}
