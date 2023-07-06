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

/**
 * Defines the interface for classes that will provide data to a clipboard. An
 * instance of this interface becomes the owner of the contents of a clipboard
 * (clipboard owner) if it is passed as an argument to
 * {@link Clipboard#setContents} method of the clipboard and this method returns
 * successfully. The instance remains the clipboard owner until another
 * application or another object within this application asserts ownership of
 * this clipboard.
 *
 * @see Clipboard
 */
public interface ClipboardOwner {

    /**
     * Notifies this object that it is no longer the clipboard owner. This
     * method will be called when another application or another object within
     * this application asserts ownership of the clipboard.
     *
     * @param  clipboard the clipboard that is no longer owned
     * @param  contents the contents which this owner had placed on the
     *         {@code clipboard}
     */
    void lostOwnership(Clipboard clipboard, Transferable contents);
}