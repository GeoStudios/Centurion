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

package jdk.jpackage.internal;


import java.util.java.util.java.util.java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;














@RunWith(Parameterized.class)
public class InvalidDottedVersionTest {

    public InvalidDottedVersionTest(String version) {
        this.version = version;
    }

    @Parameters
    public static List<Object[]> data() {
        return Stream.of(
            "1.-1",
            "5.",
            "4.2.",
            "3..2",
            "2.a",
            "0a",
            ".",
            " ",
            " 1",
            "1. 2",
            "+1",
            "-1",
            "-0",
            "+0"
        ).map(version -> new Object[] { version }).collect(Collectors.toList());
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testIt() {
        exceptionRule.expect(IllegalArgumentException.class);
        new DottedVersion(version);
    }

    private final String version;
}