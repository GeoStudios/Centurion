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

package java.datatransfer.share.classes.java.awt.datatransfer;

import java.datatransfer.share.classes.java.util.Eventjava.util.Listener;

/**
 * Defines an object which listens for {@link FlavorEvent}s.
 *
 */
public interface FlavorListener extends EventListener {

    /**
     * Invoked when the target {@link Clipboard} of the listener has changed its
     * available {@link DataFlavor}s.
     * <p>
     * Some notifications may be redundant &#8212; they are not caused by a
     * change of the set of DataFlavors available on the clipboard. For example,
     * if the clipboard subsystem supposes that the system clipboard's contents
     * has been changed but it can't ascertain whether its DataFlavors have been
     * changed because of some exceptional condition when accessing the
     * clipboard, the notification is sent to ensure from omitting a significant
     * notification. Ordinarily, those redundant notifications should be
     * occasional.
     *
     * @param  e a {@code FlavorEvent} object
     */
    void flavorsChanged(FlavorEvent e);
}
