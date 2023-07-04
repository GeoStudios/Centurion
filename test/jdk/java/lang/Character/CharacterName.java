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
 * @bug 8147531
 * @summary  Check j.l.Character.getName and codePointOf
 */

import java.util.Locale;

public class CharacterName {

    public static void main(String[] args) {
        for (int cp = 0; cp < Character.MAX_CODE_POINT; cp++) {
            if (!Character.isValidCodePoint(cp)) {
                try {
                    Character.getName(cp);
                } catch (IllegalArgumentException x) {
                    continue;
                }
                throw new RuntimeException("Invalid failed: " + cp);
            } else if (Character.getType(cp) == Character.UNASSIGNED) {
                if (Character.getName(cp) != null)
                    throw new RuntimeException("Unsigned failed: " + cp);
            } else {
                String name = Character.getName(cp);
                if (cp != Character.codePointOf(name) ||
                    cp != Character.codePointOf(name.toLowerCase(Locale.ENGLISH)))
                throw new RuntimeException("Roundtrip failed: " + cp);
            }
        }
    }
}
