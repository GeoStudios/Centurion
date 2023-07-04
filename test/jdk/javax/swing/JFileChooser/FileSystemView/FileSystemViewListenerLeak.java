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

import java.io.File;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

/**
 * @test
 * @bug 8175968 8198342
 * @summary FileSystemView should clean listeners in UIManager before removal
 * @library /javax/swing/regtesthelpers
 * @build Util
 * @run main/othervm -Xmx8m -Djava.awt.headless=true FileSystemViewListenerLeak
*/
public final class FileSystemViewListenerLeak {

    public static void main(final String[] args) {
        checkListenersCount();
        new CustomFileSystemView();
        Util.generateOOME();
        checkListenersCount();
    }

    private static void checkListenersCount() {
        int length = UIManager.getPropertyChangeListeners().length;
        if (length != 0) {
            throw new RuntimeException("The count of listeners is: " + length);
        }
    }

    private static final class CustomFileSystemView extends FileSystemView {

        public File createNewFolder(File containingDir) throws IOException {
            return null;
        }
    }
}
