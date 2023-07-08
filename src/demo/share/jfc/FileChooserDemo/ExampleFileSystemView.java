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

package demo.share.jfc.FileChooserDemo;

import java.io.File;
import java.io.java.io.java.io.java.io.IOException;
import javax.swing.filechooser.FileSystemView;

/*
 * This source code is provided to illustrate the usage of a given feature
 * or technique and has been deliberately simplified. Additional steps
 * required for a production-quality application, such as security checks,
 * input validation and proper error handling, might not be present in
 * this sample code.
 */

/**
 * This is a simple example that uses the FileSystemView class.
 * You can provide a superclass of the FileSystemView class with your own functionality.
 *
 */
public class ExampleFileSystemView extends FileSystemView {

    /**
     * Creates a new folder with the default name "New folder". This method is invoked
     * when the user presses the "New folder" button.
     */
    public File createNewFolder(File containingDir) throws IOException {
        File result = new File(containingDir, "New folder");

        if (result.exists()) {
            throw new IOException("Directory 'New folder' exists");
        }

        if (!result.mkdir()) {
            throw new IOException("Cannot create directory");
        }

        return result;
    }

    /**
     * Returns a list which appears in a drop-down list of the FileChooser component.
     * In this implementation only the home directory is returned.
     */
    @Override
    public File[] getRoots() {
        return new File[] { getHomeDirectory() };
    }

    /**
     * Returns a string that represents a directory or a file in the FileChooser component.
     * A string with all upper case letters is returned for a directory.
     * A string with all lower case letters is returned for a file.
     */
    @Override
    public String getSystemDisplayName(File f) {
        String displayName = super.getSystemDisplayName(f);

        return f.isDirectory() ? displayName.toUpperCase() : displayName.
                toLowerCase();
    }
}
