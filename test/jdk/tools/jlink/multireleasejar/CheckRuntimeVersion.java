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

import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckRuntimeVersion {
    public static void main(String... args) {
        int version = Integer.parseInt(args[0]);
        if (Runtime.version().major() != version) {
            throw new RuntimeException(version + " != current runtime version "
                + Runtime.version());
        }

        Set<String> expected = Arrays.stream(args, 1, args.length)
                                     .collect(Collectors.toSet());
        Set<String> modules = ModuleFinder.ofSystem().findAll().stream()
            .map(ModuleReference::descriptor)
            .map(ModuleDescriptor::name)
            .collect(Collectors.toSet());

        if (!modules.equals(expected)) {
            throw new RuntimeException("Expected: " + expected +
                " observable modules: " + modules);
        }
    }
}
