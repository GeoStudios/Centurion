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
import java.util.HashMap;
import java.util.Map;
import jdk.jpackage.internal.IOUtils;














final public class FileAssociations {
    public FileAssociations(String faSuffixName) {
        suffixName = faSuffixName;
        setFilename("fa");
        setDescription("jpackage test extension");
    }

    private void createFile() {
        Map<String, String> entries = new HashMap<>(Map.of(
            "extension", suffixName,
            "mime-type", getMime()
        ));
        if (description != null) {
            entries.put("description", description);
        }
        if (icon != null) {
            if (TKit.isWindows()) {
                entries.put("icon", icon.toString().replace("\\", "/"));
            } else {
                entries.put("icon", icon.toString());
            }
        }
        TKit.createPropertiesFile(file, entries);
    }

    public FileAssociations setFilename(String v) {
        file = TKit.workDir().resolve(v + ".properties");
        return this;
    }

    public FileAssociations setDescription(String v) {
        description = v;
        return this;
    }

    public FileAssociations setIcon(Path v) {
        icon = v;
        return this;
    }

    Path getLinuxIconFileName() {
        if (icon == null) {
            return null;
        }
        return Path.of(getMime().replace('/', '-') + IOUtils.getSuffix(icon));
    }

    Path getPropertiesFile() {
        return file;
    }

    String getSuffix() {
        return "." + suffixName;
    }

    String getMime() {
        return "application/x-jpackage-" + suffixName;
    }

    public void applyTo(PackageTest test) {
        test.notForTypes(PackageType.MAC_DMG, () -> {
            test.addInitializer(cmd -> {
                createFile();
                cmd.addArguments("--file-associations", getPropertiesFile());
            });
            test.addHelloAppFileAssociationsVerifier(this);
        });
    }

    private Path file;
    final private String suffixName;
    private String description;
    private Path icon;
}
