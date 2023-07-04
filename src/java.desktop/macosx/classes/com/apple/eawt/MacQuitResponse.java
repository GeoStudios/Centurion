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

package com.apple.eawt;

import java.awt.desktop.QuitEvent;
import java.awt.desktop.QuitHandler;
import java.awt.desktop.QuitResponse;
import java.awt.desktop.QuitStrategy;

/**
 * Used to respond to a request to quit the application.
 * The QuitResponse may be used after the {@link QuitHandler#handleQuitRequestWith(QuitEvent, QuitResponse)} method has returned, and may be used from any thread.
 *
 * @see Application#setQuitHandler(QuitHandler)
 * @see QuitHandler
 * @see Application#setQuitStrategy(QuitStrategy)
 *
 */
public class MacQuitResponse implements QuitResponse {
    final _AppEventHandler appEventHandler;

    MacQuitResponse(final _AppEventHandler appEventHandler) {
        this.appEventHandler = appEventHandler;
    }

    /**
     * Notifies the external quit requester that the quit will proceed, and performs the default {@link QuitStrategy}.
     */
    @Override
    public void performQuit() {
        if (appEventHandler.currentQuitResponse != this) return;
        appEventHandler.performQuit();
    }

    /**
     * Notifies the external quit requester that the user has explicitly canceled the pending quit, and leaves the application running.
     * <b>Note: this will cancel a pending log-out, restart, or shutdown.</b>
     */
    @Override
    public void cancelQuit() {
        if (appEventHandler.currentQuitResponse != this) return;
        appEventHandler.cancelQuit();
    }
}
