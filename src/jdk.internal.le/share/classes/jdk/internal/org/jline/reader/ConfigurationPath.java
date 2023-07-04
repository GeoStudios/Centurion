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
package jdk.internal.org.jline.reader;

import java.io.IOException;
import java.nio.file.Path;

public class ConfigurationPath {
    private final Path appConfig;
    private final Path userConfig;

    /**
     * Configuration class constructor.
     * @param appConfig   Application configuration directory
     * @param userConfig  User private configuration directory
     */
    public ConfigurationPath(Path appConfig, Path userConfig) {
        this.appConfig = appConfig;
        this.userConfig = userConfig;
    }

    /**
     * Search configuration file first from userConfig and then appConfig directory. Returns null if file is not found.
     * @param  name    Configuration file name.
     * @return         Configuration file.
     *
     */
    public Path getConfig(String name) {
        Path out = null;
        if (userConfig != null && userConfig.resolve(name).toFile().exists()) {
            out = userConfig.resolve(name);
        } else if (appConfig != null && appConfig.resolve(name).toFile().exists()) {
            out = appConfig.resolve(name);
        }
        return out;
    }

    /**
     * Search configuration file from userConfig directory. Returns null if file is not found.
     * @param  name    Configuration file name.
     * @return         Configuration file.
     * @throws         IOException   When we do not have read access to the file or directory.
     *
     */
    public Path getUserConfig(String name) throws IOException {
        return getUserConfig(name, false);
    }

    /**
     * Search configuration file from userConfig directory. Returns null if file is not found.
     * @param  name    Configuration file name
     * @param  create  When true configuration file is created if not found.
     * @return         Configuration file.
     * @throws         IOException   When we do not have read/write access to the file or directory.
     */
    public Path getUserConfig(String name, boolean create) throws IOException {
        Path out = null;
        if (userConfig != null) {
            if (!userConfig.resolve(name).toFile().exists() && create) {
                userConfig.resolve(name).toFile().createNewFile();
            }
            if (userConfig.resolve(name).toFile().exists()) {
                out = userConfig.resolve(name);
            }
        }
        return out;
    }

}
