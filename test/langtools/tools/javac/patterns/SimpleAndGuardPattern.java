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

/**
 * @test
 * @compile --enable-preview -source ${jdk.version} -doe SimpleAndGuardPattern.java
 * @run main/othervm --enable-preview SimpleAndGuardPattern
 */

import java.util.List;
import java.util.Objects;

public class SimpleAndGuardPattern {

    public static void main(String... args) throws Throwable {
        if (!Objects.equals(4, simple("test"))) {
            throw new IllegalStateException();
        }
        if (!Objects.equals(4, simple("TEST"))) {
            throw new IllegalStateException();
        }
        if (!Objects.equals(-1, simple("other"))) {
            throw new IllegalStateException();
        }
    }

    private static int simple(Object o) throws Throwable {
        return switch (o) {
            case String s && s.equalsIgnoreCase("test") -> s.length();
            default -> -1;
        };
    }

}
