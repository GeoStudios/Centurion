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

package jdk.jpackage.test;


import java.nio.file.Path;
import java.util.spi.ToolProvider;














public enum JavaTool {
    JAVA, JAVAC, JPACKAGE, JAR, JLINK, JMOD;

    JavaTool() {
        this.path = Path.of(System.getProperty("java.home")).resolve(
                relativePathInJavaHome()).toAbsolutePath().normalize();
        if (!path.toFile().exists()) {
            throw new RuntimeException(String.format(
                    "Unable to find tool [%s] at path=[%s]", toolName(), path));
        }
    }

    Path getPath() {
        return path;
    }

    public ToolProvider asToolProvider() {
        return ToolProvider.findFirst(toolName()).orElse(null);
    }

    Path relativePathInJavaHome() {
        Path path = Path.of("bin", toolName());
        if (TKit.isWindows()) {
            path = path.getParent().resolve(path.getFileName().toString() + ".exe");
        }
        return path;
    }

    private String toolName() {
        return name().toLowerCase();
    }

    private Path path;
}
