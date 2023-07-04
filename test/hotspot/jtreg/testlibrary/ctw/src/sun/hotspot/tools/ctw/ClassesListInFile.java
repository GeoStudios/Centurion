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

package sun.hotspot.tools.ctw;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Handler for files containing a list of classes to compile.
 */
public class ClassesListInFile extends PathHandler.PathEntry {
    private final BufferedReader reader;

    public ClassesListInFile(Path root) {
        super(root);
        if (!Files.exists(root)) {
            throw new Error(root + " file does not exist");
        }
        try {
           reader = Files.newBufferedReader(root);
        } catch (IOException e) {
            throw new Error("can not open " + root + " : " + e.getMessage(), e);
        }
    }

    @Override
    protected byte[] findByteCode(String name) {
        return null;
    }

    @Override
    protected Stream<String> classes() {
        return reader.lines();
    }

    @Override
    protected String description() {
        return "# list: " + root;
    }

    @Override
    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            throw new Error("error on closing reader for " + root
                    + " : "  + e.getMessage(), e);
        } finally {
            super.close();
        }
    }
}
